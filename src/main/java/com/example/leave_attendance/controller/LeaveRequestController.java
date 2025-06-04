package com.example.leave_attendance.controller;

import com.example.leave_attendance.model.LeaveRequest;
import com.example.leave_attendance.service.LeaveRequestService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees/{empId}/leaves")
public class LeaveRequestController {
    @Autowired
    private LeaveRequestService leaveService;

    @PostMapping
    public ResponseEntity<LeaveRequest> submitLeave(
            @PathVariable("empId") Long empId,
            @Valid @RequestBody LeaveRequest payload) {
        LeaveRequest created = leaveService.submitLeave(empId, payload);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<LeaveRequest>> getLeavesByEmployee(@PathVariable("empId") Long empId) {
        return ResponseEntity.ok(leaveService.getByEmployee(empId));
    }

    // Admin endpoints—approve/reject a leave
    @PutMapping("/{leaveId}/approve")
    public ResponseEntity<LeaveRequest> approveLeave(
            @PathVariable("leaveId") Long leaveId) {
        return ResponseEntity.ok(leaveService.approve(leaveId));
    }

    @PutMapping("/{leaveId}/reject")
    public ResponseEntity<LeaveRequest> rejectLeave(
            @PathVariable("leaveId") Long leaveId) {
        return ResponseEntity.ok(leaveService.reject(leaveId));
    }
}

// Additional controller for admin operations
@RestController
@RequestMapping("/api/leaves")
class AdminLeaveController {
    @Autowired
    private LeaveRequestService leaveService;

    @GetMapping("/pending")
    public ResponseEntity<List<LeaveRequest>> getAllPendingLeaves() {
        return ResponseEntity.ok(leaveService.getAllPending());
    }

    @PutMapping("/{leaveId}/approve")
    public ResponseEntity<LeaveRequest> approveLeave(@PathVariable Long leaveId) {
        return ResponseEntity.ok(leaveService.approve(leaveId));
    }

    @PutMapping("/{leaveId}/reject")
    public ResponseEntity<LeaveRequest> rejectLeave(@PathVariable Long leaveId) {
        return ResponseEntity.ok(leaveService.reject(leaveId));
    }
}
