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
import co.com.sigepro.entidades.Documento;
import co.com.sigepro.entidades.Propuesta;
import co.com.sigepro.negocio.serv.PropuestaServicio;

@Controller
@Scope("session")
public class PropuestaBean {
	
	private boolean renderModalInvitados = false;
	private List<Propuesta> propuestas = new ArrayList<Propuesta>();
	private Propuesta propuesta = new Propuesta();
	private Documento documento = new Documento();
	
	@Autowired
	private PropuestaServicio propuestaServicio;

	public String verInicializar() {
		propuesta = propuestaServicio.cargar(propuesta.getId());
		return ConstantesNavegacion.DETALLE_PROPUESTA;
	}
	
	public String consultar() {
		propuestas = new ArrayList<Propuesta>();
		propuesta = new Propuesta();
		propuesta.setNombre("");
		propuestas = propuestaServicio.listado(propuesta);		
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
	
		renderModalInvitados = false;
		return ConstantesNavegacion.NO_ACCION;
	}

	public String crearInicializar() {
		propuesta = new Propuesta();
		return ConstantesNavegacion.CREAR_PROPUESTA;
	}

	public String crearAceptar() {
		java.util.Date fechaActual = new java.util.Date();
		propuesta.setCtlFecAlta(fechaActual);
		propuesta = propuestaServicio.guardar(propuesta);
		FacesUtils.agregarMensajeInformacion("msg.crearCorrecto", true);	
		propuesta = new Propuesta();
		return ConstantesNavegacion.CONSULTA_PROPUESTA;
	}

	public String verAceptar() {

		return ConstantesNavegacion.CONSULTA_PROPUESTA;
	}

	public String crearCancelar() {

		return ConstantesNavegacion.CONSULTA_PROPUESTA;
	}

	public String verBorrar() {		
		propuestaServicio.borrar(propuesta);
		propuesta = new Propuesta();
		return ConstantesNavegacion.NO_ACCION;
	}

	public String limpiar() {	
		propuesta = new Propuesta();
		return ConstantesNavegacion.NO_ACCION;
	}
		
	public int getNumeroResultados() {
		return this.propuestas != null ? this.propuestas.size() : 0;
	}

	public String agregarActa(){
		if (!propuesta.getDocumento().equals(documento)) {
			documento.setNombre(propuesta.getDocumento().getNombre());
			setDocumento(documento);
			setPropuesta(propuesta);
			propuesta.setDocumento(documento);
		}
		documento = new Documento();
		return ConstantesNavegacion.EDITAR_PROPUESTA;		
	}
	
	public void cargarDocumento(UploadEvent event) throws Exception {
		UploadItem item = event.getUploadItem();
		File archivo = item.getFile();
		InputStream inputStream = new FileInputStream(archivo);
		byte[] datos = new byte[item.getFileSize()];
		String[] partesNombre = item.getFileName().split("\\.");
		inputStream.read(datos);
		if (getDocumento() == null) {
			documento = new Documento();
		} else {
			documento = getDocumento();
		}
		documento.setDocumento(datos);
		documento.setTipoContenido(item.getContentType());
		documento.setExtencion(partesNombre[partesNombre.length - 1]);

	}
	
	public void descargarDocumento() {
		if (getDocumento().getDocumento() != null) {
			HttpServletResponse response = (HttpServletResponse) FacesUtils
					.getResponse();

			response.setContentType(getDocumento().getTipoContenido());
			response.addHeader("Content-disposition", "attachment; filename=\""
					+ getDocumento().getNombre() + "."
					+ getDocumento().getExtencion() + "\"");
			try {
				ServletOutputStream os = response.getOutputStream();
				os.write(getDocumento().getDocumento());
				os.flush();
				os.close();
				FacesContext.getCurrentInstance().responseComplete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public String limpiarDocumento(){
		documento = new Documento();
		return ConstantesNavegacion.NO_ACCION;
	}
	public Propuesta getPropuesta() {
		return propuesta;
	}

	public void setPropuesta(Propuesta propuesta) {
		this.propuesta = propuesta;
	}

	public boolean isRenderModalInvitados() {
		return renderModalInvitados;
	}

	public void setRenderModalInvitados(boolean renderModalInvitados) {
		this.renderModalInvitados = renderModalInvitados;
	}

	public List<Propuesta> getPropuestas() {
		return propuestas;
	}

	public void setPropuestas(List<Propuesta> propuestas) {
		this.propuestas = propuestas;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}	
}
