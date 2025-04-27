package glaciar.tablapinguino;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class TableResultSetReader implements TableInputReader{
	
	private ResultSet rs;
	
	public TableResultSetReader(ResultSet rs) {
		this.rs = rs;
		
	}

	@Override
	public String[] createColumnNames() throws Exception {
		String[] columnNames;
		ResultSetMetaData rsmd = rs.getMetaData();
		int nCol = rsmd .getColumnCount();
		columnNames = new String[nCol];
		
		for(int col = 1; col<=nCol ;col++)
		{
			columnNames[col-1] = rsmd.getColumnName(col).toUpperCase();
		}
		return columnNames;
	}

	@Override
	public Object[][] createMatrix() throws Exception {
		Object[][] matrix;
		ResultSetMetaData rsmd = rs.getMetaData();
        int nCol = rsmd.getColumnCount();
        List<Object[]> rows = new ArrayList<>();
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
        
        return matrix;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "TODO";
	}

}
