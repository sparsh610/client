package com.freelancing.servercode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.freelancing.servercode.model.Employee;
import com.freelancing.servercode.service.TestService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TestController
{
    @Autowired
    TestService testService;

    @RequestMapping(value = "/employees", method = RequestMethod.GET, produces = "application/json")
    public List<Employee> firstPage()
    {
        return null;
    }

    @PostMapping(value = "/employees")
    public Employee create(@RequestBody Employee user)
    {
        Employee response = testService.saveEmployee(user);
        return response;
    }

    @GetMapping(value = "/fetchEmployees")
    public List<Employee> fetchEmployees()
    {
        List<Employee> response = testService.fetchEmployees();
        return response;
    }

    @DeleteMapping(value = "/deleteEmployee/{emailid}")
    public void deleteEmployee(@PathVariable("emailid") String emailId)
    {
        testService.deleteEmployee(emailId);
    }

    @PostMapping(value = "/updateEmployee")
    public void updateEmployee(@RequestBody Employee user)
    {
        testService.updateEmployee(user);
    }
}
