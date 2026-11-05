package com.example.ems.config;

import com.example.ems.model.Department;
import com.example.ems.model.Employee;
import com.example.ems.repository.DepartmentRepository;
import com.example.ems.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner seedData(DepartmentRepository departments, EmployeeRepository employees) {
        return args -> {
            if (departments.count() == 0) {
                Department engineering = departments.save(new Department("Engineering"));
                Department hr = departments.save(new Department("Human Resources"));
                departments.save(new Department("Finance"));
                if (employees.count() == 0) {
                    Employee a = new Employee();
                    a.setFirstName("Aarav"); a.setLastName("Sharma"); a.setEmail("aarav@example.com");
                    a.setPhone("9876543210"); a.setJobTitle("Software Engineer"); a.setSalary(new BigDecimal("65000"));
                    a.setStatus("ACTIVE"); a.setDepartment(engineering); employees.save(a);
                    Employee b = new Employee();
                    b.setFirstName("Ananya"); b.setLastName("Rao"); b.setEmail("ananya@example.com");
                    b.setPhone("9876501234"); b.setJobTitle("HR Executive"); b.setSalary(new BigDecimal("45000"));
                    b.setStatus("ACTIVE"); b.setDepartment(hr); employees.save(b);
                }
            }
        };
    }
}
