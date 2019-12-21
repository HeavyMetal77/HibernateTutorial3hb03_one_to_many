package ua.tarastom.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.tarastom.entity.Course;
import ua.tarastom.entity.Instructor;
import ua.tarastom.entity.InstructorDetail;

public class CreateInstructorDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class).buildSessionFactory();

        Session currentSession = sessionFactory.getCurrentSession();
        try {
            Instructor instructor = new Instructor("Bob", "Doe", "bob@gmail.com");
            InstructorDetail instructorDetail = new InstructorDetail("bob.youtube", "coding");
            instructor.setInstructorDetail(instructorDetail);
            currentSession.beginTransaction();
            currentSession.save(instructor);
            currentSession.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            currentSession.close();
            sessionFactory.close();
        }
    }
}
