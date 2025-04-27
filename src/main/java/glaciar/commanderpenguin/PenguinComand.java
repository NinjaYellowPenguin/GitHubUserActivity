package glaciar.commanderpenguin;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PenguinComand {
	String value();
}
