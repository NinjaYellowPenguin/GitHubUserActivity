package glaciar.tablapinguino;

public class StringsParaTablas {

	private StringsParaTablas() {}
	
	public static String addSpacesToString(String palabra, int spaces)
	{
		StringBuilder msg = new StringBuilder("");
		
		int margin = (spaces-palabra.length())/2;
		if((spaces-palabra.length())%2 == 1)
		{
			msg.append(" ");
		}
		for(int i = 0; i<margin;i++)
		{
			msg.append(" ");
		}
		msg.append(palabra);
		for(int i = 0; i<margin;i++)
		{
			msg.append(" ");
		}
		return msg.toString();
	}

	public static String addLine(String line, int size) {
		StringBuilder msg = new StringBuilder();
		for(int i = 0; i<size; i++)
		{
			msg.append(line);
		}
		return msg.toString();
	}
	
}
