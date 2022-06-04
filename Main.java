package com.example.geektrust;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {




        //Sample code to read from file passed as command line argument
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis);
            CourseScheduling cs = new CourseScheduling();
            // returns true if there is another line to read
            while (sc.hasNextLine()) {

                String[] inputStr= sc.nextLine().split(" ");


                if(inputStr[0].compareTo("ADD-COURSE-OFFERING")==0)
                {
                    if(cs.validateInputForAddCource(inputStr)) {

                        String course = inputStr[1];
                        String name = inputStr[2];
                        String date = inputStr[3];
                        int min= Integer.parseInt(inputStr[4]);
                        int max= Integer.parseInt(inputStr[5]);
                        cs.addCourse(course,name,date,min,max);
                    }
                }

                else if(inputStr[0].compareTo("REGISTER")==0)
                {
                    if(cs.validateRegistrationInput(inputStr))
                    {
                        cs.registerForCourse(inputStr[1],inputStr[2]);
                    }
                }
                else if(inputStr[0].compareTo("CANCEL")==0)
                {
                    cs.cancelRegistration(inputStr);
                }
                else if(inputStr[0].compareTo("ALLOT")==0)
                {
                    cs.printAllotmentStatus(inputStr);
                }


            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
        }

    }
}
