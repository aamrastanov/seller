package az.tezapp.seller.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import az.tezapp.seller.server.exception.ControllerLogicException;
import az.tezapp.seller.server.manager.LoggerManager;

@ControllerAdvice
public class RestAdvisor {	
	
	@ExceptionHandler	
	public void handleException(Exception e) throws Exception{		
		LoggerManager.error(e);
		throw e;
	}
	
	@ExceptionHandler(ControllerLogicException.class)
	public ResponseEntity<LogicErrorResponse> hadleControllerException(ControllerLogicException e){
		LoggerManager.error(e);
		LogicErrorResponse errorResponse = new LogicErrorResponse(e.getErrorCode(), e.getMessage());
		return new ResponseEntity<LogicErrorResponse>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
