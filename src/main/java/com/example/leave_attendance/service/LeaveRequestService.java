package com.example.leave_attendance.service;

import com.example.leave_attendance.exception.BadRequestException;
import com.example.leave_attendance.exception.ResourceNotFoundException;
import com.example.leave_attendance.model.Employee;
import com.example.leave_attendance.model.LeaveRequest;
import com.example.leave_attendance.model.LeaveRequest.LeaveStatus;
import com.example.leave_attendance.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LeaveRequestService {
    @Autowired
    private LeaveRequestRepository leaveRepo;

    @Autowired
    private EmployeeService employeeService;

    public LeaveRequest submitLeave(Long employeeId, LeaveRequest payload) {
        Employee emp = employeeService.getById(employeeId);
        if (payload.getEndDate().isBefore(payload.getStartDate())) {
            throw new BadRequestException("End date cannot be before start date");
        }
        payload.setEmployee(emp);
        payload.setStatus(LeaveStatus.PENDING);
        return leaveRepo.save(payload);
    }

    public List<LeaveRequest> getByEmployee(Long employeeId) {
        employeeService.getById(employeeId); // throws if not exist
        return leaveRepo.findByEmployeeId(employeeId);
    }

    public List<LeaveRequest> getAllPending() {
        return leaveRepo.findByStatus(LeaveStatus.PENDING);
    }

    public LeaveRequest approve(Long leaveId) {
        LeaveRequest lr = leaveRepo.findById(leaveId)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found with id " + leaveId));
        lr.setStatus(LeaveStatus.APPROVED);
        return leaveRepo.save(lr);
    }

    public LeaveRequest reject(Long leaveId) {
        LeaveRequest lr = leaveRepo.findById(leaveId)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found with id " + leaveId));
        lr.setStatus(LeaveStatus.REJECTED);
        return leaveRepo.save(lr);
    }
}
