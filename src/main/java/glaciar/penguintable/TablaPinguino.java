package glaciar.penguintable;

import java.sql.ResultSet;

public class TablaPinguino {

	private String[] columnNames;
	private Object[][] matrix;
	private int columnMargin = 3;
	private int[] spaces;
	
	private TableInputReader reader;
	
	public TablaPinguino(String[] columnNames, Object[][] matrix)
	{
		try {
			this.columnNames = columnNames;
			this.matrix = matrix;
			createSpaces();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public TablaPinguino(Object[] objects)
	{
		reader = new TableObjectReader(objects);		
		try {
			matrix = reader.createMatrix();
			columnNames = reader.createColumnNames();
		} catch (Exception e) {
			String msg = "Error al construir la tabla: " + e.getMessage();
			throw new ErrorDeTabla(msg);
		}		
		createSpaces();
	}	
	public TablaPinguino(ResultSet rs)
	{
		reader = new TableResultSetReader(rs);
		
		try {
			columnNames = reader.createColumnNames();
			matrix = reader.createMatrix();
		} catch (Exception e) {
			String msg = "Error al construir la tabla: " + e.getMessage();
			throw new ErrorDeTabla(msg);
		}
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


	// ------------------------------------------- SYSO ------------------------------------------- 
	
	public void sysoTable()
	{			
		sysoHead();
		sysoBody();
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
	private String mensaje = "El tamño de la array debe de ser de 1 por lo menos.";
	
	public ErrorDeTabla() {}
	public ErrorDeTabla(String msg) {mensaje = msg;}
	public String getMensaje()
	{
		return mensaje;
	}
}
