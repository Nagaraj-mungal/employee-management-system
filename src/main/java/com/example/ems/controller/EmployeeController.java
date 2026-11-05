package com.example.ems.controller;

import com.example.ems.model.Employee;
import com.example.ems.repository.DepartmentRepository;
import com.example.ems.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmployeeController {
    private final EmployeeRepository employees;
    private final DepartmentRepository departments;

    public EmployeeController(EmployeeRepository employees, DepartmentRepository departments) {
        this.employees = employees; this.departments = departments;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("totalEmployees", employees.count());
        model.addAttribute("activeEmployees", employees.countByStatus("ACTIVE"));
        model.addAttribute("inactiveEmployees", employees.countByStatus("INACTIVE"));
        model.addAttribute("departmentCount", departments.count());
        model.addAttribute("recentEmployees", employees.findAll(Sort.by(Sort.Direction.DESC, "id")).stream().limit(5).toList());
        return "dashboard";
    }

    @GetMapping("/employees")
    public String list(@RequestParam(defaultValue = "") String q, Model model) {
        model.addAttribute("employees", q.isBlank() ? employees.findAll(Sort.by("id").descending()) : employees.search(q));
        model.addAttribute("q", q);
        return "employees/list";
    }

    @GetMapping("/employees/new")
    public String createForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departments.findAll());
        model.addAttribute("pageTitle", "Add Employee");
        return "employees/form";
    }

    @GetMapping("/employees/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employees.findById(id).orElseThrow());
        model.addAttribute("departments", departments.findAll());
        model.addAttribute("pageTitle", "Edit Employee");
        return "employees/form";
    }

    @PostMapping("/employees/save")
    public String save(@Valid @ModelAttribute Employee employee, BindingResult result,
                       @RequestParam(required = false) Long departmentId, Model model,
                       RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departments.findAll());
            model.addAttribute("pageTitle", employee.getId() == null ? "Add Employee" : "Edit Employee");
            return "employees/form";
        }
        if (departmentId != null) employee.setDepartment(departments.findById(departmentId).orElse(null));
        employees.save(employee);
        redirect.addFlashAttribute("successMessage", "Employee saved successfully.");
        return "redirect:/employees";
    }

    @PostMapping("/employees/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        employees.deleteById(id);
        redirect.addFlashAttribute("successMessage", "Employee deleted successfully.");
        return "redirect:/employees";
    }
}
