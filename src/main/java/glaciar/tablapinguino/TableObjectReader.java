package glaciar.tablapinguino;

import java.lang.reflect.Field;

import glaciar.ReflexivePenguin;
import glaciar.anotaciones.PenguinConstants;
import glaciar.anotaciones.processors.PenguinAttributeProcessor;
import glaciar.anotaciones.processors.PennguinEntityProcessor;

public class TableObjectReader implements TableInputReader{
	
	private Object[] objects;
	
	public TableObjectReader(Object[] objects) {
		this.objects = objects;
	}
	
	private int getColNamesLenght() {
		int len = 0;
		if(objects != null) {
			Object object = objects[0];
			len = object.getClass().getDeclaredFields().length;
		}
		return len;
	}

	@Override
	public String[] createColumnNames() {
		String[] columnNames = new String[0];
		if(objects != null) {
			Object object = objects[0];
			Field[] fields = object.getClass().getDeclaredFields();
			columnNames = new String[fields.length];
			int i = 0;
			for(Field field:fields)
			{
				PenguinAttributeProcessor processor = new PenguinAttributeProcessor(field);
				String nombreCampo = processor.getName();				
				columnNames[i] = nombreCampo;
				i++;
			}
		}
		return columnNames;		
	}

	@Override
	public Object[][] createMatrix() throws Exception {
		int row = 0;
		Object[][] matrix = new Object[objects.length][getColNamesLenght()];
		for (Object obj:objects)
		{
			int col = 0;
			Field[] fields = obj.getClass().getDeclaredFields();
			for(Field field:fields)
			{			
				Object valorCampo;
				valorCampo = ReflexivePenguin.getFieldValue(field, obj);
				if(valorCampo == null) {
					valorCampo = PenguinConstants.NULL_PENGUIN;
				}
				matrix[row][col] = valorCampo;			
				col++;
			}
			row++;
		}	
		return matrix;
	}

	@Override
	public String getTableName() {
		if(objects != null) {
			Object object = objects[0];
			PennguinEntityProcessor processor = new PennguinEntityProcessor(object);
			return processor.getName();
			}
		return "N/A";
	}

}
