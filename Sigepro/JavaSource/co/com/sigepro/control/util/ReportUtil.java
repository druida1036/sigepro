package co.com.sigepro.control.util;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import co.com.sigepro.excepcion.AplicacionExcepcion;

public class ReportUtil {



	public static byte[] getReportePdf(Map<String, Object> infoReporte,
			Object dataSource) throws JRException,
			AplicacionExcepcion {
		// definiendo la ruta del archivo jasper
		String nombreReporte = infoReporte.get(Constantes.REPORT_NAME)
				+ ".jasper";

		// obteniendo el reporte dada la ruta de ubicacion
		InputStream reportStream = ReportUtil.class.getClassLoader()
				.getResourceAsStream(nombreReporte);

		JasperPrint print;

		if (dataSource instanceof Connection) {
			print = JasperFillManager.fillReport(reportStream, infoReporte,
					(Connection) dataSource);

		} else if (dataSource instanceof JRMapCollectionDataSource) {
			print = JasperFillManager.fillReport(reportStream, infoReporte,
					(JRMapCollectionDataSource) dataSource);
		} else {
			print = JasperFillManager.fillReport(reportStream, infoReporte,
					(JRBeanCollectionDataSource) dataSource);
		}
		// generando los bytes del reporte para pdf
		return JasperExportManager.exportReportToPdf(print);
		
	}

}
