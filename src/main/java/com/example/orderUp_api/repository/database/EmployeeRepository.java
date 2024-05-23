package com.example.orderUp_api.repository.database;

import com.example.orderUp_api.entity.sql.database.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {
    Optional<EmployeeEntity> findByUsernameAndIsDeletedFalse(String username);
    Optional<EmployeeEntity> findByIdAndIsDeletedFalse(String username);

    @Query(value = """
            select e.id, e.birth_date, e.created_at, e.first_name, e.last_name, e.gender, e.email, e.is_deleted, e.password, e.phone_number, e.status, e.updated_at, e.username, e.branch_id\s
            from employee e
            where e.status regexp ?1
            and is_deleted = false
            """, nativeQuery = true)
    Page<EmployeeEntity> getEmployeeList(String statusRegex, Pageable pageable);

    @Query(value = """
            select e.id, e.birth_date, e.created_at, e.first_name, e.last_name, e.gender, e.email, e.is_deleted, e.password, e.phone_number, e.status, e.updated_at, e.username, e.branch_id\s
            from employee e
            where is_deleted = false
            and status regexp  ?2
            and username regexp ?1
            """, nativeQuery = true)
    Page<EmployeeEntity> searchEmployee(String key, String status, Pageable pageable);

    @Query(value = """
            select e.id, e.birth_date, e.created_at, e.first_name, e.last_name, e.gender, e.email, e.is_deleted, e.password, e.phone_number, e.status, e.updated_at, e.username, e.branch_id\s
            from employee e
            where e.branch_id = ?1
            and is_deleted = false
            and e.status regexp ?2
            """, nativeQuery = true)
    Page<EmployeeEntity> getEmployeeListByBranchId(String branchId, String statusRegex, Pageable pageable);

    @Query(value = """
            select e.id, e.birth_date, e.created_at, e.first_name, e.last_name, e.gender, e.email, e.is_deleted, e.password, e.phone_number, e.status, e.updated_at, e.username, e.branch_id\s
            from employee e
            where e.branch_id = ?1
            and is_deleted = false
            and status regexp  ?3
            and username regexp ?2
            """, nativeQuery = true)
    Page<EmployeeEntity> searchEmployeeByBranchId(String branchId, String key, String status, Pageable pageable);

    @Query(value = """
            select e.id, e.birth_date, e.created_at, e.first_name, e.last_name, e.gender, e.email, e.is_deleted, e.password, e.phone_number, e.status, e.updated_at, e.username, e.branch_id\s
            from branch b\s
            join (
            	select *
            	from employee e\s
            	join employee_role er on e.id = er.employee_id\s
            	where er.role_id = ?2 and e.status = 'ACTIVE'
            ) as e on e.branch_id = b.id\s
            where b.id = ?1
            """, nativeQuery = true)
    Optional<EmployeeEntity> getEmployeeOfBranchWithRole(String branchId, String roleId);
}
