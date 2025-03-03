import java.util.ArrayList;

public class Student {
    private String name;
    private School school; // reference Student to a school;
    private ArrayList<Course> courses; // student has a list  of Courses
    

    // Constructor:
    public Student(String name, School school) {
        this.name = name;
        this.school = school;
        this.courses = new ArrayList<Course>(); // 
        }
    
        public void addCourse(Course course) {  // add course to list of courses
            this.courses.add(course); 
        }

        public void passCourse(String courseCode, int numCredits, int level) {  
            this.courses.add(new Course(courseCode, level, numCredits)); 
        }
        
        public int countCreditsForLevel(int level) {
            int totalCredits = 0;
            for (Course course: this.courses){          // refer to specific instances
                if (course.getLevel() == level) {
                    totalCredits += course.getCredits();
                }
                
            } 
            return totalCredits;
        }

        public ArrayList<Course> getCourses() {
            return courses;
        }


    public String getName() {
        return name;
    }
}
