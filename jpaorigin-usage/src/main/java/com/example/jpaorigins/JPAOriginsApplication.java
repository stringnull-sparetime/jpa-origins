package com.example.jpaorigins;


import com.example.jpaorigins.entity.Student;
import org.stringnull.core.StringnullFramework;
import org.stringnull.core.data.JPAOriginsCrudRepository;
import org.stringnull.core.data.JPAOriginsFactory;

public class JPAOriginsApplication {

	public static void main(String[] args) {

		StringnullFramework.build(JPAOriginsApplication.class);

		//TEST JPAOrigins Framework
//		JPAOriginsFactory factory = new JPAOriginsFactory();
//		JPAOriginsCrudRepository<Student, Integer> studentRepository = factory.build(Student.class);
//		studentRepository.findAll();
//		studentRepository.findById(4);
		//studentRepository.save();

	}

}
