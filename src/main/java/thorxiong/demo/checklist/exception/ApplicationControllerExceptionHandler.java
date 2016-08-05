package thorxiong.demo.checklist.exception;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ApplicationControllerExceptionHandler {
	private static Logger LOGGER = LogManager.getLogger(ApplicationControllerExceptionHandler.class);

    @ExceptionHandler(value = ApplicationException.class)
    @ResponseBody
    public ApplicationError handleApplicationException(HttpServletResponse response, ApplicationException e){
    	LOGGER.info(e.getMessage());

    	response.setStatus(HttpStatus.NOT_FOUND.value());
    	return new ApplicationError(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApplicationError handleBaseException(HttpServletResponse response, Exception e){
    	LOGGER.info(e.getMessage());

    	response.setStatus(HttpStatus.BAD_REQUEST.value());
    	return new ApplicationError(HttpStatus.BAD_REQUEST);
    }

}
