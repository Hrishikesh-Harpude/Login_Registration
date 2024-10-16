package com.example.RegistrationLogin.Controller;

import com.example.RegistrationLogin.Dto.EmployeeDTO;
import com.example.RegistrationLogin.Dto.LoginDTO;
import com.example.RegistrationLogin.Payloadresponse.LoginResponse;
import com.example.RegistrationLogin.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @PostMapping(path = "/save")
    public String saveEmployee(@RequestBody EmployeeDTO employeeDto)
    {
        String id=employeeService.addEmployee(employeeDto);
        return id;
    }

    @PostMapping(path = "/login")

    public ResponseEntity<?> loginEmployee(@RequestBody LoginDTO loginDTO)
    {
        LoginResponse loginResponse=employeeService.loginEmployee(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }

}
