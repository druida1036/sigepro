package co.com.sigepro.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.com.sigepro.control.util.FacesUtils;
import co.com.sigepro.entidades.Acta;
import co.com.sigepro.entidades.Documento;
import co.com.sigepro.entidades.Persona;
import co.com.sigepro.entidades.Reunion;
import co.com.sigepro.negocio.serv.PersonaServicio;
import co.com.sigepro.negocio.serv.ReunionServicio;

@Controller
@Scope("session")
public class ReunionBean {
	
	private boolean renderModalInvitados = false; 
	private Persona persona = new Persona();
	private List<Persona> personas = new ArrayList<Persona>();
	private Reunion reunion = new Reunion();
	private Acta acta = new Acta();
	
	@Autowired
	private ReunionServicio reunionServicio;
	@Autowired
	private PersonaServicio personaServicio;
	private Documento documento;

	public String verInicializar() {
		reunion = reunionServicio.cargar(reunion.getId());
		return ConstantesNavegacion.DETALLE_REUNION;
	}
	
	public String consultar() {
		personas = new ArrayList<Persona>();
		persona = new Persona();
		persona.setNombre("");
		personas = personaServicio.listado(persona);		
		return ConstantesNavegacion.NO_ACCION;
	}
	
	public String modalCancelar() {
		renderModalInvitados = false;
		return ConstantesNavegacion.NO_ACCION;
	}
	
	public String modalAbrir() {		
		renderModalInvitados = true;
		return consultar();
	}	
	
	public String seleccionar() {	
		reunion.getPersonas().add(persona);
		renderModalInvitados = false;
		return ConstantesNavegacion.NO_ACCION;
	}

	public String crearInicializar() {
		reunion = new Reunion();
		return ConstantesNavegacion.CREAR_REUNION;
	}

	public String crearAceptar() {
		java.util.Date fechaActual = new java.util.Date();
		reunion.setCtlFecAlta(fechaActual);
		reunion = reunionServicio.guardar(reunion);
		FacesUtils.agregarMensajeInformacion("msg.crearCorrecto", true);	
		reunion = new Reunion();
		return ConstantesNavegacion.CONSULTA_REUNION;
	}



	public String verAceptar() {

		return ConstantesNavegacion.CONSULTA_REUNION;
	}

	public String crearCancelar() {

		return ConstantesNavegacion.CONSULTA_REUNION;
	}

	public String editarInicializar() {
	
		Integer id = reunion.getId();
		reunion = new Reunion();
		reunion = reunionServicio.cargar(id);
		return ConstantesNavegacion.EDITAR_REUNION;
	}

	public String editarAceptar() {
		reunion = reunionServicio.guardar(reunion);
		FacesUtils.agregarMensajeInformacion("msg.editarCorrecto", true);
		return ConstantesNavegacion.CONSULTA_REUNION;
	}

	public String editarCancelar() {
		return ConstantesNavegacion.CONSULTA_REUNION;
	}

	public String limpiar() {	
		reunion = new Reunion();
		return ConstantesNavegacion.NO_ACCION;
	}
	
	public int getNumeroInvitados() {
		return this.reunion.getPersonas() != null ? this.reunion.getPersonas().size() : 0;
	}
	
	public int getNumeroResultados() {
		return this.personas != null ? this.personas.size() : 0;
	}

	public String agregarActa(){
		if (!reunion.getActas().contains(acta)) {
			documento.setNombre(reunion.getAsunto());
			acta.setDocumento(documento);
			acta.setReunione(reunion);
			reunion.getActas().add(acta);
		}
		acta = new Acta();
		return ConstantesNavegacion.EDITAR_REUNION;
		
	}
	
	public void cargarDocumento(UploadEvent event) throws Exception {
		UploadItem item = event.getUploadItem();
		File archivo = item.getFile();
		InputStream inputStream = new FileInputStream(archivo);
		byte[] datos = new byte[item.getFileSize()];
		String[] partesNombre = item.getFileName().split("\\.");
		inputStream.read(datos);
		if (acta.getDocumento() == null) {
			documento = new Documento();
		} else {
			documento = acta.getDocumento();
		}
		documento.setDocumento(datos);
		documento.setTipoContenido(item.getContentType());
		documento.setExtencion(partesNombre[partesNombre.length - 1]);

	}
	
	public void descargarDocumento() {
		if (acta.getDocumento().getDocumento() != null) {
			HttpServletResponse response = (HttpServletResponse) FacesUtils
					.getResponse();

			response.setContentType(acta.getDocumento().getTipoContenido());
			response.addHeader("Content-disposition", "attachment; filename=\""
					+ acta.getDocumento().getNombre() + "."
					+ acta.getDocumento().getExtencion() + "\"");
			try {
				ServletOutputStream os = response.getOutputStream();
				os.write(acta.getDocumento().getDocumento());
				os.flush();
				os.close();
				FacesContext.getCurrentInstance().responseComplete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public String limpiarActa(){
		acta = new Acta();
		return ConstantesNavegacion.NO_ACCION;
	}
	public Reunion getReunion() {
		return reunion;
	}

	public void setReunion(Reunion reunion) {
		this.reunion = reunion;
	}

	public boolean isRenderModalInvitados() {
		return renderModalInvitados;
	}

	public void setRenderModalInvitados(boolean renderModalInvitados) {
		this.renderModalInvitados = renderModalInvitados;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	public Acta getActa() {
		return acta;
	}

	public void setActa(Acta acta) {
		this.acta = acta;
	}

}
