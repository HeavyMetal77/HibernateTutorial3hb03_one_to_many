package ua.tarastom.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.tarastom.entity.Course;
import ua.tarastom.entity.Instructor;
import ua.tarastom.entity.InstructorDetail;

public class EagerLazyDemo {
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
            currentSession.getTransaction().commit();

            // close the session
            currentSession.close();
            System.out.println("Courses: " + instructor.getCourses());
            //если instructor.getCourses() был вызван после закрытия сессии,
            //то при fetch = FetchType.EAGER - исключения не будет
            //FetchType.LAZY - будет

            //если instructor.getCourses() был вызван до закрытия сессии,
            //то вне зависимости от fetch = FetchType.EAGER или LAZY исключения не будет

            System.out.println("\nluv2code: The session is now closed!\n");

            // option 1: call getter method while session is open

            // get courses for the instructor
            System.out.println("luv2code: Courses: " + instructor.getCourses());

            System.out.println("luv2code: Done!");

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            currentSession.close();
            sessionFactory.close();
        }
    }
}
