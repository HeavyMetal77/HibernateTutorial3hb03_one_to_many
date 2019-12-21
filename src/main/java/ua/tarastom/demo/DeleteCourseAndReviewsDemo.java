package ua.tarastom.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.tarastom.entity.Course;
import ua.tarastom.entity.Instructor;
import ua.tarastom.entity.InstructorDetail;
import ua.tarastom.entity.Review;

import java.util.List;

public class DeleteCourseAndReviewsDemo {
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

            int theId = 10;
            Course course = currentSession.get(Course.class, theId);
            List<Review> reviews = course.getReviews();
            System.out.println(course);
            System.out.println(reviews);
            currentSession.delete(course);

            currentSession.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            currentSession.close();
            sessionFactory.close();
        }
    }
}
