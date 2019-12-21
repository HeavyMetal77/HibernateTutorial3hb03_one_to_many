package ua.tarastom.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.tarastom.entity.Course;
import ua.tarastom.entity.Instructor;
import ua.tarastom.entity.InstructorDetail;
import ua.tarastom.entity.Review;

public class CreateCourseAndReviewsDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        Session currentSession = sessionFactory.getCurrentSession();
        try {

            currentSession.beginTransaction();

            Course course1 = new Course("Spring");
            course1.addReview(new Review("Great spring!"));
            course1.addReview(new Review("Best spring!"));
            course1.addReview(new Review("Love spring!"));
            currentSession.save(course1);

            currentSession.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            currentSession.close();
            sessionFactory.close();
        }
    }
}
