package com.shrewd.develop.updates2k19.Model;

import com.shrewd.develop.updates2k19.Utilities.CS;

import java.util.HashMap;
import java.util.Map;

public class User {
    public String college = "college";
    public String department;
    public String email = "email";
    public String enrolment = "enrolment";
    public String firstName = "first_name";
    public String lastName = "last_name";
    public int gender;
    public String mobileNo = "mobile";
    public int userTypeId;
    public int year;

    public User(String college, String department, String email, String enrolment, String firstName, String lastName,
                int gender, String mobileNo, int userTypeId, int year) {
        this.college = college;
        this.department = department;
        this.email = email;
        this.enrolment = enrolment;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.mobileNo = mobileNo;
        this.userTypeId = userTypeId;
        this.year = year;
    }

    public Map<String,Object> getHashmap() {
        Map<String, Object> data = new HashMap<>();
        data.put(CS.college, college);
        data.put(CS.department, department);
        data.put(CS.enrolment, enrolment);
        data.put(CS.firstName, firstName);
        data.put(CS.lastName, lastName);
        data.put(CS.mobileNo, mobileNo);
        data.put(CS.year, year);
        data.put(CS.gender, gender);
        data.put(CS.userTypeId, userTypeId);
        data.put(CS.email, email);
        return data;
    }
}
