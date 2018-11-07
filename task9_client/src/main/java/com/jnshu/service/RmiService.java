package com.jnshu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by Maibenben on 2018/11/3.
 */
@Service
public class RmiService {
    @Qualifier("studentRMIClientOne")
    @Autowired
    StudentInterface studentServiceOne;

    @Qualifier("courseRMIClientOne")
    @Autowired
    CourseInterface courseServiceOne;

    @Qualifier("userRMIClientOne")
    @Autowired
    UserInterface userServiceOne;

    @Qualifier("studentRMIClientTwo")
    @Autowired
    StudentInterface studentServiceTwo;

    @Qualifier("courseRMIClientTwo")
    @Autowired
    CourseInterface courseServiceTwo;

    @Qualifier("userRMIClientTwo")
    @Autowired
    UserInterface userServiceTwo;

    private Logger log = LoggerFactory.getLogger(RmiService.class);
    public StudentInterface getStudentInterface(){

        if (getRandom()==0) {
         try {
                studentServiceOne.count();
            } catch (Exception e) {

                e.printStackTrace();
             return  studentServiceTwo;
            }
            return studentServiceOne;
        } else {

            try {
                studentServiceTwo.count();
            } catch (Exception e) {

                e.printStackTrace();
                return studentServiceOne;
            }
            return  studentServiceTwo;
        }

    }

    public CourseInterface getCourseInterface(){


        if (getRandom()==0) {
            try {
                courseServiceOne.select();
            } catch (Exception e) {

                e.printStackTrace();
                return  courseServiceTwo;
            }
            return courseServiceOne;
        } else {

            try {
                courseServiceTwo.select();
            } catch (Exception e) {

                e.printStackTrace();
                return courseServiceOne;
            }
            return  courseServiceTwo;
        }



    }
    public UserInterface getUserInterface(){

        if (getRandom()==0) {
            try {
                userServiceOne.select();
            } catch (Exception e) {

                e.printStackTrace();
                return  userServiceTwo;
            }
            return userServiceOne;
        } else {

            try {
                userServiceTwo.select();
            } catch (Exception e) {

                e.printStackTrace();
                return  userServiceOne;
            }
            return  userServiceTwo;
        }

    }

    public static int getRandom(){

        return  (new Random()).nextInt(2);
    }

//    public static void main(String[] args) {
//        System.out.println(getRandom());
//    }
}
