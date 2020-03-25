package com.freelancing.servercode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancing.servercode.dao.TestDao;
import com.freelancing.servercode.model.Employee;
import com.freelancing.servercode.table.User;

@Service
public class TestService
{
    @Autowired
    TestDao testDao;

    public Employee saveEmployee(Employee user)
    {
        Employee e = testDao.saveEmployee(user);
        return e;
    }

    public List<Employee> fetchEmployees()
    {
        List<Employee> e = testDao.fetchEmployees();
        return e;
    }

    public void deleteEmployee(String emailId)
    {
        testDao.deleteEmployee(emailId);
    }

    public void updateEmployee(Employee user)
    {
        testDao.updateEmployee(user);
    }

    public void saveUser(User user)
    {
        testDao.saveUser(user);
    }
}
