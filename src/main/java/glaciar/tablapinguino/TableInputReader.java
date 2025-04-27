package glaciar.tablapinguino;

public interface TableInputReader {
	
	public String[] createColumnNames() throws Exception;
	public Object[][] createMatrix() throws Exception;
	public String getTableName();

}
