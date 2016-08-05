package thorxiong.demo.checklist.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import thorxiong.demo.checklist.model.Task;

public class TaskMapperTester {

	private static final Long ID = 1L;
	private static final String USER_NAME = "Joe";
	private static final String DESCRIPTION = "Task 1";
	private static final Boolean IS_COMPLETED = false;
	private static final String SHARED_USERS = "";

	@Mock
	private ResultSet resultSet;

	@Before
	public void setup() throws SQLException {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test to validate mapper will map the result set to the Task object.
	 * @throws SQLException
	 */
	@Test
	public void testMapper() throws SQLException {
		// Mock
		Mockito.when(this.resultSet.next()).thenReturn(true).thenReturn(false);
		Mockito.when(this.resultSet.getLong(Task.ID)).thenReturn(ID);
		Mockito.when(this.resultSet.getString(Task.USER_NAME)).thenReturn(USER_NAME);
		Mockito.when(this.resultSet.getString(Task.DESCRIPTION)).thenReturn(DESCRIPTION);
		Mockito.when(this.resultSet.getBoolean(Task.IS_COMPLETED)).thenReturn(IS_COMPLETED);
		Mockito.when(this.resultSet.getString(Task.SHARED_USERS)).thenReturn(SHARED_USERS);

		// Test
		List<Task> taskList = TaskMapper.mapResults(this.resultSet);
		Task task = taskList.get(0);

		// Verify
		Assert.assertArrayEquals(new int[] {1}, new int[] {taskList.size()});
		Assert.assertArrayEquals(new String[] {ID.toString(), USER_NAME, DESCRIPTION, IS_COMPLETED.toString(), SHARED_USERS},
				new String[] {task.getId().toString(), task.getUserName(), task.getDescription(), task.getIsCompleted().toString(), task.getSharedUsers()});
	}
}
