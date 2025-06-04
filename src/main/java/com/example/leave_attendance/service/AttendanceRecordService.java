package com.example.leave_attendance.service;

import com.example.leave_attendance.exception.ResourceNotFoundException;
import com.example.leave_attendance.model.AttendanceRecord;
import com.example.leave_attendance.model.Employee;
import com.example.leave_attendance.repository.AttendanceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceRecordService {
    @Autowired
    private AttendanceRecordRepository attendanceRepo;

    @Autowired
    private EmployeeService employeeService;

    public AttendanceRecord checkIn(Long employeeId) {
        Employee emp = employeeService.getById(employeeId);
        AttendanceRecord record = new AttendanceRecord(LocalDateTime.now(), emp);
        return attendanceRepo.save(record);
    }

    public AttendanceRecord checkOut(Long recordId) {
        AttendanceRecord record = attendanceRepo.findById(recordId)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance record not found with id " + recordId));
        if (record.getCheckOutTime() != null) {
            throw new IllegalStateException("Already checked out");
        }
        record.setCheckOutTime(LocalDateTime.now());
        return attendanceRepo.save(record);
    }

    public List<AttendanceRecord> getByEmployee(Long employeeId) {
        employeeService.getById(employeeId);
        return attendanceRepo.findByEmployeeId(employeeId);
    }
}
