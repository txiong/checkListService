package thorxiong.demo.checklist.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import thorxiong.demo.checklist.dao.TaskDAO;
import thorxiong.demo.checklist.exception.ApplicationException;
import thorxiong.demo.checklist.model.Task;
import thorxiong.demo.checklist.service.TaskService;

/**
 * The task service layer.
 * @author thor
 *
 */
@Service(value = "taskService")
public class TaskServiceImpl implements TaskService {
	private static Logger LOGGER = LogManager.getLogger(TaskServiceImpl.class);

	@Autowired
	private TaskDAO taskDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Task> getTasks(String userName) throws ApplicationException {
		List<Task> taskList = new ArrayList<Task>();

		try {
			taskList = taskDAO.getTasks(userName);
		} catch (SQLException e) {
			throw new ApplicationException(ApplicationException.ERROR_FAILED_TO_GET_TASKS_FOR_USER, userName);
		}

		return taskList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Task> getSharedTasks(String userName) throws ApplicationException {
		List<Task> taskList = new ArrayList<Task>();

		try {
			taskList = taskDAO.getSharedTasks(userName);
		} catch (SQLException e) {
			throw new ApplicationException(ApplicationException.ERROR_FAILED_TO_GET_TASKS_FOR_USER, userName);
		}

		return taskList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateTask(Task task) throws ApplicationException {
		try {
			int status = taskDAO.updateTask(task);

			LOGGER.info("Update task status: " + status);

			if (status == 0) {
				throw new ApplicationException(ApplicationException.ERROR_NO_ENTRY);
			}
		} catch (SQLException e) {
			throw new ApplicationException(ApplicationException.ERROR_FAILED_TO_UPDATE_TASK);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createTask(Task task) throws ApplicationException {
		try {
			int status = taskDAO.createTask(task);

			LOGGER.info("Create task status: " + status);

			if (status == 0) {
				throw new ApplicationException(ApplicationException.ERROR_NO_ENTRY);
			}
		} catch (SQLException e) {
			throw new ApplicationException(ApplicationException.ERROR_FAILED_TO_CREATE_TASK);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteTask(Integer id) throws ApplicationException {
		try {
			int status = taskDAO.deleteTask(id);

			LOGGER.info("Delete task status: " + status);

			if (status == 0) {
				throw new ApplicationException(ApplicationException.ERROR_NO_ENTRY);
			}
		} catch (SQLException e) {
			throw new ApplicationException(ApplicationException.ERROR_FAILED_TO_DELETE_TASK, id);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteTask(String userName) throws ApplicationException {
		try {
			int status = taskDAO.deleteTask(userName);

			LOGGER.info("Delete task status: " + status);

			if (status == 0) {
				throw new ApplicationException(ApplicationException.ERROR_NO_ENTRY);
			}
		} catch (SQLException e) {
			throw new ApplicationException(ApplicationException.ERROR_FAILED_TO_DELETE_TASK_FOR_USER, userName);
		}
	}

	/**
	 * Get the Task DAO object.
	 * @return TaskDAO The Task DAO.
	 */
	public TaskDAO getTaskDAO() {
		return taskDAO;
	}

	/**
	 * Set the Task DAO object.
	 * @param taskDAO The Task DAO.
	 */
	public void setTaskDAO(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}
}
