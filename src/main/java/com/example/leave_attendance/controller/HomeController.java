package com.example.leave_attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {
      @GetMapping("/")
    public String home() {
        return "redirect:/index.html";
    }
    
    @GetMapping("/api")
    @ResponseBody
    public Map<String, Object> apiInfo() {
        Map<String, Object> response = new HashMap<>();
        response.put("application", "Leave & Attendance Management System");
        response.put("version", "1.0.0");
        response.put("status", "Running");
        response.put("timestamp", java.time.LocalDateTime.now());
        
        Map<String, Object> endpoints = new HashMap<>();
        
        // Employee endpoints
        Map<String, String> employeeEndpoints = new HashMap<>();
        employeeEndpoints.put("GET /api/employees", "Get all employees");
        employeeEndpoints.put("POST /api/employees", "Create new employee");
        employeeEndpoints.put("GET /api/employees/{id}", "Get employee by ID");
        employeeEndpoints.put("PUT /api/employees/{id}", "Update employee");
        employeeEndpoints.put("DELETE /api/employees/{id}", "Delete employee");
        endpoints.put("employees", employeeEndpoints);
        
        // Leave request endpoints
        Map<String, String> leaveEndpoints = new HashMap<>();
        leaveEndpoints.put("GET /api/employees/{empId}/leaves", "Get leaves for employee");
        leaveEndpoints.put("POST /api/employees/{empId}/leaves", "Submit leave request");
        leaveEndpoints.put("PUT /api/employees/{empId}/leaves/{leaveId}/approve", "Approve leave");
        leaveEndpoints.put("PUT /api/employees/{empId}/leaves/{leaveId}/reject", "Reject leave");
        endpoints.put("leaves", leaveEndpoints);
        
        // Attendance endpoints
        Map<String, String> attendanceEndpoints = new HashMap<>();
        attendanceEndpoints.put("GET /api/employees/{empId}/attendance", "Get attendance records");
        attendanceEndpoints.put("POST /api/employees/{empId}/attendance/checkin", "Check in");
        attendanceEndpoints.put("PUT /api/employees/{empId}/attendance/{id}/checkout", "Check out");
        endpoints.put("attendance", attendanceEndpoints);
        
        response.put("endpoints", endpoints);
        
        Map<String, String> database = new HashMap<>();
        database.put("h2Console", "http://localhost:8080/h2-console");
        database.put("jdbcUrl", "jdbc:h2:mem:leaveattendancedb");
        database.put("username", "sa");
        database.put("password", "");
        response.put("database", database);
        
        return response;
    }
    
    @GetMapping("/health")
    @ResponseBody
    public Map<String, String> health() {
        Map<String, String> health = new HashMap<>();
        health.put("status", "UP");
        health.put("timestamp", java.time.LocalDateTime.now().toString());
        return health;
    }
}
