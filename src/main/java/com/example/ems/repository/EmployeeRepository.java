package com.example.ems.repository;

import com.example.ems.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select e from Employee e where lower(concat(e.firstName, ' ', e.lastName)) like lower(concat('%', :q, '%')) or lower(e.email) like lower(concat('%', :q, '%')) or lower(e.jobTitle) like lower(concat('%', :q, '%'))")
    List<Employee> search(@Param("q") String query);
    long countByStatus(String status);
}
