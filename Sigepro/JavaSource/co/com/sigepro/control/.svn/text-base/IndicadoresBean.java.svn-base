package co.com.sigepro.control;

import java.io.IOException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.com.sigepro.control.util.Constantes;
import co.com.sigepro.control.util.FacesUtils;
import co.com.sigepro.control.util.ListaGenerica;
import co.com.sigepro.control.util.ReportUtil;
import co.com.sigepro.entidades.Proyecto;
import co.com.sigepro.excepcion.AplicacionExcepcion;
import co.com.sigepro.negocio.serv.ActividadServicio;
import co.com.sigepro.persistencia.util.SessionUtil;

@Component("indicadoresBean")
@Scope("session")
public class IndicadoresBean {

	private Integer horasEstimadas = 0;
	private Integer horasEjecutadas = 0;
	private Proyecto proyecto;
	private ListaGenerica<String> listadoReportes = new ListaGenerica<String>();
	@Autowired
	private ActividadServicio actividadServicio;
	@Autowired
	private SessionUtil sessionUtil;
	private Double estadoProyecto;

	public String cargarIndicadores() {
		listadoReportes = new ListaGenerica<String>(
				new String[] { "Promedio Retrasos Por mes" });
		horasEstimadas = actividadServicio.calcularTotalHorasEstimadas(proyecto
				.getMetodologia().getId());
		horasEjecutadas = actividadServicio
				.calcularTotalHorasEjecutadas(proyecto.getMetodologia().getId());
		estadoProyecto = proyecto.getMetodologia().getAvance();
		return ConstantesNavegacion.CONSULTA_INDICADORES;
	}

	public void generarReporte() {
		Map<String, Object> infoReporte = new HashMap<String, Object>();
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		infoReporte.put(Constantes.REPORT_NAME,
				"co/com/sigepro/reportes/retrasos_mes");
		infoReporte.put("nombreProyecto", proyecto.getNombre());
		infoReporte.put("fecha", format.format(new Date()));
		infoReporte.put("idMetodologia", proyecto.getMetodologia().getId());

		String nombreDirector = proyecto.getPersona().getNombre() + " "
				+ proyecto.getPersona().getApellido();
		infoReporte.put("nombreDirector", nombreDirector);

		@SuppressWarnings("deprecation")
		Connection connection = sessionUtil.openSession().connection();

		String rutaLogoAliaddos = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext())
				.getRealPath("tema/imagen/aliaddos.png");

		infoReporte.put("logoAliaddos", rutaLogoAliaddos);
		
		String rutaBackAliaddos = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext())
				.getRealPath("tema/imagen/backAliaddos.png");

		infoReporte.put("backAliaddos", rutaBackAliaddos);
		try {
			byte[] reporBytes = ReportUtil.getReportePdf(infoReporte,
					connection);
			if (reporBytes != null) {
				HttpServletResponse response = (HttpServletResponse) FacesUtils
						.getResponse();

				response.setContentType("application/pdf");
				response.addHeader("Content-disposition",
						"attachment; filename=\"" + "reporte.pdf" + "\"");

				ServletOutputStream os = response.getOutputStream();
				os.write(reporBytes);
				os.flush();
				os.close();
				FacesContext.getCurrentInstance().responseComplete();

			}

		} catch (AplicacionExcepcion e1) {
			e1.printStackTrace();
		} catch (JRException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Integer getHorasEstimadas() {
		return horasEstimadas;
	}

	public void setHorasEstimadas(Integer horasEstimadas) {
		this.horasEstimadas = horasEstimadas;
	}

	public Integer getHorasEjecutadas() {
		return horasEjecutadas;
	}

	public void setHorasEjecutadas(Integer horasEjecutadas) {
		this.horasEjecutadas = horasEjecutadas;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public ListaGenerica<String> getListadoReportes() {
		return listadoReportes;
	}

	public void setListadoReportes(ListaGenerica<String> listadoReportes) {
		this.listadoReportes = listadoReportes;
	}

	public Double getEstadoProyecto() {
		return estadoProyecto;
	}

	public void setEstadoProyecto(Double estadoProyecto) {
		this.estadoProyecto = estadoProyecto;
	}

}
