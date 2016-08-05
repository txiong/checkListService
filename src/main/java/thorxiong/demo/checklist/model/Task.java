package thorxiong.demo.checklist.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Task {
	public static final String ID = "id";
	public static final String USER_NAME = "user_name";
	public static final String IS_COMPLETED = "is_completed";
	public static final String DESCRIPTION = "description";
	public static final String SHARED_USERS = "shared_users";

	@Id
	@ApiModelProperty(notes = "This id field will be auto generated regardless if there is a value passed in.")
	private Long id;

	@ApiModelProperty(notes = "The user name of which this task belongs to.", required = true, example = "Phil")
	private String userName;

	@ApiModelProperty(notes = "The task description", example = "Pick up Sam from pratice at 9pm tonight.")
	private String description;

	@ApiModelProperty(notes = "Indicator to if the task is complete.")
	private Boolean isCompleted;

	@ApiModelProperty(notes = "Specify the name of the users you wish to share your task with", example = "Joe,John")
	private String sharedUsers;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Boolean getIsCompleted() {
		return isCompleted;
	}
	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSharedUsers() {
		return sharedUsers;
	}
	public void setSharedUsers(String sharedUsers) {
		this.sharedUsers = sharedUsers;
	}
}
