package com.example.jpaorgins;

import com.example.jpaorgins.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JPAOriginsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JPAOriginsApplication.class, args);
		Session session = HibernateConfig.getSessionFactory().openSession();

		Transaction transaction = session.beginTransaction();
		Student s = new Student();
		s.setName("Steve Mclaine");
		s.setEmail("stevemclaine@gmail.com");
		session.persist(s);
		transaction.commit();
		session.close();
	}

}
