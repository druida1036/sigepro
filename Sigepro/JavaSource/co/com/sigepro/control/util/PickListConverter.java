package co.com.sigepro.control.util;

import java.util.HashMap;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import co.com.sigepro.entidades.Accion;

public class PickListConverter implements Converter {

	private HashMap<String, Accion> map;

	public PickListConverter(List<Accion> objekts) {
		map = new HashMap<String, Accion>();
		for (Accion o : objekts) {
			map.put(Integer.toString(o.getId()), o);
		}
	}

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		return map.get(string);
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {

		return obj.toString();

	}
}
