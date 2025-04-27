package glaciar.anotacionespinguino;

@PenguinEntity(name = "N/A", ignore = true)
public class PenguinNull {
	
	@PenguinAttribute(penguinKey = true)
	private final String nullDefaultValue = "N/A";

	public String getNullDefaultValue() {
		return nullDefaultValue;
	}
	
	
}
