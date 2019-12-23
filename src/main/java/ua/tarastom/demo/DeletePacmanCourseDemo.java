package ua.tarastom.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.tarastom.entity.*;

public class DeletePacmanCourseDemo {
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
            int theIdCourse = 10;

            Course coursePacman = currentSession.get(Course.class, theIdCourse);
            currentSession.delete(coursePacman);

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
