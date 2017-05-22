// Olga
package fr.acdo.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebRestControllerAdvice {

	@ExceptionHandler(CustomException.class)
	public ResponseMsg handleCustomException(CustomException ex) {
		ResponseMsg responseMsg = new ResponseMsg(ex.getMessage());
		return responseMsg;
	}

}
