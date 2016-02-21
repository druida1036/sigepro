package co.com.sigepro.control.convertidores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import co.com.sigepro.entidades.Pagina;

public class Convertidor implements Converter{

	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {

		
		Pagina pagina = new Pagina();
		pagina.setNombre(value);
		
		return pagina;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		Pagina optionItem = (Pagina) value;
		return optionItem.toString() ;
	}

}