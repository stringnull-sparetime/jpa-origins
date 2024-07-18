# 🦖 
<b>jpaorigins-stringnull-variation</b>.
This project is my Brother's challenge to me. Lets say that I am living at the age of dinosaur, around 200BC(Before Cornedbeef) where then I need to create my OWN persistence framework without the aid of <b>Spring Data JPA.</b>



###### Core

    JPAOriginsCRUDRepository<T, ID>
    - use this class for standard operation 'findById' 'save' 'delete' 'update'

Example Usage

    JPAOriginsFactory factory = new JPAOriginsFactory();
    JPAOriginsCrudRepository<Student, Integer> studentRepository = factory.build(Student.class);
    Student s = studentRepository.findById(1);


###### Processors 
<pre>
@BuilderProperty
- this annotation generates a BuilderPattern for your entity.
</pre>


###### jpaorigin-usage setup
+ add my custom dependency

```html
<dependency>
	 <groupId>org.stringnull</groupId>
	 <artifactId>jpaorigins-stringnull-variation</artifactId>
	 <version>1.0-SNAPSHOT</version>
</dependency>
```
+ PostgreSQL
  + table name: xjpa-test
  + added sequence: CREATE SEQUENCE hibernate_sequence START 0;

 Protest? this is not a full-pledge framework. These demonstrate advance technique such as invocation handling, reflection, annotation processing and query generation.