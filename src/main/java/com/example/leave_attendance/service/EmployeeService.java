package com.example.leave_attendance.service;

import com.example.leave_attendance.exception.ResourceNotFoundException;
import com.example.leave_attendance.model.Employee;
import com.example.leave_attendance.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee create(Employee emp) {
        return employeeRepository.save(emp);
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee update(Long id, Employee updated) {
        Employee existing = getById(id);
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setDepartment(updated.getDepartment());
        return employeeRepository.save(existing);
    }

    public void delete(Long id) {
        Employee existing = getById(id);
        employeeRepository.delete(existing);
    }
}
