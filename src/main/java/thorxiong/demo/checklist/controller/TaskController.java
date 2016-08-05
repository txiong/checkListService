package thorxiong.demo.checklist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import thorxiong.demo.checklist.exception.ApplicationException;
import thorxiong.demo.checklist.model.Task;
import thorxiong.demo.checklist.service.TaskService;

/**
 * The entry point for all "check list" and "task" API.
 * @author thor
 *
 */
@RestController
@RequestMapping(value = "/checkList")
public class TaskController {
	@Autowired
	private TaskService taskService;

    @RequestMapping(value = "/userName/{userName}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
	public List<Task> getTasks(@PathVariable("userName") String userName) throws ApplicationException {
		return taskService.getTasks(userName);
	}

    @RequestMapping(value = "/shared/{userName}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
	public List<Task> getSharedTasks(@PathVariable("userName") String userName) throws ApplicationException {
		return taskService.getSharedTasks(userName);
	}

    @RequestMapping(value = "/task", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateTask(@RequestBody Task task) throws ApplicationException {
		taskService.updateTask(task);
	}

    @RequestMapping(value = "/task", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
	public void createTask(@RequestBody Task task) throws ApplicationException {
		taskService.createTask(task);
	}

    @RequestMapping(value = "/task/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
	public void deleteTask(@PathVariable("id") Integer id) throws ApplicationException {
		taskService.deleteTask(id);
	}

    @RequestMapping(value = "/userName/{userName}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
	public void deleteTask(@PathVariable("userName") String userName) throws ApplicationException {
		taskService.deleteTask(userName);
	}

    /**
     * Get the task service.
     * @return The task service.
     */
	public TaskService getTaskService() {
		return taskService;
	}

	/**
	 * Set the task service.
	 * @param taskService The task service.
	 */
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
}
