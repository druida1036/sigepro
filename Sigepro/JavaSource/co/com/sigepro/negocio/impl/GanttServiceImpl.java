package co.com.sigepro.negocio.impl;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;
import org.springframework.stereotype.Service;

import co.com.sigepro.entidades.Actividad;
import co.com.sigepro.negocio.serv.GanttService;
import co.com.sigepro.negocio.utilidades.Utilidades;

@Service
public class GanttServiceImpl implements GanttService {

	public JFreeChart generarDiagrama() {
		return null;
	}

	public JFreeChart generarDiagrama(List<Actividad> actividades) {
		TaskSeries base = crearLineaBase("Linea Base", actividades);
		TaskSeries ejecucion = crearLineaEjecucion("Ejecutado", actividades);
		TaskSeriesCollection collection = new TaskSeriesCollection();
		collection.add(base);
		collection.add(ejecucion);
		return createChart(collection);
	}

	private TaskSeries crearLineaBase(String nombreSerie,
			List<Actividad> actividades) {
		TaskSeries serie = new TaskSeries(nombreSerie);
		for (Actividad actividad : actividades) {
			Task tarea = new Task(actividad.getNombre(), new SimpleTimePeriod(
					actividad.getFechaInicio(), Utilidades.sumarHoras(actividad
							.getFechaInicio(), actividad.getDuracion()
							.intValue())));
			serie.add(tarea);
		}
		return serie;
	}

	private TaskSeries crearLineaEjecucion(String nombreSerie,
			List<Actividad> actividades) {
		TaskSeries serie = new TaskSeries(nombreSerie);
		for (Actividad actividad : actividades) {
			if (actividad.getFechaFinReal() != null
					&& actividad.getDuracionReal() != null) {
				Task tarea = new Task(
						actividad.getNombre(),
						new SimpleTimePeriod(actividad.getFechaInicioReal(),
								Utilidades.sumarHoras(
										actividad.getFechaInicioReal(),
										actividad.getDuracionReal().intValue())));
				serie.add(tarea);
			}
		}
		return serie;
	}

	/**
	 * Creates a chart.
	 * 
	 * @param dataset
	 *            the dataset.
	 * 
	 * @return The chart.
	 */
	private JFreeChart createChart(final IntervalCategoryDataset dataset) {
		final JFreeChart chart = ChartFactory.createGanttChart(
				"Diagrama de Gantt", // chart title
				"Tareas", // domain axis label
				"Fechas", // range axis label
				dataset, // data
				true, // include legend
				true, // tooltips
				false // urls
				);
		// chart.getCategoryPlot().getDomainAxis().setMaxCategoryLabelWidthRatio(Float.parseFloat("1"));
		chart.setTitle("");
		// get an axis reference...
//		CategoryPlot plot = chart.getCategoryPlot();
//		CategoryAxis domainAxis = plot.getDomainAxis();
		// change axis properties...
		return chart;
	}

}
