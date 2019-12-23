package ua.tarastom.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.tarastom.entity.*;

public class AddCoursesForMaryDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session currentSession = sessionFactory.getCurrentSession();
        try {
            // start a transaction
            currentSession.beginTransaction();

            // get the student mary from database
            int studentId = 2;
            Student tempStudent = currentSession.get(Student.class, studentId);

            System.out.println("\nLoaded student: " + tempStudent);
            System.out.println("Courses: " + tempStudent.getCourses());

            // create more courses
            Course tempCourse1 = new Course("Rubik's Cube - How to Speed Cube");
            Course tempCourse2 = new Course("Atari 2600 - Game Development");

            // add student to courses
            tempCourse1.addStudent(tempStudent);
            tempCourse2.addStudent(tempStudent);

            // save the courses
            System.out.println("\nSaving the courses ...");

            currentSession.save(tempCourse1);
            currentSession.save(tempCourse2);

            // commit transaction
            currentSession.getTransaction().commit();

            System.out.println("Done!");

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            currentSession.close();
            sessionFactory.close();
        }
    }
}
