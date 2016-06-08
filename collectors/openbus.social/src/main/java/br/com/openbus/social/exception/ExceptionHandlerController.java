package br.com.openbus.social.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import facebook4j.FacebookException;

@ControllerAdvice
public class ExceptionHandlerController {

	private static final Logger LOGGER =  LoggerFactory.getLogger(ExceptionHandlerController.class);
	
	@ExceptionHandler(value = FormException.class)
	@ResponseBody
	public ResponseEntity<ExceptionBody> formExceptionHandler(Exception e, HttpServletRequest request) {
		ExceptionBody exceptionBody = new ExceptionBody();
		exceptionBody.status = HttpStatus.BAD_REQUEST.value();
		exceptionBody.message = e.getMessage();
		exceptionBody.path  = request.getRequestURI();
		return new ResponseEntity<ExceptionBody>(exceptionBody, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = FacebookException.class)
	@ResponseBody
	public ResponseEntity<ExceptionBody> facebookExceptionHandler(Exception e, HttpServletRequest request) {
		ExceptionBody exceptionBody = new ExceptionBody();
		exceptionBody.status = HttpStatus.BAD_REQUEST.value();
		exceptionBody.message = "Invalid Token";
		exceptionBody.path  = request.getRequestURI();
		LOGGER.error("Token could be invalid : ",e);
		return new ResponseEntity<ExceptionBody>(exceptionBody, HttpStatus.BAD_REQUEST);
	}
	
	
	public class ExceptionBody {

		private long timestamp = new Date().getTime();
		private int status;
		private String path;
		private String message;

		public long getTimestamp() {
			return timestamp;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
}
