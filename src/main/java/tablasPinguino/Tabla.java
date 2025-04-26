package tablasPinguino;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Tabla {

	private String[] columnNames;
	private Object[][] matrix;
	private int columnMargin = 3;
	private int[] spaces;
	
	public Tabla(String[] columnNames, Object[][] matrix)
	{
		try {
			this.columnNames = columnNames;
			this.matrix = matrix;
			createSpaces();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Tabla(Object[] objects)
	{
		if(objects.length > 0)
		{			
			try {
				System.out.println("\nCREANDO TABLA USANDO ARRAY DE OBJETOS COMO PARÁMETRO");
				createColumnNames(objects[0]);
				createMatrix(objects);
				createSpaces();
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}			
		}
		else {			
			throw new ErrorDeTabla();
		}		
	}	
	public Tabla(ResultSet rs) throws SQLException
	{
		System.out.println("\nCREANDO TABLA USANDO RS");
		createColumnNames(rs);
		createMatrix(rs);
		createSpaces();
	}
	
	private void createSpaces()
	{
		int maxLenght;
		spaces = new int[columnNames.length];
		
		for (int col = 0;col<columnNames.length;col++)
		{
			maxLenght = (columnNames[col].length())+columnMargin*2;
			
			for (int row = 0;row<matrix.length;row++)
			{
				if(matrix[row][col].toString().length() > maxLenght - columnMargin*2)
				{
					maxLenght = (matrix[row][col].toString().length())+columnMargin*2;
				}
			}
			spaces[col] = maxLenght;
		}
		
	}
	
	// ------------------------------------- CREATE MATRIX -------------------------------------
	
	private void createMatrix(Object[] objects) throws IllegalArgumentException, IllegalAccessException 
	{			
		int row = 0;
		matrix = new Object[objects.length][columnNames.length];
		for (Object obj:objects)
		{
			int col = 0;
			Field[] campos = obj.getClass().getDeclaredFields();
			for(Field campo:campos)
			{
				campo.setAccessible(true); // Hacer accesible el campo (incluso si es privado)
				String nombreCampo = campo.getName();				
				Object valorCampo = campo.get(obj);
				matrix[row][col] = valorCampo;		
				//System.out.println("Nombre del campo: " + nombreCampo + ", Valor: " + valorCampo);
				campo.setAccessible(false);
				col++;
			}
			row++;
		}		
	}
	
	private void createMatrix(ResultSet rs) throws SQLException
	{
		ResultSetMetaData rsmd = rs.getMetaData();
        int nCol = rsmd.getColumnCount();
        List<Object[]> rows = new ArrayList();
        while(rs.next())
        {
        	Object[] row = new Object[nCol];
        	
        	for(int col = 1; col<=nCol;col++)
        	{
        		Object value = rs.getObject(col);
        		row[col-1] = value;
        	}
        	rows.add(row);
        }
        
        matrix = new Object[rows.size()][nCol];
        for (int r = 0; r < rows.size(); r++) {
            matrix[r] = rows.get(r);
        }        
	}

	// ------------------------------------- CREATE COLUMN NAMES ------------------------------------- 
	
	private void createColumnNames(Object object) {
		Field[] campos = object.getClass().getDeclaredFields();
		columnNames = new String[campos.length];
		int i = 0;
		for(Field campo:campos)
		{
			campo.setAccessible(true); // Hacer accesible el campo (incluso si es privado)
			String nombreCampo = campo.getName();
			columnNames[i] = nombreCampo;
			i++;
			campo.setAccessible(false);
		}
	}
	private void createColumnNames(ResultSet rs) throws SQLException
	{
		ResultSetMetaData rsmd = rs.getMetaData();
		int nCol = rsmd .getColumnCount();
		columnNames = new String[nCol];
		
		for(int col = 1; col<=nCol ;col++)
		{
			columnNames[col-1] = rsmd.getColumnName(col).toUpperCase();
		}
	}

	// ------------------------------------------- SYSO ------------------------------------------- 
	
	public void sysoTable()
	{			
		sysoHead();
		sysoBody(); //TODO: Obtener el max lenght de la matrix para poder imprimirla segun su tamano
	}
	
	private void sysoHead()
	{
		StringBuilder top = new StringBuilder("┌");
		StringBuilder mid = new StringBuilder("│");
		StringBuilder bot = new StringBuilder("├");
		int i = 0;
		for (String name: columnNames) {
			top.append(StringsParaTablas.addLine("─",(spaces[i]))).append("┬");
			mid.append(StringsParaTablas.addSpacesToString(name.toUpperCase(), spaces[i])).append("│");
			bot.append(StringsParaTablas.addLine("─",(spaces[i]))).append("┼");
			i++;
		}
		System.out.println(top.toString());
		System.out.println(mid.toString());
		System.out.println(bot.toString());
	}
	
	private void sysoBody()
	{		
		StringBuilder general = new StringBuilder();			
		StringBuilder mid = new StringBuilder("│");
		StringBuilder bot = new StringBuilder("├");
		
		for(int row = 0; row < matrix.length;row++)
		{
			mid = new StringBuilder("│");
			bot = new StringBuilder("├");
			
			for(int col = 0; col < matrix[row].length;col++)
			{
				String value = matrix[row][col].toString();
				mid.append(StringsParaTablas.addSpacesToString(value, spaces[col])).append("│");
				bot.append(StringsParaTablas.addLine("─",(spaces[col]))).append("┼");
			}
			
			general.append(mid).append("\n").append(bot).append("\n");
		}
		
		System.out.println(general.toString());            
	}
	
	public void setMargin(int margin)
	{
		columnMargin = margin;
	}

}

//------------------------------------------- ERROR ------------------------------------------- 

class ErrorDeTabla extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	private final String mensaje = "El tamño de la array debe de ser de 1 por lo menos.";
	
	public ErrorDeTabla() {}
	public String getMensaje()
	{
		return mensaje;
	}
}
