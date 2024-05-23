package com.example.orderUp_api.repository.database.product;

import com.example.orderUp_api.entity.sql.database.product.ProductEntity;
import com.example.orderUp_api.enums.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    Optional<ProductEntity> findByName(String name);
    Optional<ProductEntity> findByIdAndStatus(String id, ProductStatus status);
    Optional<ProductEntity> findByIdAndStatusNot(String productId, ProductStatus status);
    Page<ProductEntity> findByCategory_IdAndStatusNot(String categoryId, ProductStatus status, Pageable pageable);
    Page<ProductEntity> findByStatusNot(ProductStatus status, Pageable pageable);

    @Query(value = """
            select *
            from product p
            where category_id regexp ?1
            	and status regexp ?2
            """, nativeQuery = true)
    Page<ProductEntity> getProductList(String categoryIdRegex, String productStatusRegex, Pageable pageable);

    @Query(value = """
            select count(*)
            from product p
            join order_item oi on p.id = oi.product_id
            where p.id = ?1
            """, nativeQuery = true)
    long countOrderItem(String productId);

    @Query(value = """
            select *
            from product p\s
            join (
            	select sum(id.quantity) as sold_quantity, oi.product_id \s
            	from order_item oi \s
            	join item_detail id ON oi.id = id.order_item_id\s
            	join product p on p.id = oi.product_id\s
            	join order_bill ob on ob.id = oi.order_bill_id\s
            	where p.status != 'HIDDEN'\s
            		and ob.created_at >= DATE_SUB(CURDATE(), INTERVAL 1 WEEK)
            		and ob.created_at <= CURDATE()
            	group by oi.product_id\s
            ) as top_product on top_product.product_id = p.id\s
            order by top_product.sold_quantity desc
            limit ?1
            """, nativeQuery = true)
    List<ProductEntity> getTopProductBySoldQuantity(int limit);

    @Query(value = """
            select p.id, p.created_at, p.description, p.name, p.price, p.status, p.`type`, p.updated_at, p.category_id, p.image_id
            from product p\s
            left join (
            	select p.id, avg(pr.star) as star, count(*) as quantity
            	from product p\s
            	join order_item oi on p.id = oi.product_id\s
            	join product_review pr on oi.product_review_id = pr.id\s
            	where p.status != 'HIDDEN'
            	group by p.id\s
            ) as top_product on p.id = top_product.id
            where p.status != 'HIDDEN'
            order by top_product.star desc
            limit ?1
            """, nativeQuery = true)
    List<ProductEntity> getTopRatingProduct(int limit);

    @Query(value = """
            select p.id, p.created_at, p.description, p.name, p.price, p.status, p.`type`, p.updated_at, p.category_id, p.image_id
            from product p\s
            join (select product.id as product_id, COALESCE(avg(pr.star), 0) as star\s
            	  from (select *
            			from product p\s
            			where p.category_id = ?1 and p.price between ?2 and ?3 and p.status != 'HIDDEN') as product
            			left join order_item oi on oi.product_id = product.id\s
            			left join product_review pr on oi.product_review_id = pr.id\s
            			group by product.id ) as prd on prd.product_id = p.id
            where prd.star >= ?4
            """, nativeQuery = true)
    Page<ProductEntity> getProductByCategoryIdAndFilter(String categoryId, long minPrice, long maxPrice, int minStar, Pageable pageable);

    @Query(value = """
            select p.id, p.created_at, p.description, p.name, p.price, p.status, p.`type`, p.updated_at, p.category_id, p.image_id
            from product p
            join (select product.id as product_id, COALESCE(avg(pr.star), 0) as star
                  from (select *
                        from product p
                        where p.price between ?1 and ?2 and p.status != 'HIDDEN') as product
                        left join order_item oi on oi.product_id = product.id
                        left join product_review pr on oi.product_review_id = pr.id
                        group by product.id ) as prd on prd.product_id = p.id
            where prd.star >= ?3
            """, nativeQuery = true)
    Page<ProductEntity> getAllProductAndFilter(long minPrice, long maxPrice, int minStar, Pageable pageable);
}
