# Spring Boot Leave-Attendance Management System

## Issue: JRE vs JDK
You're currently running Java Runtime Environment (JRE) which doesn't include the Java compiler. To run this Spring Boot project, you need:

### Option 1: Install JDK 8 (Recommended)
1. Download JDK 8 from Oracle or adopt OpenJDK
2. Install it and set JAVA_HOME to point to the JDK (not JRE)
3. Update your PATH to use JDK's bin directory

### Option 2: Use IDE
1. Import this project into an IDE like IntelliJ IDEA or Eclipse
2. The IDE will handle compilation and running

### Option 3: Pre-compiled JAR
I can help you create a pre-compiled JAR file if you have access to a JDK environment.

## Current Project Structure
This project includes:
- Employee management
- Leave request system  
- Attendance tracking
- H2 in-memory database
- REST APIs

## API Endpoints
Once running, the application will be available at:
- Base URL: http://localhost:8080
- H2 Console: http://localhost:8080/h2-console

### Employee APIs
- POST /api/employees - Create employee
- GET /api/employees - Get all employees  
- GET /api/employees/{id} - Get employee by ID
- PUT /api/employees/{id} - Update employee
- DELETE /api/employees/{id} - Delete employee

### Leave Request APIs
- POST /api/employees/{empId}/leaves - Submit leave request
- GET /api/employees/{empId}/leaves - Get employee's leaves
- PUT /api/employees/{empId}/leaves/{leaveId}/approve - Approve leave
- PUT /api/employees/{empId}/leaves/{leaveId}/reject - Reject leave
- GET /api/leaves/pending - Get all pending leaves (admin)

### Attendance APIs
- POST /api/employees/{empId}/attendance/checkin - Check in
- PUT /api/employees/{empId}/attendance/{recordId}/checkout - Check out
- GET /api/employees/{empId}/attendance - Get attendance history

## Sample Requests
See the original instructions for detailed examples.
