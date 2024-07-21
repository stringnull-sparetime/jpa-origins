# 🦖 
<b>jpaorigins-stringnull-variation</b>.
This project is my Brother's challenge to me. Lets say that I am living at the age of dinosaur, around 200BC(Before Cornedbeef) where then I need to create my OWN persistence framework without the aid of <b>Spring Data JPA.</b>



###### Getting Started 

    //add dependency to your pom.xml
    <dependency>
         <groupId>org.stringnull</groupId>
         <artifactId>jpaorigins-stringnull-variation</artifactId>
         <version>1.0-SNAPSHOT</version>
    </dependency>

<p></p>

    //Create file "stringnull.properties under root/Resource/
    jpaorigins.url=jdbc:postgresql://localhost:5432/dbname
    jpaorigins.username=dbusername
    jpaorigins.password=dbpassword

<p></p>

    //Run our framework from your main application. 
    StringnullFramework.build(YourProjectMain.class)

    //This will automatically build your database configuration(limited to PostgreSQL).
    //Resource file stringnull.properties load the neccessary fields 
    //to a connector class org.stringnull.core.database.DatabaseManager

    ---------------------------------------------------------------------------
    OUTPUT
    ---------------------------------------------------------------------------
    🆂🆃🆁🅸🅽🅶🅽🆄🅻🅻 - 🅵🆁🅰🅼🅴🆆🅾🆁🅺
    :::::: 🦖 ::::::   obtaining PostgreSQL configuration properties
    :::::: 🦖 ::::::   database url jdbc:postgresql://localhost:5432/xjpa-test
    :::::: 🦖 ::::::   starting connection ....
    :::::: 🦖 ::::::   database connection open
    :::::: 🦖 ::::::   Successfully connected!


###### Setting up Entity
    //add this to the stringnull.properties
    //this will drop and create schema
    jpaorigins.schema=auto-create

    //From your entity class e.g Student 
    //add our annotation our framework will generate schema for us. 
    
    @JPAOriginsTable(name = "Students") //responsible for handling table properties
    public class Student {
        @JPAOriginsID //determining the primary key also added as SERIAL (for now)
        @JPAOriginsColumn(name = "id") // dont forget to add this annotation which allow the framework to evaluate and generate valid table column and type.
        private int id;

        @JPAOriginsColumn()
        private String name;
    }

    ---------------------------------------------------------------------------
    OUTPUT
    ---------------------------------------------------------------------------
    🆂🆃🆁🅸🅽🅶🅽🆄🅻🅻 - 🅵🆁🅰🅼🅴🆆🅾🆁🅺
    :::::: 🦖 ::::::   obtaining PostgreSQL configuration properties
    :::::: 🦖 ::::::   database url jdbc:postgresql://localhost:5432/xjpa-test
    :::::: 🦖 ::::::   startitng connection ....
    :::::: 🦖 ::::::   database connection open
    :::::: 🦖 ::::::   Successfully connected!
    :::::: 🦖 ::::::   starting schema creator...
    :::::: 🦖 ::::::   ✔️table dropped -> Students
    :::::: 🦖 ::::::   ✔️created table -> CREATE TABLE Students( id SERIAL PRIMARY KEY, name VARCHAR);


###### Using repository

    JPAOriginsCRUDRepository<T, ID>
    - use this class for standard operation 'findById' 'save' 'delete' 'update'

Example Usage

    JPAOriginsFactory factory = new JPAOriginsFactory();
    JPAOriginsCrudRepository<Student, Integer> studentRepository = factory.build(Student.class);
    Student s = studentRepository.findById(1);


###### Processors 
Utility that automatically created builder pattern to our entity. 
<pre>
@BuilderProperty
- this annotation generates a BuilderPattern for your entity.
</pre>

Protest? this is not a full-pledge framework. These demonstrate advance technique such as dynamic proxy, invocation handling, reflection, annotation processing and query generation.

