package com.example.jpaorigins;

import com.example.jpaorigins.entity.Student;
import com.example.jpaorigins.entity.StudentBuilder;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.stringnull.core.data.JPAOriginsCrudRepository;
import org.stringnull.core.data.JPAOriginsFactory;

@SpringBootApplication

//@StringnullFramework annatation for stringnull project

public class JPAOriginsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JPAOriginsApplication.class, args);
		Session session = HibernateConfig.getSessionFactory().openSession();
		Student s = new com.example.jpaorigins.entity.StudentBuilder()
			 .setName("Gene")
			 .setEmail("gene@gmail.com")
			 .build();
		System.out.println(s.getEmail());
//		Transaction transaction = session.beginTransaction();
//		Student s = new Student();
//		s.setName("Steve Mclaine");
//		s.setEmail("stevemclaine@gmail.com");
//		session.persist(s);
//		transaction.commit();

		Student getStudent = session.get(Student.class, 2);
		System.out.println(getStudent.getName());
		session.close();

		Student s1 = new StudentBuilder().setName("eugene").setEmail("myemail@gmail.com").build();
		System.out.println("get email: " + s1.getEmail());
		//TEST INVOCATION HERE
//		TestImpl testObj = new TestImpl();
//		TestInvocationHandler testInvocation = new TestInvocationHandler(testObj);
//
//		TestInterface proxyInstance = (TestInterface) Proxy.newProxyInstance(
//				testObj.getClass().getClassLoader(),
//				new Class<?>[]{TestInterface.class},
//				testInvocation
//		);
//
//		proxyInstance.update();
//		System.out.println(proxyInstance.get());

		//TEST JPAOrigins Framework
		//pass the entity manager
		JPAOriginsFactory factory = new JPAOriginsFactory();
		JPAOriginsCrudRepository<Student, Integer> studentRepository = factory.build(Student.class);
		studentRepository.findById(1);
		studentRepository.save();

	}

}
