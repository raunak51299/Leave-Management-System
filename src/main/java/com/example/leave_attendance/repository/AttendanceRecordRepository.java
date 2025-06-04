package com.example.leave_attendance.repository;

import com.example.leave_attendance.model.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {
    List<AttendanceRecord> findByEmployeeId(Long employeeId);
    List<AttendanceRecord> findByCheckInTimeBetween(LocalDateTime start, LocalDateTime end);
}
