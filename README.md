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


Repository

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

Protest? this is not a full-pledge framework. These demonstrate advance technique such as invocation handling, reflection, annotation processing and query generation.

