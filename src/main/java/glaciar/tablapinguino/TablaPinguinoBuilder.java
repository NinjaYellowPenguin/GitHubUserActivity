package glaciar.tablapinguino;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TablaPinguinoBuilder {
	
	private Map<String, Set<Object>> tables;
	
	public TablaPinguinoBuilder() {
		tables = new HashMap<String, Set<Object>>();
	}
	
	public void addObject(Object objectToAdd ) {
		String clazzName = objectToAdd.getClass().getName();
		addObject(clazzName, objectToAdd);	
	}
	public void addObject(String clazzName, Object objectToAdd ) {
		try {
			tables.get(clazzName).add(objectToAdd);
		} catch (RuntimeException e) {
			tables.put(clazzName, new HashSet<>());
			tables.get(clazzName).add(objectToAdd);			
		}	
	}
	
	public List<TablaPinguino> build() {
		return tables.values()
			    .stream().map(set -> {
			    	return new TablaPinguino(set.toArray());
			    })
			    .collect(Collectors.toList());
	}

}
