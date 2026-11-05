package com.example.ems.controller;

import com.example.ems.model.Department;
import com.example.ems.repository.DepartmentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentRepository departments;
    public DepartmentController(DepartmentRepository departments) { this.departments = departments; }

    @GetMapping public String list(Model model) {
        model.addAttribute("departments", departments.findAll());
        model.addAttribute("department", new Department());
        return "departments/list";
    }
    @PostMapping public String add(@ModelAttribute Department department, RedirectAttributes redirect) {
        if (department.getName() != null && !department.getName().isBlank()) {
            departments.save(department);
            redirect.addFlashAttribute("successMessage", "Department added successfully.");
        }
        return "redirect:/departments";
    }
    @PostMapping("/{id}/delete") public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        departments.deleteById(id);
        redirect.addFlashAttribute("successMessage", "Department deleted.");
        return "redirect:/departments";
    }
}
