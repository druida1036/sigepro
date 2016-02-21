package co.com.sigepro.junit.persistencia;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.com.sigepro.entidades.Metodologia;
import co.com.sigepro.persistencia.dao.MetodologiaDao;

/**
 * @author Luis Francisco Fontalvo Romero (lfontalvo@aliaddos.com)
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/co/com/sigepro/junit/configuracion/applicationContextPruebas.xml" })
public class ClienteDaoTest {

	@Autowired
	private MetodologiaDao metodologiaDao;
	@Autowired

	@Test
	public void consultaTest() {
		Metodologia metodologia = new Metodologia();
		metodologia.setNombre("copia");
		List<Metodologia> metodologias = metodologiaDao.listado(metodologia);
		for (Metodologia metodologia2 : metodologias) {
			metodologiaDao.borrar(metodologia2);
		}
//		Actividad actividad = actividadDao.cargar(new Integer(950));
//		try {
//			List<Actividad> actividades = actividadDao.listadoNoAsignadasPrecedendes(actividad);
//			for (Actividad actividad2 : actividades) {
//				System.out.println(actividad2.toString());
//			}
//			actividades.size();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
	}
}
