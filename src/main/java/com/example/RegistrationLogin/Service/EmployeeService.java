package com.example.RegistrationLogin.Service;

import com.example.RegistrationLogin.Dto.EmployeeDTO;
import com.example.RegistrationLogin.Dto.LoginDTO;
import com.example.RegistrationLogin.Payloadresponse.LoginResponse;
import org.springframework.stereotype.Service;

public interface EmployeeService {
    String addEmployee(EmployeeDTO employeeDto);

    LoginResponse loginEmployee(LoginDTO loginDTO);
}
