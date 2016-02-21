package co.com.sigepro.control.convertidores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class DescripcionCorta implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String d) {
		return d;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (((String) arg2).length() > 50) {
			return ((String) arg2).substring(0, 50)+"...";
		} else {
			return arg2.toString();
		}
	}

}