package com.example.jpaorigins;


import com.example.jpaorigins.entity.Student;
import com.example.jpaorigins.entity.StudentBuilder;
import org.stringnull.core.data.JPAOriginsCrudRepository;
import org.stringnull.core.data.JPAOriginsFactory;
import org.stringnull.core.FrameworkConfig;
//import org.stringnull.core.dbmanager.StringnullFramework;

//@StringnullFramework annatation for stringnull project
//@SpringBootApplication
//@StringnullFramework
public class JPAOriginsApplication {

	public static void main(String[] args) {

		new FrameworkConfig().build(JPAOriginsApplication.class);

//		Student s = new com.example.jpaorigins.entity.StudentBuilder()
//			 .setName("Gene")
//			 .setEmail("gene@gmail.com")
//			 .build();
//		System.out.println(s.getEmail());

//		Student s1 = new StudentBuilder().setName("eugene").setEmail("myemail@gmail.com").build();
//		System.out.println("get email: " + s1.getEmail());
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
