package co.com.sigepro.control.validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang.StringUtils;



public class EmailValidador implements Validator {

	@Override
	public void validate(FacesContext contex, UIComponent componente, Object valorComponente)
			throws ValidatorException {
		 // Si el valor es null, lo transformamos en un valor vacío
        String valor = StringUtils.defaultString((String)valorComponente);
        // el valor debe tener 9 posiciones, de las cuales las primeras deben ser dígitos y la última letra
        valor=valor.toUpperCase();
        Pattern mask =  Pattern.compile("^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$");
        Matcher matcher = mask.matcher(valor);
        if(!matcher.matches())
                    throw new ValidatorException(new FacesMessage("El componente " + componente.getId() + 
                           "un direccion de correo electonico valida"));      

		
	}
	
}
