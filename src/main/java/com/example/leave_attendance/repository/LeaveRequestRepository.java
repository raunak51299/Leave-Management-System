package com.example.leave_attendance.repository;

import com.example.leave_attendance.model.LeaveRequest;
import com.example.leave_attendance.model.LeaveRequest.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployeeId(Long employeeId);
    List<LeaveRequest> findByStatus(LeaveStatus status);
}
