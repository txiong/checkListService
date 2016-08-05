package thorxiong.demo.checklist.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import thorxiong.demo.checklist.model.Task;

public class TaskMapper {

	/**
	 * Handles mapping the result set to the Task object.
	 * @param result The result set that needs to be mapped.
	 * @return The list of tasks.
	 * @throws SQLException
	 */
	public static List<Task> mapResults(ResultSet result) throws SQLException {
		List<Task> taskList = new ArrayList<Task>();

		if (result != null) {
			while (result.next()) {
				Task task = new Task();
				task.setId(result.getLong(Task.ID));
				task.setUserName(result.getString(Task.USER_NAME));
				task.setDescription(result.getString(Task.DESCRIPTION));
				task.setIsCompleted(result.getBoolean(Task.IS_COMPLETED));
				task.setSharedUsers(result.getString(Task.SHARED_USERS));

				taskList.add(task);
			}
		}

		return taskList;
	}
}
