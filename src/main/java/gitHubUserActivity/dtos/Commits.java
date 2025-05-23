package gitHubUserActivity.dtos;

import glaciar.anotacionespinguino.PenguinAttribute;
import glaciar.anotacionespinguino.PenguinEntity;

@PenguinEntity
public class Commits {

	@PenguinAttribute(penguinKey = true)
	private String sha;
	private Author author;
	private String message;
	private String distinct;
	private String url;
	
	public String getSha() {
		return sha;
	}
	public void setSha(String sha) {
		this.sha = sha;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDistinct() {
		return distinct;
	}
	public void setDistinct(String distinct) {
		this.distinct = distinct;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
