package com.example.ems.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "employees")
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Size(max = 80) private String firstName;
    @NotBlank @Size(max = 80) private String lastName;
    @NotBlank @Email @Column(unique = true) private String email;
    @NotBlank private String phone;
    @NotBlank private String jobTitle;
    @NotNull @DecimalMin("0.0") private BigDecimal salary;
    @NotBlank private String status = "ACTIVE";
    @ManyToOne @JoinColumn(name = "department_id") private Department department;

    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getJobTitle() { return jobTitle; }
    public BigDecimal getSalary() { return salary; }
    public String getStatus() { return status; }
    public Department getDepartment() { return department; }
    public void setId(Long id) { this.id = id; }
    public void setFirstName(String v) { firstName = v; }
    public void setLastName(String v) { lastName = v; }
    public void setEmail(String v) { email = v; }
    public void setPhone(String v) { phone = v; }
    public void setJobTitle(String v) { jobTitle = v; }
    public void setSalary(BigDecimal v) { salary = v; }
    public void setStatus(String v) { status = v; }
    public void setDepartment(Department v) { department = v; }
    @Transient public String getFullName() { return firstName + " " + lastName; }
}
