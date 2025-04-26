package glaciar.penguintable;

public interface TableInputReader {
	
	public String[] createColumnNames() throws Exception;
	public Object[][] createMatrix() throws Exception;

}
