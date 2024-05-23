package com.example.orderUp_api.repository.database.order;

//import com.example.orderUp_api.dto.sql.GetStatisticByKeyValue;
import com.example.orderUp_api.entity.sql.database.order.OrderEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderEventRepository extends JpaRepository<OrderEventEntity, String> {

    List<OrderEventEntity> findByOrderBill_IdOrderByCreatedAtDesc(String orderBillId);
//
//
//    @Query(value = """
//            SELECT s.order_status as 'key', COALESCE( statistic.count, 0) as value
//            FROM (
//            	SELECT 'SUCCEED' AS order_status
//            	UNION ALL
//            	SELECT 'CANCEL' AS order_status
//            	UNION ALL
//            	SELECT 'NOT_RECEIVED' AS order_status
//            	) AS s
//            left join (
//            	SELECT oe.order_status, COALESCE(count(*), 0) AS count
//            	from order_bill ob
//            	join (
//            		select order_bill_id, MAX(created_at) as created_at
//            	 	from order_event
//            	 	group by order_bill_id
//            	) as last_event on ob.id = last_event.order_bill_id
//            	join order_event oe on last_event.created_at = oe.created_at\s
//            	join `user` u on u.id = ob.user_id\s
//            	WHERE u.id = ?1\s
//            	AND oe.order_status IN ('SUCCEED', 'CANCELED', 'NOT_RECEIVED')
//            	group by oe.order_status
//            ) as statistic on statistic.order_status = s.order_status
//            """, nativeQuery = true)
//    List<GetStatisticByKeyValue> getCountOrderEventStatisticsByUser(String userId);
}
