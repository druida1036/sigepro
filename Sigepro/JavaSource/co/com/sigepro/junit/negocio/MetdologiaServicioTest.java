package co.com.sigepro.junit.negocio;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.com.sigepro.negocio.serv.MetodologiaServicio;

/**
 * @author Jorge Martinez
 *
 */
/**
 * @author Jorge Martinez
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/co/com/sigepro/junit/configuracion/applicationContextPruebas.xml" })
public class MetdologiaServicioTest {
	@Autowired
	private MetodologiaServicio metodologiaServicio;

	@Test
	public void copiarMetodologia() {
		// Metodologia metodologia = metodologiaServicio.cargar(1);
		// Metodologia copia = metodologiaServicio.copiar(metodologia,
		// metodologia.getNombre()+"-"+ "copia");
		// System.out.println(copia.getNombre());
		metodologiaServicio.borrar(metodologiaServicio.cargar(10));
		metodologiaServicio.borrar(metodologiaServicio.cargar(9));

	}

	public static void main(String[] args) {
		String descripcion = "Señor(a) #usuario su nuevo usuario es: #login y su clave: 123456 favor verificar la informacion en el sistema. ";
		descripcion.replaceAll("#usuario", "jonas");
	    System.out.println(descripcion);

	}
}
