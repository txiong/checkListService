package thorxiong.demo.checklist.dao;

import java.sql.SQLException;
import java.util.List;

import thorxiong.demo.checklist.model.Task;

public interface TaskDAO {

	/**
	 * Get the tasks associated with the given user name.
	 * @param userName The user name.
	 * @return A list of tasks for the given user name.
	 * @throws SQLException
	 */
	public List<Task> getTasks(String userName) throws SQLException;

	/**
	 * Get the tasks associated with the shared user name.
	 * @param userName The user name.
	 * @return A list of tasks that match the shared user name.
	 * @throws SQLException
	 */
	public List<Task> getSharedTasks(String userName) throws SQLException;

	/**
	 * Update a task.
	 * @param task The task to update.
	 * @return 1 if successful else 0
	 * @throws SQLException
	 */
	public int updateTask(Task task) throws SQLException;

	/**
	 * Create a task.
	 * @param task The task to create.
	 * @return 1 if successful else 0
	 * @throws SQLException
	 */
	public int createTask(Task task) throws SQLException;

	/**
	 * Delete a task.
	 * @param id The task id to remove.
	 * @return 1 if successful else 0
	 * @throws SQLException
	 */
	public int deleteTask(Integer id) throws SQLException;

	/**
	 * Delete all the tasks for this user.
	 * @param userName The user name for tasks to be deleted.
	 * @return Greater than 1 if successful else 0
	 * @throws SQLException
	 */
	public int deleteTask(String userName) throws SQLException;
}
