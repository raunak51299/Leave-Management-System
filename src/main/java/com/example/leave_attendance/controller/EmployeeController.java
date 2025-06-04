package com.example.leave_attendance.controller;

import com.example.leave_attendance.model.Employee;
import com.example.leave_attendance.service.EmployeeService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService empService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee emp) {
        Employee created = empService.create(emp);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(empService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(empService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody Employee payload) {
        return ResponseEntity.ok(empService.update(id, payload));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        empService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
