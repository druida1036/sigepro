package co.com.sigepro.control.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.beanutils.PropertyUtils;

public class ListaGenerica <T> {

	private List<SelectItem> lista = new ArrayList<SelectItem>();
	private SelectItem itemSeleccionado;
	private Integer idSeleccionado;
	private T objetoSeleccionado;
	private SelectItem itemNulo;
	private HashMap<Object, T> datos;

	/**
	 * @author Jorge Martinez
	 * @param datos
	 * @param id
	 *            nombre de la propiedad del objeto que será tomado id de la
	 *            lista
	 * @param rotulo
	 * 
	 */
	public ListaGenerica(List<T> datos, String id, String rotulo) {
		super();
		this.datos = new HashMap<Object, T>();
		crearItems(datos, id, rotulo);
	}

	public ListaGenerica(String[] etiquetas) {
		super();
		this.datos = new HashMap<Object, T>();
		crearItems(etiquetas);
	}

	public ListaGenerica() {
		super();
		this.datos = new HashMap<Object, T>();
	}

	public void agregarLista(List<T> datos, String id, String rotulo) {
		crearItems(datos, id, rotulo);
	}

	@SuppressWarnings("unchecked")
	private void crearItems(String[] etiquetas) {
		agregarItemNulo();
		for (int i = 0; i < etiquetas.length; i++) {
			SelectItem item = new SelectItem();
			item.setLabel(etiquetas[i]);
			item.setValue(i);
			agregarItem(item);
			datos.put(new Integer(i), (T) etiquetas[i]);
		}

	}

	private void agregarItemNulo() {
		itemNulo = new SelectItem();
		itemNulo.setValue("-1");
		itemNulo.setLabel("Seleccione un Valor");
		lista.add(itemNulo);

	}

	private void crearItems(List<T> datos, String id, String rotulo) {
		if (datos.size() > 0) {
			lista = new ArrayList<SelectItem>();
			agregarItemNulo();
			for (T object : datos) {
				SelectItem item = null;
				if (object != null)
					try {
						item = new SelectItem();
						Integer valorId = (Integer) PropertyUtils
								.getSimpleProperty(object, id);
						item.setValue(valorId);
						String valorRotulo = "";
						if(rotulo.contains(".")){
							String[] partes = rotulo.split("\\.");
							Object propiedadPadre = PropertyUtils
							.getSimpleProperty(object, partes[0]);
							valorRotulo = (String) PropertyUtils
							.getSimpleProperty(propiedadPadre, partes[1]);
						}else{
						valorRotulo = (String) PropertyUtils
								.getSimpleProperty(object, rotulo);
						}
						item.setLabel(valorRotulo);
						this.datos.put(valorId, object);

					} catch (IllegalAccessException e) {

						e.printStackTrace();
					} catch (InvocationTargetException e) {

						e.printStackTrace();
					} catch (NoSuchMethodException e) {

						e.printStackTrace();
					}

				if (item != null) {
					lista.add(item);
				}
			}

		}

	}

	public void mostrarItemNulo(boolean mostrarItem) {
		if (mostrarItem) {
			if(itemNulo == null){
				agregarItemNulo();
			}else if(!lista.contains(itemNulo)){
				lista.add(itemNulo);
			}
		} else {
			lista.remove(itemNulo);
		}
	}

	public List<SelectItem> getLista() {
		return lista;
	}

	public void setLista(List<SelectItem> lista) {
		this.lista = lista;
	}

	public SelectItem getItemSeleccionado() {
		if (idSeleccionado != null && !idSeleccionado.equals("")) {
			for (SelectItem item : lista) {
				Integer tmp = new Integer(item.getValue().toString());
				if (tmp.intValue() == idSeleccionado.intValue()) {
					this.itemSeleccionado = item;
					return itemSeleccionado;
				}
			}
		}
		return null;
	}

	public void agregarItem(SelectItem item) {
		lista.add(item);
	}

	public void clear() {
		lista.clear();
	}

	public void setItemSeleccionado(SelectItem itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public Integer getIdSeleccionado() {
		return idSeleccionado;
	}

	public void setIdSeleccionado(Integer idSeleccionado) {
		this.idSeleccionado = idSeleccionado;
	}

	public T getObjetoSeleccionado() {
		objetoSeleccionado = datos.get(idSeleccionado);
		return objetoSeleccionado;
	}

	public void setObjetoSeleccionado(T objetoSeleccionado) {
		this.objetoSeleccionado = objetoSeleccionado;
	}

	public HashMap<Object, T> getDatos() {
		return datos;
	}

	public void setDatos(HashMap<Object, T> datos) {
		this.datos = datos;
	}

}
