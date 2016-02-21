package co.com.sigepro.junit.persistencia;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.com.sigepro.persistencia.dao.ActividadDao;
import co.com.sigepro.persistencia.dao.MetodologiaDao;
import co.com.sigepro.persistencia.dao.ProyectoDao;

/**
 * @author Jorge Armando Martinez Sanchez (jamartinez@aliaddos.com)
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/co/com/sigepro/junit/configuracion/applicationContextPruebas.xml" })
public class MetodologiaDaoTest {

	
	@Autowired
	private MetodologiaDao metodologiaDao;
	@Autowired
	private ProyectoDao proyectoDao;
	
	@Autowired
	private ActividadDao actividadDao;

	@Test
	public void consultaTest() {
//		Metodologia parametro = new Metodologia();
//		parametro.setNombre("Pmi");
//		List<Metodologia> metodologias = metodologiaDao.metodologiasConcretas(parametro);
//		for (Metodologia metodologia : metodologias) {
//			System.out.println(metodologia.getNombre());
//		}
//		metodologiaDao.borrarPorIdentificador(5);
//		metodologiaDao.borrarPorIdentificador(6);
//		metodologiaDao.borrarPorIdentificador(7);
//		metodologiaDao.borrarPorIdentificador(8);
//		metodologiaDao.borrarPorIdentificador(9);
		
		
		proyectoDao.borrarPorIdentificador(8);
		

//		actividadDao.noPrecedidas(14);
	}


}
