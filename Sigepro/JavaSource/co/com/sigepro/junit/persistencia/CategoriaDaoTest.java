package co.com.sigepro.junit.persistencia;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.com.sigepro.entidades.Actividad;
import co.com.sigepro.negocio.utilidades.Utilidades.TipoOperacion;
import co.com.sigepro.persistencia.dao.ActividadDao;
import co.com.sigepro.persistencia.dao.MetodologiaDao;

/**
 * @author Luis Francisco Fontalvo Romero (lfontalvo@aliaddos.com)
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/co/com/sigepro/junit/configuracion/applicationContextPruebas.xml" })
public class CategoriaDaoTest {

	@Autowired
	private ActividadDao actividadDao;
	@Autowired
	private MetodologiaDao metodologiaDao;

	@Test
	public void consultaTest() {
		metodologiaDao.borrarPorIdentificador(8);

		HashMap<String, TipoOperacion> propiedades = new HashMap<String, TipoOperacion>();
		propiedades.put("metodologia.categorias", TipoOperacion.REPLICA_TOTAL);
		propiedades.put("categoria.procesos", TipoOperacion.REPLICA_TOTAL);
		propiedades.put("proceso.actividades", TipoOperacion.REPLICA_TOTAL);
		propiedades.put("actividad.rol", TipoOperacion.REPLICA_TOTAL);
		propiedades.put("actividad.plantillas", TipoOperacion.REPLICA_TOTAL);
		propiedades.put("plantilla.documento", TipoOperacion.REPLICA_TOTAL);
		// padres
		// propiedades.put("categoria.metodologia",
		// TipoOperacion.REPLICA_PARCIAL);
		// propiedades.put("proceso.categoria", TipoOperacion.REPLICA_PARCIAL);
		// propiedades.put("actividad.proceso", TipoOperacion.REPLICA_PARCIAL);
		// propiedades.put("plantilla.actividad",
		// TipoOperacion.REPLICA_PARCIAL);
		propiedades.put("actividad.precedentes", TipoOperacion.REPLICA_TOTAL);

		// relaciones con objetos quedeben mantenerse no crearse
		propiedades.put("actividad.notificaciones", TipoOperacion.COPIA);
		propiedades.put("actividad.herramientas", TipoOperacion.COPIA);

		// Utilidades.copiarObjeto(metodologia, metodologia1, propiedades);
		// metodologia1.setNombre("copia-"+metodologia1.getId()+"-"+metodologia.getNombre());
		// try {
		// metodologiaDao.guardar(metodologia1);
		// } catch (Exception e) {
		// TODO: handle exception
		// e.printStackTrace();
		// }
		for (Actividad actividad : actividadDao.actividadesPorMetodologia(1)) {
			Actividad actividadCopia = actividadDao.actividadPorMetodologia(8,"1",
					actividad.getNombre());
			for (Actividad precedente : actividad.getPrecedentes()) {
				Actividad precedenteCopia = actividadDao.actividadPorMetodologia(8,"1",
						precedente.getNombre());
				actividadCopia.getPrecedentes().add(precedenteCopia);
			}
			actividadDao.guardar(actividadCopia);
		}
		boolean matcher = Pattern.matches(" ", "[^ ]{2}");
		System.out.println(matcher);

	}

	public static void main(String[] args) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY)+24);
		
		System.out.println(calendar.getTime());
	}

}
