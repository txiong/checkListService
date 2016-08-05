package thorxiong.demo.checklist.service;

import java.util.List;

import thorxiong.demo.checklist.exception.ApplicationException;
import thorxiong.demo.checklist.model.Task;

public interface TaskService {

	/**
	 * Get the tasks associated with the given user name.
	 * @param userName The user name.
	 * @return A list of tasks for the given user name.
	 * @throws ApplicationException
	 */
	public List<Task> getTasks(String userName) throws ApplicationException;

	/**
	 * Get the tasks associated with the shared user name.
	 * @param userName The user name.
	 * @return A list of tasks that match the shared user name.
	 * @throws ApplicationException
	 */
	public List<Task> getSharedTasks(String userName) throws ApplicationException;

	/**
	 * Update a task.
	 * @param task The task to update.
	 * @return 1 if successful else 0
	 * @throws ApplicationException
	 */
	public void updateTask(Task task) throws ApplicationException;

	/**
	 * Create a task.
	 * @param task The task to create.
	 * @return 1 if successful else 0
	 * @throws ApplicationException
	 */
	public void createTask(Task task) throws ApplicationException;

	/**
	 * Delete a task.
	 * @param id The task id to remove.
	 * @return 1 if successful else 0
	 * @throws ApplicationException
	 */
	public void deleteTask(Integer id) throws ApplicationException;

	/**
	 * Delete all the tasks for this user.
	 * @param userName The user name for tasks to be deleted.
	 * @return Greater than 1 if successful else 0
	 * @throws ApplicationException
	 */
	public void deleteTask(String userName) throws ApplicationException;
}
