package glaciar.tablapinguino;

import java.sql.ResultSet;
import java.util.List;

import glaciar.anotaciones.PenguinNull;
import glaciar.anotaciones.processors.PennguinEntityProcessor;


public class TablaPinguino {
	
	private String tableName;

	private String[] columnNames;
	private Object[][] matrix;
	private int columnMargin = 3;
	private int[] spaces;
	private List<TablaPinguino> subTablas;
	
	public TablaPinguino(Object[] objects)
	{
		this(new TableObjectReader(objects));		
	}	
	public TablaPinguino(ResultSet rs)
	{
		this(new TableResultSetReader(rs));
	}
	
	private TablaPinguino(TableInputReader reader) {
		try {
			tableName = reader.getTableName();
			matrix = reader.createMatrix();
			columnNames = reader.createColumnNames();
		} catch (Exception e) {
			e.printStackTrace();
			String msg = "Error al construir la tabla: " + e.getMessage();
			throw new ErrorDeTabla(msg + e);
		}
		subTablas = createSubTabla();
		createSpaces();
	}
	
	private List<TablaPinguino> createSubTabla() {
		// Itera la matrix en busca de PenguinEntities
		TablaPinguinoBuilder tablaDePinguinosBuilder = new TablaPinguinoBuilder();
		for(int col=0; col<matrix.length; col++) {
			for (int row = 0; row < matrix[col].length; row++) {
				Object value = matrix[col][row];
				PennguinEntityProcessor processor = new PennguinEntityProcessor(value);
				matrix[col][row] = processor.getPenguinKey();
				if(matrix[col][row] != value && !(value instanceof PenguinNull)) {
					tablaDePinguinosBuilder.addObject(value);
				}				
			}					
		}
		return tablaDePinguinosBuilder.build();
		
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
				//se ha anadido un valor por defecto si es null
				if(matrix[row][col] == null) {
					matrix[row][col] = "N/A";
				}
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
		
		sysoSubTablas();
	}
	
	private void sysoSubTablas() {
		subTablas.stream().forEach(tabla->tabla.sysoTable());		
	}

	private void sysoHead()
	{
		StringBuilder head = new StringBuilder("─── ").append(tableName).append(" ───").append("\n");
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
		System.out.println(head.toString());
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
				Object obj = matrix[row][col];
				String value = null;
				
				value = matrix[row][col].toString();
				
				
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
