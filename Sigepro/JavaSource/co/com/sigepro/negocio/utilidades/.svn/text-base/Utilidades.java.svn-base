package co.com.sigepro.negocio.utilidades;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Id;

public class Utilidades {

	public enum TipoOperacion {
		COPIA, REPLICA_TOTAL, NO_COPIAR, REPLICA_PARCIAL
	};

	public static void copiarObjeto(Object fuente, Object destino,
			HashMap<String, TipoOperacion> propiedades) {
		String nombreFuente = fuente.getClass().getSimpleName().toLowerCase();
		Field[] atributos = fuente.getClass().getDeclaredFields();
		for (Field campoOrigen : atributos) {
			System.out.println("Objeto " + nombreFuente + " tipo "
					+ campoOrigen.getType().getCanonicalName() + " nombre "
					+ campoOrigen.getName());
			String nombrePropiedad = nombreFuente + "." + campoOrigen.getName();

			try {
				campoOrigen.setAccessible(true);

				if (campoOrigen.getAnnotation(Id.class) == null) {
					if (!campoOrigen.getType().getCanonicalName()
							.contains("co.com.sigepro")) {
						if (campoOrigen.get(fuente) instanceof List) {
							if (propiedades.get(nombrePropiedad) != null) {
								Field campoDestino = destino
										.getClass()
										.getDeclaredField(campoOrigen.getName());
								campoDestino.setAccessible(true);
								List<?> listaFuente = (List<?>) campoOrigen
										.get(fuente);
								@SuppressWarnings("unchecked")
								List<Object> listaDestino = List.class
										.cast(campoDestino.get(destino));
								for (Object valor : listaFuente) {
									if (propiedades.get(nombrePropiedad) == TipoOperacion.REPLICA_TOTAL) {
										Object nuevoValor = valor.getClass()
												.newInstance();
										copiarObjeto(valor, nuevoValor,
												propiedades);
										try {
											Field campoPadre = nuevoValor
													.getClass()
													.getDeclaredField(
															nombreFuente);
											campoPadre.setAccessible(true);
											campoPadre.set(nuevoValor, destino);
										} catch (Exception e) {
											e.printStackTrace();
										}

										listaDestino.add(nuevoValor);
									} else if (propiedades.get(nombrePropiedad) == TipoOperacion.COPIA) {
										listaDestino.add(valor);
									}

								}
							}
						} else {
							Field campoDestino = destino.getClass()
									.getDeclaredField(campoOrigen.getName());
							campoDestino.setAccessible(true);
							campoDestino.set(destino, campoOrigen.get(fuente));
						}
					} else {
						if (propiedades.get(nombrePropiedad) != null) {
							Field campoDestino = destino.getClass()
									.getDeclaredField(campoOrigen.getName());
							campoDestino.setAccessible(true);
							if (campoOrigen.get(fuente) != null
									&& propiedades.get(nombrePropiedad) == TipoOperacion.REPLICA_TOTAL) {
								Object nuevoValor = campoDestino.getType()
										.newInstance();
								copiarObjeto(campoOrigen.get(fuente),
										nuevoValor, propiedades);
								campoDestino.set(destino, nuevoValor);
							} else if (campoOrigen.get(fuente) != null
									&& propiedades.get(nombrePropiedad) == TipoOperacion.REPLICA_PARCIAL) {
								Object nuevoValor = campoDestino.getType()
										.newInstance();
								copiarObjeto(
										campoOrigen.get(fuente),
										nuevoValor,
										new HashMap<String, Utilidades.TipoOperacion>());
								campoDestino.set(destino, nuevoValor);
							} else if (campoOrigen.get(fuente) != null
									&& propiedades.get(nombrePropiedad) == TipoOperacion.COPIA) {
								campoDestino.set(destino,
										campoOrigen.get(fuente));
							}

						}

					}
				}
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			} catch (IllegalAccessException e) {
				System.out.println(e.getMessage());
			} catch (SecurityException e) {
				System.out.println(e.getMessage());
			} catch (NoSuchFieldException e) {
				System.out.println(e.getMessage());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void copiarObjeto(Object fuente, Object destino) {
		String nombreFuente = fuente.getClass().getSimpleName().toLowerCase();
		Field[] atributos = fuente.getClass().getDeclaredFields();
		for (Field campoOrigen : atributos) {
			System.out.println("Objeto " + nombreFuente + " tipo "
					+ campoOrigen.getType().getCanonicalName() + " nombre "
					+ campoOrigen.getName());

			try {
				campoOrigen.setAccessible(true);

				if (!campoOrigen.getType().getCanonicalName()
						.contains("co.com.sigepro")) {
					if (!(campoOrigen.get(fuente) instanceof List)) {
						Field campoDestino = destino.getClass()
								.getDeclaredField(campoOrigen.getName());
						campoDestino.setAccessible(true);
						campoDestino.set(destino, campoOrigen.get(fuente));
					}

				}

			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			} catch (IllegalAccessException e) {
				System.out.println(e.getMessage());
			} catch (SecurityException e) {
				System.out.println(e.getMessage());
			} catch (NoSuchFieldException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static Date sumarHoras(Date fecha, int cantidad) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.HOUR_OF_DAY, cantidad);
		return calendar.getTime();
	}

	public static int restaFechas(GregorianCalendar fechaInicial,
			GregorianCalendar fechaFinal) {
		if (fechaInicial.get(Calendar.YEAR) == fechaFinal.get(Calendar.YEAR)) {
			return fechaFinal.get(Calendar.DAY_OF_YEAR)
					- fechaInicial.get(Calendar.DAY_OF_YEAR);
		} else {
			/*
			 * SI ESTAMOS EN DISTINTO ANYO COMPROBAMOS QUE EL ANYO DEL DATEINI
			 * NO SEA BISIESTO SI ES BISIESTO SON 366 DIAS EL ANYO SINO SON 365
			 */
			int diasAnyo = fechaInicial.isLeapYear(fechaInicial
					.get(Calendar.YEAR)) ? 366 : 365;

			/* CALCULAMOS EL RANGO DE ANYOS */
			int rangoAnyos = fechaFinal.get(Calendar.YEAR)
					- fechaInicial.get(Calendar.YEAR);

			/* CALCULAMOS EL RANGO DE DIAS QUE HAY */
			int rango = (rangoAnyos * diasAnyo)
					+ (fechaFinal.get(Calendar.DAY_OF_YEAR) - fechaInicial
							.get(Calendar.DAY_OF_YEAR));

			return rango;
		}

	}
}
