package glaciar.anotaciones;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.TYPE)  
public @interface PenguinEntity 
{
	String name() default PenguinConstants.DEFAULT_NAME;
	boolean ignore() default false;
}
