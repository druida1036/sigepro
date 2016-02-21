package co.com.sigepro.control;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jfree.chart.JFreeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import co.com.sigepro.entidades.Actividad;
import co.com.sigepro.entidades.Paginador;
import co.com.sigepro.entidades.Proyecto;
import co.com.sigepro.negocio.serv.ActividadServicio;
import co.com.sigepro.negocio.serv.GanttService;

@Controller
@Scope("session")
public class GanttBean {
	public static final int TAMANO_PAGINA = 6;

	private List<Actividad> actividades;
	private Paginador paginador = new Paginador();
	private Proyecto proyecto;

	@Autowired
	private GanttService ganttService;
	@Autowired
	private ActividadServicio actividadServicio;

	@PostConstruct
	public void inicializarDiagrama() {
		paginador.setTamañoPagina(TAMANO_PAGINA);
	}

	public void paint(Graphics2D g2d, Object data) {

		if (proyecto != null) {
			Integer idMetodologia = proyecto.getMetodologia().getId();
			actividades = actividadServicio.actividadesPorMetodologia(
					idMetodologia, paginador);
			JFreeChart diagrama = ganttService.generarDiagrama(actividades);
			Font font = new Font("Serif", Font.HANGING_BASELINE, 40);
			g2d.setFont(font);
			Rectangle2D chartArea = new Rectangle2D.Double(0, 0, 600, 300);
			AffineTransform origTransform = g2d.getTransform();
			g2d.scale(1, 0.8);
			diagrama.draw(g2d, chartArea);
			g2d.setTransform(origTransform);
		}
	}

	public Paginador getPaginador() {
		return paginador;
	}

	public void setPaginador(Paginador paginador) {
		this.paginador = paginador;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

}
