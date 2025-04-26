package gitHubUserActivity.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Activity {
	
	    private Long id;
	    private String type;
	    private Actor actor;
	    private Payload payload;
	    private Repo repo;
	    @JsonProperty("public")
	    private boolean isPublic;
	    @JsonProperty("created_at")
	    private LocalDateTime createdAt;
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public Actor getActor() {
			return actor;
		}
		public void setActor(Actor actor) {
			this.actor = actor;
		}
		public Payload getPayload() {
			return payload;
		}
		public void setPayload(Payload payload) {
			this.payload = payload;
		}
		public Repo getRepo() {
			return repo;
		}
		public void setRepo(Repo repo) {
			this.repo = repo;
		}
		public boolean isPublic() {
			return isPublic;
		}
		public void setPublic(boolean isPublic) {
			this.isPublic = isPublic;
		}
		public LocalDateTime getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}
	    
	    @Override
	    public String toString() {
	    	StringBuilder msg = new StringBuilder();
	    	msg.append("\n");
	    	msg.append("ID: ").append(id);
	    	msg.append("type: ").append(type);
	    	msg.append("actor: ").append(actor);
	    	msg.append("payload: ").append(payload);
	    	msg.append("repo: ").append(repo);
	    	msg.append("isPublic: ").append(isPublic);
	    	msg.append("createdAt").append(createdAt);
	    	return super.toString();
	    }
	  
}
