package com.lc.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lc.hibernate.demo.entity.Instructor;
import com.lc.hibernate.demo.entity.InstructorDetail;
import com.lc.hibernate.demo.entity.Student;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {		
			
			//start a transaction
			session.beginTransaction();
			
			//get instructor details object
			int theId = 3;
			InstructorDetail tempInstructorDetail = 
					session.get(InstructorDetail.class, theId);
			
			//print instructor detail
			System.out.println(tempInstructorDetail);
			
			//print instructor
			System.out.println(tempInstructorDetail.getInstructor());
			
			//remove the associated oject reference
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			
			//delete instructor detail
			session.delete(tempInstructorDetail);
			
			//commit transaction
			session.getTransaction().commit();;
			System.out.println("Done");
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}
