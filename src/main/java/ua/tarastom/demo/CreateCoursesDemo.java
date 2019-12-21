package ua.tarastom.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.tarastom.entity.Course;
import ua.tarastom.entity.Instructor;
import ua.tarastom.entity.InstructorDetail;

public class CreateCoursesDemo {
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
            Course course1 = new Course("Spring");
            Course course2 = new Course("JavaEE");
            Course course3 = new Course("Hibernate");
            Course course4 = new Course("MySQL");
            instructor.add(course1);
            instructor.add(course2);
            instructor.add(course3);
            instructor.add(course4);
            currentSession.save(course1);
            currentSession.save(course2);
            currentSession.save(course3);
            currentSession.save(course4);

            currentSession.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            currentSession.close();
            sessionFactory.close();
        }
    }
}
