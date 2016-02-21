package co.com.sigepro.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.el.Expression;
import javax.el.MethodExpression;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.event.ActionEvent;
import javax.faces.event.MethodExpressionActionListener;

import org.ajax4jsf.component.html.HtmlAjaxCommandButton;
import org.richfaces.component.html.HtmlDropDownMenu;
import org.richfaces.component.html.HtmlMenuGroup;
import org.richfaces.component.html.HtmlMenuItem;
import org.richfaces.component.html.HtmlToolBar;
import org.richfaces.component.html.HtmlToolBarGroup;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.com.sigepro.control.util.Constantes;
import co.com.sigepro.control.util.FacesUtils;
import co.com.sigepro.entidades.Credencial;
import co.com.sigepro.entidades.Menu;
import co.com.sigepro.entidades.Pagina;
import co.com.sigepro.entidades.Persona;
import co.com.sigepro.entidades.Submenu;
import co.com.sigepro.entidades.Subsistema;

/**
 * @author Jorge Armando Martinez Sanchez (jamartinez@aliaddos.com)
 */
@Component("menuUsuarioBean")
@Scope("session")
public class MenuUsuarioBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String ACTION_LISTENER = "#{menuUsuarioBean.actionListener}";
	private final static String MENU = "menu";
	private final static String SUBMENU = "submenu";
	private final static String PAGINA = "pagina";
	private Subsistema subsistemaActual;

	HtmlToolBar bar = new HtmlToolBar();
	boolean renderedBar = false;
	Persona usuario;

	/**
	 * Inserta un(a) bar
	 * 
	 * @param bar
	 *            the bar to set
	 */
	public void setBar(HtmlToolBar bar) {
		this.bar = bar;
	}

	/**
	 * Metodo encargado de retornar el menu correspondiente al usuario activo
	 * 
	 * @return
	 */
	public HtmlToolBar getBar() {

		Persona usuarioActivo = FacesUtils.getUsuario();
		if (usuarioActivo != null
				&& usuarioActivo.getCredencial() != null
				&& usuarioActivo.getCredencial().getSubsistema() != null
				&& usuarioActivo.getCredencial().getSubsistema().getId() != null) {
			if (subsistemaActual == null) {
				subsistemaActual = new Subsistema();
				subsistemaActual.setId(new Integer(-1));
			}
			if (this.subsistemaActual.getId().compareTo(
					usuarioActivo.getCredencial().getSubsistema().getId()) != 0) {
				subsistemaActual = usuarioActivo.getCredencial()
						.getSubsistema();
				bar = crearMenu(usuarioActivo.getCredencial());
				this.usuario = usuarioActivo;
			}
			this.renderedBar = true;
		} else {
			this.renderedBar = false;
		}

		return bar;
	}

	/**
	 * Genera el nuevo menu si el subsistema a sido modificado
	 * 
	 * @param credencial
	 * @return
	 */
	public HtmlToolBar crearMenu(Credencial credencial) {

		HtmlToolBar barraMenu = new HtmlToolBar();

		List<Menu> menus = credencial.getMenus();

		Expression expresion = FacesUtils.crearExpresionEL(ACTION_LISTENER,
				Constantes.EXPRESION_ACTION_LISTENER);
		MethodExpressionActionListener actionListener = new MethodExpressionActionListener(
				(MethodExpression) expresion);

		for (Menu menu : menus) {
			HtmlForm form = new HtmlForm();
			HtmlDropDownMenu downMenu = new HtmlDropDownMenu();
			downMenu.setValue(menu.getNombre());
			// crear facet con icono y nombre
			downMenu.getFacets().put("label",
					crearFacet(menu.getNombre(), menu.getIcono()));
			List<Submenu> Submenus = menu.getSubmenus();
			for (Submenu Submenu : Submenus) {
				HtmlMenuGroup item = new HtmlMenuGroup();
				item.setIcon(null);
				item.setValue(Submenu.getNombre());
				List<Pagina> paginas = Submenu.getPaginas();
				List<HtmlMenuItem> paginaTemporales = new ArrayList<HtmlMenuItem>();
				for (Pagina pagina : paginas) {
					if (pagina.getVisible().equals("1")) {
						HtmlMenuItem menuItem = new HtmlMenuItem();
						menuItem.setValue(pagina.getNombre());
						menuItem.setIcon(null);
						menuItem.getAttributes().put(PAGINA, pagina.getId());
						menuItem.getAttributes().put(SUBMENU, Submenu.getId());
						menuItem.getAttributes().put(MENU, menu.getId());
						menuItem.addActionListener(actionListener);
						Expression accion = FacesUtils.crearExpresionEL(
								pagina.getUrl(), Constantes.EXPRESION_ACTION);
						menuItem.setActionExpression((MethodExpression) accion);
						paginaTemporales.add(menuItem);
					}

				}
				if (paginaTemporales.size() > 0) {
					for (HtmlMenuItem htmlMenuItem : paginaTemporales) {
						item.getChildren().add(htmlMenuItem);
					}
					downMenu.getChildren().add(item);
				}
			}
			if (downMenu.getChildren().size() > 0) {
				form.getChildren().add(downMenu);
				barraMenu.getChildren().add(form);
			}

		}
		HtmlForm form = new HtmlForm();
		HtmlToolBarGroup barGroup = new HtmlToolBarGroup();
		barGroup.setLocation("right");
		HtmlCommandButton logout = new HtmlCommandButton();
		logout.setImage("/tema/imagen/salir.gif");
		
		MethodExpression actionExpression =  (MethodExpression) FacesUtils.crearExpresionEL("#{ingresoBean.cerrarSesion}",
				Constantes.EXPRESION_ACTION); 
		logout.setActionExpression(actionExpression);
		logout.setImmediate(true);
		
		
		
		HtmlCommandButton home = new HtmlCommandButton();
		home.setImage("/tema/imagen/home.png");
		actionExpression =  (MethodExpression) FacesUtils.crearExpresionEL("volverInicio",
				Constantes.EXPRESION_ACTION); 
		home.setActionExpression(actionExpression);
		home.setImmediate(true);
		
		HtmlAjaxCommandButton subsistema = new HtmlAjaxCommandButton();
		subsistema.setImage("/tema/imagen/menu.png");
		subsistema.setImmediate(true);
		subsistema.setOncomplete("Richfaces.showModalPanel('modalSeleccionSubsistema');");
		
		form.getChildren().add(subsistema);
		form.getChildren().add(home);
		form.getChildren().add(logout);
		
		barGroup.getChildren().add(form);
		
		barGroup.getChildren().add(form);
		barraMenu.getChildren().add(barGroup);
		return barraMenu;
	}

	private HtmlPanelGrid crearFacet(String nombre, String icono) {
		HtmlPanelGrid panelGrid = new HtmlPanelGrid();
		panelGrid.setCellpadding("0");
		panelGrid.setCellspacing("0");
		panelGrid.setColumns(2);
		panelGrid.setStyle("vertical-align:middle");
		HtmlOutputText outputText = new HtmlOutputText();
		outputText.setValue(nombre);
		panelGrid.getChildren().add(crearIcono(icono));
		panelGrid.getChildren().add(outputText);
		return panelGrid;
	}

	/**
	 * Metodo encargadao de actualizar la credencial segun el objeto del menu
	 * 
	 * @param e
	 */
	public void actionListener(ActionEvent e) {
		Map<String, Object> attributes = e.getComponent().getAttributes();
		Credencial credencial = FacesUtils.getUsuario().getCredencial();
		credencial.setMenuActual((Integer) attributes.get(MENU));
		credencial.setSubmenuActual((Integer) attributes.get(SUBMENU));
		credencial.setPaginaActual((Integer) attributes.get(PAGINA));
	}

	/**
	 * @return the renderedBar
	 */
	public boolean isRenderedBar() {
		return renderedBar;
	}

	/**
	 * @param renderedBar
	 *            the renderedBar to set
	 */
	public void setRenderedBar(boolean renderedBar) {
		this.renderedBar = renderedBar;
	}

	/**
	 * @return the usuario
	 */
	public Persona getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(Persona usuario) {
		this.usuario = usuario;
	}

	public HtmlGraphicImage crearIcono(String rutaIcono) {
		HtmlGraphicImage graphicImage = new HtmlGraphicImage();
		graphicImage.setValue(rutaIcono);
		graphicImage.setStyleClass("pic");
		graphicImage.setWidth("25px");
		graphicImage.setHeight("25px");
		return graphicImage;
	}

}
