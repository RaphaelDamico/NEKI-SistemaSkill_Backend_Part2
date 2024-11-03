package br.com.neki.sistema_skill_refactored.exceptions;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.micrometer.common.lang.Nullable;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final String ERROR_PREFIX = "Error: ";

	@ExceptionHandler(EntityNotFoundException.class)
	ProblemDetail handleEntityNotFoundException(EntityNotFoundException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
				ERROR_PREFIX + e.getLocalizedMessage());

		problemDetail.setTitle("Resource not found");
		problemDetail.setType(URI.create("http://localhost:8080/errors/not-found"));
		return problemDetail;
	}

	@ExceptionHandler(SkillAlreadyExistsException.class)
	ProblemDetail handleSkillAlreadyExistsException(SkillAlreadyExistsException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,
				ERROR_PREFIX + e.getLocalizedMessage());

		problemDetail.setTitle("Skill already exists");
		problemDetail.setType(URI.create("http://localhost:8080/errors/conflict"));
		return problemDetail;
	}
	
	@ExceptionHandler(UsernameAlreadyExistsException.class)
	ProblemDetail handleUsernameAlreadyExistsException(UsernameAlreadyExistsException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,
				ERROR_PREFIX + e.getLocalizedMessage());
		
		problemDetail.setTitle("Username already exists");
		problemDetail.setType(URI.create("http://localhost:8080/errors/conflict"));
		return problemDetail;
	}

	@ExceptionHandler(NoSuchElementException.class)
	ProblemDetail handleNoSuchElementException(NoSuchElementException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
		problemDetail.setTitle("Resource not Found");
		problemDetail.setType(URI.create("http://localhost:8080/errors/not-found"));
		return problemDetail;
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ProblemDetail> handleIllegalArgumentException(IllegalArgumentException exception, WebRequest request) {
		ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
				"Error: '" + exception.getLocalizedMessage());
		pd.setType(URI.create("http://localhost:8080/errors/bad-request"));
		pd.setTitle("Bad Request");
		pd.setProperty("hostname", "localhost");
		return ResponseEntity.status(400).body(pd);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
	        HttpStatusCode statusCode, WebRequest request) {
	    ResponseEntity<Object> response = super.handleExceptionInternal(ex, body, headers, statusCode, request);

	    if (response != null && response.getBody() instanceof ProblemDetail problemDetailBody) {
	        problemDetailBody.setProperty("message", ex.getMessage());
	        if (ex instanceof MethodArgumentNotValidException subEx) {
	            BindingResult result = subEx.getBindingResult();
	            problemDetailBody.setType(URI.create("http://localhost:8080/erros/argument-not-valid"));
	            problemDetailBody.setTitle("Request error");
	            problemDetailBody.setDetail("An error occurred while processing the Request");
	            problemDetailBody.setProperty("message", "Object Validation Failed" + result.getObjectName() + "'. "
	                    + "Number of Errors: " + result.getErrorCount());
	            List<FieldError> fieldErrors = result.getFieldErrors();
	            List<String> errors = new ArrayList<>();

	            for (FieldError fieldError : fieldErrors) {
	                errors.add("Field: " + fieldError.getField() + " - Error: " + fieldError.getDefaultMessage());
	            }
	            problemDetailBody.setProperty("Errors Found", errors.toString());
	        }
	    }
	    return response;
	}

}
