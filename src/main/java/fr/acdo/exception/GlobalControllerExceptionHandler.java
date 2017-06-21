package fr.acdo.exception;

import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.CannotCreateTransactionException;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 *
 * @author Clément
 */
@RestControllerAdvice
public class GlobalControllerExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

	/*
	 * SERVER ERROR
	 */
	@ExceptionHandler(value = { CannotCreateTransactionException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiErrorResponse cannotCreateTransactionException(CannotCreateTransactionException ex) {
		logger.error("***--Erreur Accès Base De Données--***");
		return new ApiErrorResponse(500, 500404, ex.getMessage());
	}

	// primary key or UNIQUE index error
	@ExceptionHandler(value = { ConstraintViolationException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiErrorResponse constraintViolationException(ConstraintViolationException ex) {
		return new ApiErrorResponse(500, 5001, ex.getMessage());
	}

	// element not find
	@ExceptionHandler(value = { NoSuchElementException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiErrorResponse notExistException(Exception ex) {
		return new ApiErrorResponse(500, 5002, ex.getMessage());
	}

	/*
	 * BAD REQUEST
	 */
	@ExceptionHandler(value = { IllegalArgumentException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorResponse badArgument(Exception ex) {
		return new ApiErrorResponse(400, 4030, ex.getMessage());
	}

	/*
	 * 404 ERROR
	 */
	@ExceptionHandler(value = { NoHandlerFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrorResponse noHandlerFoundException(Exception ex) {
		return new ApiErrorResponse(404, 4041, ex.getMessage());
	}

	/*
	 * ACCES ERROR
	 */
	// @ExceptionHandler(value = { AccessDeniedException.class })
	//
	// @ResponseStatus(HttpStatus.FORBIDDEN) public ApiErrorResponse
	// accesDeniedException(Exception ex) { return new ApiErrorResponse(403,
	// 4030, ex.getMessage()); }
	//
	// @ExceptionHandler(value = { AuthenticationException.class })
	//
	// @ResponseStatus(HttpStatus.UNAUTHORIZED) public ApiErrorResponse
	// accesUnauthorizedException(Exception ex) { return new
	// ApiErrorResponse(401, 4010, ex.getMessage()); }
	//

	/*
	 * TEAPOT ERROR
	 */
	// Any attempt to brew coffee with a teapot should result in the error code
	// "418 I'm a teapot".
	// The resulting entity body MAY be short and stout
	// Toute tentative de préparer du café avec une théière devrait entraîner le
	// code d'erreur "418, je suis une théière".
	// Le corps de l'entité qui en résulte PEUT être court et robuste
	// @ExceptionHandler(value = { Exception.class })
	// @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
	// public ApiErrorResponse teaPotException(Exception ex) {
	// return new ApiErrorResponse(418, 4180, ex.getMessage());
	// }

}
