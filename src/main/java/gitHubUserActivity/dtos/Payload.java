package gitHubUserActivity.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import glaciar.anotaciones.PenguinAttribute;
import glaciar.anotaciones.PenguinEntity;

@PenguinEntity
public class Payload {
	
	@JsonProperty("repository_id")
	@PenguinAttribute(penguinKey = true)
	private Long repositoryId;
	@JsonProperty("push_id")
	private Long pushId;
	private int size;
	@JsonProperty("distinct_size")
	private int distinctSize;
	private String ref;
	private String head;
	private String before;
	private List<Commits> commits;
	
	@JsonProperty("ref_type")
    private String refType;
	@JsonProperty("master_branch")
	private String masterBranch;
	private String description;
	@JsonProperty("pusher_type")
	private String pusherType;
	
	public Long getRepositoryId() {
		return repositoryId;
	}
	public void setRepositoryId(Long repositoryId) {
		this.repositoryId = repositoryId;
	}
	public Long getPushId() {
		return pushId;
	}
	public void setPushId(Long pushId) {
		this.pushId = pushId;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getDistinctSize() {
		return distinctSize;
	}
	public void setDistinctSize(int distinctSize) {
		this.distinctSize = distinctSize;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getBefore() {
		return before;
	}
	public void setBefore(String before) {
		this.before = before;
	}
	public List<Commits> getCommits() {
		return commits;
	}
	public void setCommits(List<Commits> commits) {
		this.commits = commits;
	}
	public String getRefType() {
		return refType;
	}
	public void setRefType(String refType) {
		this.refType = refType;
	}
	public String getMasterBranch() {
		return masterBranch;
	}
	public void setMasterBranch(String masterBranch) {
		this.masterBranch = masterBranch;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPusherType() {
		return pusherType;
	}
	public void setPusherType(String pusherType) {
		this.pusherType = pusherType;
	}
	
}
