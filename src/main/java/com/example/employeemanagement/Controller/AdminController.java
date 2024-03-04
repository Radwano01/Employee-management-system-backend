package com.example.employeemanagement.Controller;


import com.example.employeemanagement.Dto.AdminLoginDto;
import com.example.employeemanagement.Dto.employee.AuthResponse;
import com.example.employeemanagement.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path="${ADMIN_CONTROLLER_PATH}")
public class AdminController {

    private final AdminService adminService;
    @Autowired
    private AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @PostMapping("${ADMIN_LOGIN_PATH}")
    public ResponseEntity<AuthResponse> adminLogin(@RequestBody AdminLoginDto adminLoginDto) {
        return adminService.Login(adminLoginDto);
    }


}
