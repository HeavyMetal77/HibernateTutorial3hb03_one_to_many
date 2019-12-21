package ua.tarastom.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.tarastom.entity.Course;
import ua.tarastom.entity.Instructor;
import ua.tarastom.entity.InstructorDetail;

public class GetInstructorCoursesDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class).buildSessionFactory();

        Session currentSession = sessionFactory.getCurrentSession();
        try {

            currentSession.beginTransaction();
            int theId = 1;
            Instructor instructor = currentSession.get(Instructor.class, theId);
            System.out.println("Instructor: " + instructor);

            if (instructor != null) {
                System.out.println("His courses: " + instructor.getCourses());
            } else {
                System.out.println("Instructor " + theId + " is null");
            }
            currentSession.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            currentSession.close();
            sessionFactory.close();
        }
    }
}
