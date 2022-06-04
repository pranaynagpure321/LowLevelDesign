package com.example.geektrust;

import javax.jws.soap.SOAPBinding;
import java.sql.Struct;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Cource {

    String Instructer_name;
    String Course_name;
    String date;
    int min;
    int max;
    int count=0;
    boolean isAlloted=false;
    boolean isConfirmed = false;
    HashMap<String , UserRegistration> registrationHashMap = new HashMap<>();
    //List<UserRegistration> registrations = new LinkedList<>();

    public Cource(String course_name,String instructer_name, String date, int min, int max) {
        this.Instructer_name = instructer_name;
        this.Course_name = course_name;
        this.date = date;
        this.min = min;
        this.max = max;
    }

    public void addRegistration (String key, String username,String course, String instructer_name ,String email) {
        count+=1;
        UserRegistration userRegistration = new UserRegistration(username,course,instructer_name,email);
        registrationHashMap.put("REG-COURSE-"+username+"-"+course,userRegistration);
    }
}
