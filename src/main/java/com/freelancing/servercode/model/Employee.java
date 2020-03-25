package com.freelancing.servercode.model;

public class Employee
{
    private String employeename;
    private String emailid;
    private String designation;
    private String passwd;

    public String getEmployeename()
    {
        return employeename;
    }

    public void setEmployeename(String employeename)
    {
        this.employeename = employeename;
    }

    public String getEmailid()
    {
        return emailid;
    }

    public void setEmailid(String emailid)
    {
        this.emailid = emailid;
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