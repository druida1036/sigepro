package co.com.sigepro.persistencia.util;

import java.util.regex.Pattern;

import org.hibernate.mapping.Property;
import org.hibernate.validator.PropertyConstraint;
import org.hibernate.validator.Validator;

public class NotWhiteSpaceValidator implements Validator<NotWhiteSpace>,
		PropertyConstraint {

	private String message;

	@Override
	public void apply(Property arg0) {

	}

	@Override
	public void initialize(NotWhiteSpace arg0) {

	}

	@Override
	public boolean isValid(Object valor) {
		if (valor == null) {
			return false;
		} else {
			return Pattern.matches("^([\\S]).*([\\S])$", valor.toString());
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
