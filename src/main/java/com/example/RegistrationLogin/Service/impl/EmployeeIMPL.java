package com.example.RegistrationLogin.Service.impl;

import com.example.RegistrationLogin.Dto.EmployeeDTO;
import com.example.RegistrationLogin.Dto.LoginDTO;
import com.example.RegistrationLogin.Entity.Employee;
import com.example.RegistrationLogin.Payloadresponse.LoginResponse;
import com.example.RegistrationLogin.Repositry.EmployeeRepo;
import com.example.RegistrationLogin.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeIMPL implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public String addEmployee(EmployeeDTO employeeDto) {

        Employee employee=new Employee(
                employeeDto.getEmployeeid(),
                employeeDto.getEmployeename(),
                employeeDto.getEmail(),
                this.passwordEncoder.encode(employeeDto.getPassword())

        );
        employeeRepo.save(employee);
        return employee.getEmployeename();
    }

    @Override
    public LoginResponse loginEmployee(LoginDTO loginDTO) {
        String msg="";
        Employee employee1=employeeRepo.findByEmail(loginDTO.getEmail());

        if (employee1 !=null){
            String password=loginDTO.getPassword();
            String encodedPassword=employee1.getPassword();
            Boolean isPwdRight=passwordEncoder.matches(password,encodedPassword);
            if (isPwdRight) {
                Optional<Employee> employee = employeeRepo.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (employee.isPresent()) {
                    return new LoginResponse("Login Successfully", true);
                } else {
                    return new LoginResponse("Login Failed", false);

                }
            }else {
                    return new LoginResponse("Password Not Match",false);
                }
            }
        else {
            return new LoginResponse("EMail not exists",false);
        }
    }
}

