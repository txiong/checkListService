package thorxiong.demo.checklist.exception;

public class ApplicationException extends Exception {
	private static final long serialVersionUID = -7720723533120448184L;

	// Application exception messages
	public static final String ERROR_FAILED_TO_GET_TASKS_FOR_USER = "Error, failed to get checklist for user %s";
	public static final String ERROR_FAILED_TO_UPDATE_TASK = "Error, failed to update task";
	public static final String ERROR_NO_ENTRY = "Error, entry does not exist";
	public static final String ERROR_FAILED_TO_CREATE_TASK = "Error, failed to create task";
	public static final String ERROR_FAILED_TO_DELETE_TASK = "Error, failed to delete task %d";
	public static final String ERROR_FAILED_TO_DELETE_TASK_FOR_USER = "Error, failed to delete task(s) for user %s";
	public static final String ERROR_BAD_REQUEST = "Bad request";

	/**
	 * Initialize exception with error message
	 * @param errorMessage The error message.
	 */
	public ApplicationException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * Initialize exception with error message and parameter that will be
	 * inserted in the error message.
	 * @param errorMessage The error message.
	 * @param param The string param to be inserted into the error message.
	 */
	public ApplicationException(String errorMessage, String param) {
		super(String.format(errorMessage, param));
	}

	/**
	 * Initialize exception with error message and parameter that will be
	 * inserted in the error message.
	 * @param errorMessage The error message.
	 * @param param The int param to be inserted into the error message.
	 */
	public ApplicationException(String errorMessage, int param) {
		super(String.format(errorMessage, param));
	}
}
