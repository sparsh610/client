package com.freelancing.servercode.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.freelancing.servercode.model.Employee;
import com.freelancing.servercode.table.EmployeeTable;
import com.freelancing.servercode.table.User;

@Repository
public class TestDao
{
    @Autowired
    private EntityManager entityManager;

    private Session getSession()
    {
        return entityManager.unwrap(Session.class);
    }

    public Employee saveEmployee(Employee user)
    {
        EmployeeTable emp = new EmployeeTable();
        emp.setDesignation(user.getDesignation());
        emp.setEmailid(user.getEmailid());
        emp.setEmployeename(user.getEmployeename());
        emp.setPasswd(user.getPasswd());
        System.out.println("in Dao layer");
        Session s = getSession();
        Transaction tx = s.beginTransaction();
        s.save(emp);
        tx.commit();
        return null;
    }

    public List<Employee> fetchEmployees()
    {
        System.out.println("fetching employees");
        List<Employee> emplList = new ArrayList<>();
        Session s = getSession();
        Transaction tx = s.beginTransaction();
        List<EmployeeTable> employees = s.createCriteria(EmployeeTable.class).list();
        for (EmployeeTable emp : employees)
        {
            // System.out.println("emplid " + emp.getEmailid());
            Employee empl = new Employee();
            empl.setDesignation(emp.getDesignation());
            empl.setEmailid(emp.getEmailid());
            empl.setEmployeename(emp.getEmployeename());
            emplList.add(empl);
        }
        tx.commit();
        return emplList;
    }

    public void deleteEmployee(String emailIdToDelete)
    {
        System.out.println("deleting employee");
        Session s = getSession();
        Transaction tx = s.beginTransaction();
        Query query = s.createQuery("delete from EmployeeTable where emailid=:emailid");
        query.setParameter("emailid", emailIdToDelete);
        int deleted = query.executeUpdate();
        System.out.println("Deleted: " + deleted + " user(s)");
    }

    public void updateEmployee(Employee user)
    {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session
            .createQuery("update EmployeeTable set employeename=:employeename,designation=:designation where emailid=:emailid");
        query.setParameter("emailid", user.getEmailid());
        query.setParameter("employeename", user.getEmployeename());
        query.setParameter("designation", user.getDesignation());
        query.executeUpdate();
        tx.commit();
        session.close();
    }

    public void saveUser(User user)
    {
        Session s = getSession();
        Transaction tx = s.beginTransaction();
        s.save(user);
        tx.commit();
    }
}
