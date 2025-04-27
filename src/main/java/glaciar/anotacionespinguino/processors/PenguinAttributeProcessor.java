package glaciar.anotacionespinguino.processors;

import java.lang.reflect.Field;

import glaciar.anotacionespinguino.PenguinAttribute;
import glaciar.anotacionespinguino.PenguinConstants;

public class PenguinAttributeProcessor extends PenguinAnnotationProcessor{
	
	private Field field;
	public PenguinAttribute annotation;
	
	public PenguinAttributeProcessor(Field field) {
		this.field = field;
		annotation = field.getAnnotation(PenguinAttribute.class);
	}

	@Override
	public String getName() {
		if(isAnnotationPresent() && !annotation.name().equals(PenguinConstants.DEFAULT_NAME)) {
			return annotation.name();
		}
		return field.getName();
	}

	@Override
	public boolean isAnnotationPresent() {
		if(annotation != null) {
			return true;
		}
		return false;
	}
	
	boolean isPenguinKey()
	{	
		if(annotation != null && annotation.penguinKey())
		{	
			return true;			
		}
		return false;		
	}
	
	
	
	

}
