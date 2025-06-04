package com.example.leave_attendance.controller;

import com.example.leave_attendance.model.AttendanceRecord;
import com.example.leave_attendance.service.AttendanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees/{empId}/attendance")
public class AttendanceRecordController {
    @Autowired
    private AttendanceRecordService attendanceService;

    @PostMapping("/checkin")
    public ResponseEntity<AttendanceRecord> checkIn(@PathVariable("empId") Long empId) {
        AttendanceRecord record = attendanceService.checkIn(empId);
        return ResponseEntity.ok(record);
    }

    @PutMapping("/{recordId}/checkout")
    public ResponseEntity<AttendanceRecord> checkOut(@PathVariable("recordId") Long recordId) {
        AttendanceRecord updated = attendanceService.checkOut(recordId);
        return ResponseEntity.ok(updated);
    }

    @GetMapping
    public ResponseEntity<List<AttendanceRecord>> getAttendanceByEmployee(@PathVariable("empId") Long empId) {
        return ResponseEntity.ok(attendanceService.getByEmployee(empId));
    }
}
