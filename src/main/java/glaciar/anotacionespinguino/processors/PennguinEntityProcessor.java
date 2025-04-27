package glaciar.anotacionespinguino.processors;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import glaciar.ReflexivePenguin;
import glaciar.anotacionespinguino.PenguinConstants;
import glaciar.anotacionespinguino.PenguinEntity;

public class PennguinEntityProcessor extends PenguinAnnotationProcessor{
	
	private Object obj;
	private PenguinEntity annotation;
	
	public PennguinEntityProcessor(Object obj) {
		if(obj == null) {
			obj = PenguinConstants.NULL_PENGUIN;
		}
		this.obj = obj;
		this.annotation = obj.getClass().getAnnotation(PenguinEntity.class);
	}

	@Override
	public String getName() {		
		if(annotation != null && !annotation.name().equals(PenguinConstants.DEFAULT_NAME)) {
			return annotation.name();
		}		
		return obj.getClass().getSimpleName();
	}

	@Override
	public boolean isAnnotationPresent() {
		if(annotation != null) {
			return true;
		}		
		return false;
	}
	
	public Object getPenguinKey() {
		if(obj==null || !isAnnotationPresent()) {
			return obj;
		}
		Field[] fields = obj.getClass().getDeclaredFields();
		
		Optional<Object> penguinKey = Arrays.stream(fields).filter(f->{
			PenguinAttributeProcessor processor = new PenguinAttributeProcessor(f);
			return processor.isPenguinKey();
			}).map(f->{		
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
