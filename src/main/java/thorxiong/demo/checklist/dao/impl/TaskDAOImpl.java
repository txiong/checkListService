package thorxiong.demo.checklist.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import thorxiong.demo.checklist.dao.TaskDAO;
import thorxiong.demo.checklist.model.Task;
import thorxiong.demo.checklist.model.mapper.TaskMapper;
import thorxiong.demo.checklist.util.ConnectionUtil;

/**
 * The data access layer for a Task.
 * @author thor
 *
 */
@Repository(value = "taskDAO")
public class TaskDAOImpl implements TaskDAO {
	private static Logger LOGGER = LogManager.getLogger(TaskDAOImpl.class);

	private static final String GET_TASKS_BY_USERNAME_QUERY = "SELECT * FROM TASKS WHERE UPPER(USER_NAME) = UPPER(?)";
	private static final String GET_TASKS_BY_SHARED_USERS_QUERY = "SELECT * FROM TASKS WHERE (UPPER(SHARED_USERS) = UPPER(?) OR UPPER(SHARED_USERS) LIKE UPPER(?) OR UPPER(SHARED_USERS) LIKE UPPER(?) OR UPPER(SHARED_USERS) LIKE UPPER(?))";
	private static final String INSERT_TASK = "INSERT INTO TASKS VALUES(NEXTVAL('TASKS_SEQ_ID'), ?, ?, ?, ?)";
	private static final String UPDATE_TASK = "UPDATE TASKS SET DESCRIPTION=?, IS_COMPLETED=?, SHARED_USERS=? WHERE ID=?";
	private static final String DELETE_TASK = "DELETE FROM TASKS WHERE ID=?";
	private static final String DELETE_TASKS_FOR_USER = "DELETE FROM TASKS WHERE UPPER(USER_NAME)=UPPER(?)";

	@Autowired
	private ConnectionUtil connectionUtil;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Task> getTasks(String userName) throws SQLException{
		List<Task> taskList = new ArrayList<Task>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		LOGGER.info("About to get tasks for user " + userName);

		try {
			connection = this.connectionUtil.getConnection();
			preparedStatement = connection.prepareStatement(GET_TASKS_BY_USERNAME_QUERY);
			preparedStatement.setString(1, userName);
			result = preparedStatement.executeQuery();
			taskList = TaskMapper.mapResults(result);
		} finally {
			this.connectionUtil.close(connection, preparedStatement, result);
		}

		return taskList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Task> getSharedTasks(String userName) throws SQLException {
		List<Task> taskList = new ArrayList<Task>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		LOGGER.info("About to get shared tasks for user " + userName);

		try {
			connection = this.connectionUtil.getConnection();
			preparedStatement = connection.prepareStatement(GET_TASKS_BY_SHARED_USERS_QUERY);
			// Possible cases for seaching Joe
			// 1) Joe
			// 2) Adam,Joe
			// 3) Joesph,Joe,Phil
			// 4) Joe,Adam
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, "%," + userName);
			preparedStatement.setString(3, "%," + userName + ",%");
			preparedStatement.setString(4, userName + ",%");
			result = preparedStatement.executeQuery();
			taskList = TaskMapper.mapResults(result);
		} finally {
			this.connectionUtil.close(connection, preparedStatement, result);
		}

		return taskList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int updateTask(Task task) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int status = -1;

		LOGGER.info("About to update task.");
		try {
			connection = this.connectionUtil.getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_TASK);
			preparedStatement.setString(1, task.getDescription());
			preparedStatement.setBoolean(2, task.getIsCompleted());
			preparedStatement.setString(3, task.getSharedUsers());
			preparedStatement.setLong(4, task.getId());
			status = preparedStatement.executeUpdate();
		} finally {
			this.connectionUtil.close(connection, preparedStatement);
		}

		return status;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int createTask(Task task) throws SQLException {
		int status = -1;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		LOGGER.info("About to add new task.");
		try {
			connection = this.connectionUtil.getConnection();
			preparedStatement = connection.prepareStatement(INSERT_TASK);
			preparedStatement.setString(1, task.getUserName());
			preparedStatement.setString(2, task.getDescription());
			preparedStatement.setBoolean(3, task.getIsCompleted());
			preparedStatement.setString(4, task.getSharedUsers());
			status = preparedStatement.executeUpdate();
		} finally {
			this.connectionUtil.close(connection, preparedStatement);
		}

		return status;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int deleteTask(Integer id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int status = -1;

		LOGGER.info("About to delete task.");
		try {
			connection = this.connectionUtil.getConnection();
			preparedStatement = connection.prepareStatement(DELETE_TASK);
			preparedStatement.setLong(1, id);
			status = preparedStatement.executeUpdate();
		} finally {
			this.connectionUtil.close(connection, preparedStatement);
		}

		return status;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int deleteTask(String userName) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int status = -1;

		LOGGER.info("About to delete tasks for user " + userName);
		try {
			connection = this.connectionUtil.getConnection();
			preparedStatement = connection.prepareStatement(DELETE_TASKS_FOR_USER);
			preparedStatement.setString(1,userName);
			status = preparedStatement.executeUpdate();
		} finally {
			this.connectionUtil.close(connection, preparedStatement);
		}

		return status;
	}

	/**
	 * Get the connection util.
	 * @return The ConnectionUtil
	 */
	public ConnectionUtil getConnectionUtil() {
		return connectionUtil;
	}

	/**
	 * Set the connection util.
	 * @param connectionUtil
	 */
	public void setConnectionUtil(ConnectionUtil connectionUtil) {
		this.connectionUtil = connectionUtil;
	}
}
