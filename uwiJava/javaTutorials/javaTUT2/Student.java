package Java.uwiJava.javaTutorials.javaTUT2;

import java.util.ArrayList;


public class Student {
    private String name;
    private School school; // add School object attribute to student
    private ArrayList<Course> courses; // add arrayList of courses
    
    // RETURN STUDENT NAME
    public String getName() {
        return
        name;
    }  

    //  STUDENT CONSTRUCTOR
    public Student(String name, School school ) {
        this.name = name;
        this.school = school;
        courses = new ArrayList<Course>();

    }


    // METHOD TO ADD NEW COURSES
    public void passCourse(String courseCode, int level, int numCredits) {
        courses.add(new Course(courseCode, level, numCredits));
    }
   
    // Create the method countCreditsForLevel(int level) 
    // that returns the number of credits earned by a student at
    // a certain level
    public int countCreditsForLevel(int level) {
        int creds = 0;

        for (Course course: courses) { // --> for each course in courses...
            if (course.getLevel() == level); 
                creds += course.getCredits();
        }

        return creds;
        


    }



}

