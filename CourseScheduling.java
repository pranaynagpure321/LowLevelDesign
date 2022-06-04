package com.example.geektrust;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseScheduling {


   HashMap<String,Cource> courseOffering = new HashMap<String,Cource>();


    boolean validateInputForAddCource(String[] inputArr)
    {
        if(inputArr.length<6)
        {
            System.out.println("INPUT_DATA_ERROR");
            return false;
        }


        return true;
    }

    //add cource offering
    void addCourse(String field, String Instructer_name, String date,int min ,int max )
    {
        Cource course = new Cource(field, Instructer_name,date,min ,max);
        String CourceID= "OFFERING-"+field+"-"+Instructer_name;
        courseOffering.put(CourceID,course);
        System.out.println(CourceID);
    }


    //register for thr course

    boolean validateRegistrationInput(String[] inputArr)
    {
        if (inputArr.length<3 || !inputArr[1].contains("@"))
        {
            System.out.println("INPUT_DATA_ERROR");
            return false;
        }
        return true;
    }

    void registerForCourse(String email, String CourseKey)
    {

        if(courseOffering.containsKey(CourseKey))
        {
            Cource course = courseOffering.get(CourseKey);

            String name = email.split("@")[0];

            if(course.count<course.max)
            {
                String key = "REG-COURSE-"+name+"-"+course.Course_name+"-BOB";
                course.addRegistration(key,name,course.Course_name,course.Instructer_name,email);
                System.out.println(key+" ACCEPTED");
            }
            else {
                System.out.println("COURSE_FULL");
            }

            if (course.count>=course.min)
                course.isConfirmed = true;

        }
    }

    //cancel registration


    void cancelRegistration(String[] cancelStr )
    {

        if (cancelStr.length>=2 )
        {
            Cource course;
            String CourseName =cancelStr[1].split("-")[3];
            for(Map.Entry<String, Cource> entry: courseOffering.entrySet())
            {
                String key= entry.getKey();
                course = entry.getValue();

                if (course.Course_name.compareTo(CourseName)==0)
                {
                    if(!course.isAlloted)
                    {
                        course.count-=1;
                        course.registrationHashMap.remove(cancelStr[1]);
                        System.out.println( cancelStr[1]+" CANCEL_ACCEPTED");

                    }
                    else {

                        System.out.println( cancelStr[1]+" CANCEL_REJECTED");
                    }
                    break;
                }
            }

        }


    }


    //courceallotment
    void printAllotmentStatus(String[] inputStr)
    {
        //print registration no ,Emp Name, Email, Cource offering, Cource Name, Instructer Date, Final status
        if(courseOffering.containsKey(inputStr[1]))
        {
            Cource course = courseOffering.get(inputStr[1]);
            course.isAlloted = true;
            if(course.isConfirmed)
            for(Map.Entry<String, UserRegistration> entry: course.registrationHashMap.entrySet())
            {
                String key = entry.getKey();
                UserRegistration userReg = entry.getValue();
                System.out.println(key+" "+userReg.Email+" "+inputStr[1]+ " "+course.Course_name+" BOB "+course.date +" CONFIRMED" );

            }

            else
                System.out.println("COURSE_CANCELED");
        }

    }
}
