package glaciar.commanderpenguin;

import java.lang.reflect.Method;

public abstract class CommanderPenguin {
	
	protected String[] params;
    
    public CommanderPenguin(String[] args) {
    	params  = extractParams(args);
        executeCommand(args);
    }
    
    private String[] extractParams(String[] args) {
    	if(args.length<=1) {
    		return new String[0];
    	}
    	String[] par = new String[args.length - 1];
    	for(int i = 0; i<args.length;i++) {
    		if(i>0) {
    			par[i-1] = args[0]; 
    		}
    	}
		return par;
	}

	private void executeCommand(String[] args) {
        if (args.length == 0) {
            System.out.println("No command provided.");
            return;
        }
        String comand = args[0];
        Method[] methods = this.getClass().getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(PenguinComand.class)) {
                PenguinComand annotation = method.getAnnotation(PenguinComand.class);
                if (annotation.value().equals(comand)) {
                    try {
                        method.invoke(this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return;
                }
            }
        }
        
        System.out.println("Unknown command: " + comand);
    }
}

