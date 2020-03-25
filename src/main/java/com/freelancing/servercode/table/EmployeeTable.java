package com.freelancing.servercode.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Employee")
@Entity
public class EmployeeTable
{
    @Id
    private String emailid;
    @Column(name = "name")
    private String employeename;
    @Column(name = "des")
    private String designation;
    @Column(name = "password")
    private String passwd;

    public String getEmailid()
    {
        return emailid;
    }

    public void setEmailid(String emailid)
    {
        this.emailid = emailid;
    }

    public String getEmployeename()
    {
        return employeename;
    }

    public void setEmployeename(String employeename)
    {
        this.employeename = employeename;
    }

    public String getDesignation()
    {
        return designation;
    }

    public void setDesignation(String designation)
    {
        this.designation = designation;
    }

    public String getPasswd()
    {
        return passwd;
    }

    public void setPasswd(String passwd)
    {
        this.passwd = passwd;
    }
}
