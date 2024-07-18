package org.stringnull.utils.builder;

import java.lang.annotation.*;

/**
 * Indicates that the annotated method should be processed by the {@link BuilderPropertyProcessor }.
 * This annotation mark a method for entity that will generate a builder pattern for you.
 * <p></pre>
 * <p>Example Usage</p>
 * <pre>
 * {@code
 *
 * public class Person {
 *     String name;
 *     String email;
 *
 *     @BuilderProperty
 *     public void setName(String namej ) {
 *        this.name = name;
 *     }
 *     @BuilderProperty
 *     public void setEmail(String email){
 *         this.email = email;
 *     }
 * }
 * }
 * </pre>
 * <i>Generated builder class: PersonBuilder.java
 * <pre>
 * {@code
 * Person p = new PersonBuilder()
 * .setName("Eugene Baniaga")
 * .setEmail("myemail@gmail.com")
 * .build();
 * }
 * </pre>
 * </i>
 *
 * @see BuilderPropertyProcessor
 * @author stringnull | baniaga.eugene@gmail.com
 *
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface BuilderProperty {
}
