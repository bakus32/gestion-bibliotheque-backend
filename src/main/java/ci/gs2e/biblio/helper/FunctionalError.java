
/*
 * Created on 2022-10-21 ( Time 23:35:15 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Smile Backend generator. All Rights Reserved.
 */

package ci.gs2e.biblio.helper;

import java.util.Locale;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import lombok.*;

/**
 * Functional Error
 * 
 * @author Smile Backend generator
 *
 */
@Data
@ToString
@NoArgsConstructor
@XmlRootElement
@Component
public class FunctionalError {
	private String			code;
	private String			message;
	@Autowired
	private MessageSource	messageSource;

	//private static Status	status	= new Status();

	public Status SUCCESS(String message, Locale locale) {
    	Status status = new Status();
		status.setCode(StatusCode.SUCCESS);
		status.setMessage(messageSource.getMessage("StatusMessage.SUCCESS", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status AUTH_FAIL(String message, Locale locale) {
    	Status status = new Status();
		status.setCode(StatusCode.FUNC_AUTH_FAIL);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_AUTH_FAIL", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status DATA_NOT_EXIST(String message, Locale locale) {
    	Status status = new Status();
		status.setCode(StatusCode.FUNC_DATA_NOT_EXIST);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_DATA_NOT_EXIST", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status DATA_TOO_LONG(String message, Locale locale) {
    	Status status = new Status();
		status.setCode(StatusCode.FUNC_DATA_TOO_LONG);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_DATA_TOO_LONG", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status DATA_EMPTY(String message, Locale locale) {
    	Status status = new Status();
		status.setCode(StatusCode.FUNC_DATA_EMPTY);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_DATA_EMPTY", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status DATA_EXIST(String message, Locale locale) {
    	Status status = new Status();
		status.setCode(StatusCode.FUNC_DATA_EXIST);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_DATA_EXIST", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status INVALID_CODE_LANGUAGE(String message, Locale locale) {
    	Status status = new Status();
		status.setCode(StatusCode.FUNC_INVALID_CODE_LANGUAGE);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_INVALID_CODE_LANGUAGE", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status FIELD_EMPTY(String message, Locale locale) {
    	Status status = new Status();
		status.setCode(StatusCode.FUNC_FIELD_EMPTY);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_FIELD_EMPTY", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status USER_ALREADY_CONNECTED(String message, Locale locale) {
    	Status status = new Status();
		status.setCode(StatusCode.FUNC_USER_ALREADY_CONNECTED);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_USER_ALREADY_CONNECTED", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status REQUEST_FAIL(String message, Locale locale) {
    	Status status = new Status();
		status.setCode(StatusCode.FUNC_REQUEST_FAIL);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_REQUEST_FAIL", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status SAVE_FAIL(String message, Locale locale) {
    	Status status = new Status();
		status.setCode(StatusCode.FUNC_SAVE_FAIL);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_SAVE_FAIL", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status TYPE_NOT_CORRECT(String message, Locale locale) {
    	Status status = new Status();
		status.setCode(StatusCode.FUNC_TYPE_NOT_CORRECT);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_TYPE_NOT_CORRECT", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status DATE_FORMAT_NOT_CORRECT(String message, Locale locale) {
    	Status status = new Status();
		status.setCode(StatusCode.FUNC_DATE_FORMAT_NOT_CORRECT);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_DATE_FORMAT_NOT_CORRECT", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status INVALID_DATE_PERIOD(String message, Locale locale) {
    	Status status = new Status();
		status.setCode(StatusCode.FUNC_INVALID_DATE_PERIOD);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_INVALID_DATE_PERIOD", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status INVALID_FORMAT(String message, Locale locale) {
    	Status status = new Status();
		status.setCode(StatusCode.FUNC_INVALID_FORMAT);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_INVALID_FORMAT", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status INVALID_ENTITY_NAME(String message, Locale locale) {
    	Status status = new Status();
		status.setCode(StatusCode.FUNC_INVALID_ENTITY_NAME);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_INVALID_ENTITY_NAME", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status IMEI_INCORRECT(String message, Locale locale) {
    	Status status = new Status();
		status.setCode(StatusCode.FUNC_IMEI_INCORRECT);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_IMEI_INCORRECT", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status FUNC_ONE_MUST_BE_FIELD_EMPTY(String message, Locale locale) {
    	Status status = new Status();
		status.setCode(StatusCode.FUNC_ONE_MUST_BE_FIELD_EMPTY);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_ONE_MUST_BE_FIELD_EMPTY", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status FUNC_PERCENT_VALUE(String message, Locale locale) {
    	Status status = new Status();
		status.setCode(StatusCode.FUNC_MUST_BE_INFERIOR_TO_100);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_MUST_BE_INFERIOR_TO_100", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status FILE_GENERATION_ERROR(String message, Locale locale) {
    	Status status = new Status();
		status.setCode(StatusCode.FUNC_FILE_GENERATION_ERROR);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_FILE_GENERATION_ERROR", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status LOGIN_FAIL(Locale locale) {
    	Status status = new Status();
		status.setCode(StatusCode.FUNC_LOGIN_FAIL);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_LOGIN_FAIL", new Object[] {}, locale));
		return status;
	}

	public Status DISALLOWED_OPERATION(String message, Locale locale) {
    	Status status = new Status();
		status.setCode(StatusCode.FUNC_FILE_GENERATION_ERROR);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_DISALLOWED_OPERATION", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status DATA_NOT_DELETABLE(String message, Locale locale) {
    	Status status = new Status();
		status.setCode(StatusCode.FUNC_DATA_NOT_DELETABLE);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_DATA_NOT_DELETABLE", new Object[] {}, locale) + ": " + message);
		return status;
	}

	public Status DATA_DUPLICATE(String message, Locale locale) {
    	Status status = new Status();
		status.setCode(StatusCode.FUNC_DATA_DUPLICATE);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_DATA_DUPLICATE", new Object[] {}, locale) + ": " + message);
		return status;
	}

    public Status ACTIVE_DIRECTORY_ERROR(String message, Locale locale) {
    	Status status = new Status();
        status.setCode(StatusCode.FUNC_AUTH_FAIL);
        status.setMessage(messageSource.getMessage("StatusMessage.FUNC_AUTH_FAIL", new Object[]{}, locale) + ": " + message);
        return status;
    }

    public Status SESSION_EXPIRED(String message, Locale locale) {
    	Status status = new Status();
        status.setCode(StatusCode.FUNC_SESSION_EXPIRED);
        status.setMessage(messageSource.getMessage("StatusMessage.FUNC_SESSION_EXPIRED", new Object[] {}, locale) + message);
        return status;
    }

    public Status ACCESS_DENIED(String message, Locale locale) {
    	Status status = new Status();
        status.setCode(StatusCode.FUNC_ACCESS_DENIED);
        status.setMessage(messageSource.getMessage("StatusMessage.FUNC_ACCESS_DENIED", new Object[] {}, locale) + message);
        return status;
    }

	public Status INVALID_DATA(String message, Locale locale) {
		Status status = new Status();
		status.setCode(StatusCode.FUNC_INVALID_DATA);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_INVALID_DATA", new Object[] {}, locale) + (Utilities.isBlank(message) ? "" : (": " + message)));
		return status;
	}

	public Status USER_IS_LOCKED(String message, Locale locale) {
		Status status = new Status();
		status.setCode(StatusCode.FUNC_USER_IS_LOCKED);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_USER_IS_LOCKED", new Object[] {}, locale) + (Utilities.blank(message) ? "" : (": " + message)));
		return status;
	}
	
	public Status USER_NOT_GRANTED(String message, Locale locale) {
		Status status = new Status();
		status.setCode(StatusCode.FUNC_USER_NOT_GRANTED);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_USER_NOT_GRANTED", new Object[] {}, locale) + (Utilities.blank(message) ? "" : (": " + message)));
		return status;
	}

	public Status LANGUAGE_NOT_SUPPORTED(String message, Locale locale) {
		Status status = new Status();
		status.setCode(StatusCode.FUNC_LANGUAGE_NOT_SUPPORTED);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_LANGUAGE_NOT_SUPPORTED", new Object[] {}, locale) + (Utilities.blank(message) ? "" : (": " + message)));
		return status;
	}

	public Status USER_NOT_CONNECTED(String message, Locale locale) {
		Status status = new Status();
		status.setCode(StatusCode.FUNC_USER_NOT_CONNECTED);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_USER_NOT_CONNECTED", new Object[] {}, locale) + (Utilities.blank(message) ? "" : (": " + message)));
		return status;
	}

	public Status DEFAULT_PWD_NOT_CHANGED(String message, Locale locale){
		Status status = new Status();
		status.setCode(StatusCode.FUNC_DEFAULT_PWD_NOT_CHANGED);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_DEFAULT_PWD_NOT_CHANGED", new Object[] {}, locale) + (Utilities.blank(message) ? "" : (": " + message)));
		return status ;
	}
	
	public Status DATA_NOT_FOUND(String message, Locale locale) {
		Status status = new Status();
		status.setCode(StatusCode.FUNC_DATA_NOT_FOUND);
		status.setMessage(messageSource.getMessage("StatusMessage.FUNC_DATA_NOT_FOUND", new Object[] {}, locale) + (Utilities.blank(message) ? "" : (": " + message)));
		return status;
	}
}