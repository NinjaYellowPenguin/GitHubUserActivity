package glaciar.anotaciones;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import glaciar.ReflexivePenguin;

public class PenguinAnnotationReader {
	
	private static boolean isPenguinEntity(Class<?> clazz) {		
		PenguinEntity annotation = clazz.getAnnotation(PenguinEntity.class);
		if(annotation != null)
		{	
			return true;			
		}
		return false;		
	}
	public static boolean isPenguinEntity(Object obj) {		
		return isPenguinEntity(obj.getClass());			
	}
	
	private static boolean isPenguinAttribute(Field field) {		
		PenguinAttribute annotation = field.getAnnotation(PenguinAttribute.class);
		if(annotation != null)
		{	
			return true;			
		}
		return false;		
	}
	private static boolean isPenguinKey(Field field) {	
		PenguinAttribute annotation = field.getAnnotation(PenguinAttribute.class);
		if(annotation != null && annotation.penguinKey())
		{	
			return true;			
		}
		return false;		
	}
	
	public static String getEntityName(Class<?> clazz) {		
		if(isPenguinEntity(clazz)) {
			String name = clazz.getAnnotation(PenguinEntity.class).name();
			if(!name.equals("")) {
				return name;
			}			
		}		
		return clazz.getTypeName();
	}
	
	public static String getFieldName(Field field) {
		if(isPenguinAttribute(field)) {
			String name = field.getAnnotation(PenguinAttribute.class).name();
			if(!name.equals("")) {
				return name;
			}	
		}
		return field.getName();
	}
	
	public static Object getPenguinKey(Object obj) {
		if(obj==null || !isPenguinEntity(obj.getClass())) {
			return obj;
		}
		Field[] fields = obj.getClass().getDeclaredFields();
		Optional<?> penguinKey = Arrays.stream(fields).filter(field->PenguinAnnotationReader.isPenguinKey(field)).map(f->{
			try {
				return ReflexivePenguin.getFieldValue(f, obj);
			} catch (Exception e) {
				return null;
			}
		}).filter(Objects::nonNull).findFirst();
		if(penguinKey.isEmpty()) {
			return "N/A";
		}
		return penguinKey.get();
	}

}
