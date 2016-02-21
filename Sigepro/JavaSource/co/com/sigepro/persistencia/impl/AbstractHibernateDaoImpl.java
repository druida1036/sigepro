package co.com.sigepro.persistencia.impl;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import co.com.sigepro.persistencia.util.SessionUtil;

/**
 * @author Jorge Martinez
 * 
 * @param <T>
 * @param <ID>
 */
public abstract class AbstractHibernateDaoImpl<T extends Serializable, ID extends Serializable>
		extends HibernateDaoSupport {

	private Class<T> claseConcreta;
	private Session session;
	private DetachedCriteria criteria;
	@Autowired
	private SessionUtil sessionUtil;

	@SuppressWarnings("unchecked")
	public AbstractHibernateDaoImpl() {
		super();
		ParameterizedType parameterizedType = ParameterizedType.class
				.cast(getClass().getGenericSuperclass());
		Type type = parameterizedType.getActualTypeArguments()[0];
		claseConcreta = (Class<T>) type;
	}

	/**
	 * Metodo que permite inicializar el acceso y las operaciones de base de
	 * datos.
	 * 
	 * @param sessionFactory
	 *            Elemento de hibernate que contine todas las operaciones de
	 *            base de datos
	 */
	@Autowired
	public void setFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
		session = sessionUtil.openSession();
	}

	public Class<T> getClaseConcreta() {
		return claseConcreta;
	}

	/**
	 * 
	 */

	public T cargar(ID id) {
		Transaction tx = null;
		try {
			if (!session.isOpen()) {
				session = sessionUtil.getCurrentSession();
			}
			tx = session.beginTransaction();
			T entidad = claseConcreta.cast(session.get(claseConcreta, id));
			session.refresh(entidad);
			tx.commit();
			return entidad;
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			session.disconnect();
		}

	}

	public List<T> listado(T entidad) {
		if (!session.isOpen()) {
			session = sessionUtil.getCurrentSession();
		}
		if (entidad == null)
			try {
				entidad = claseConcreta.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		List<T> listado = new ArrayList<T>();
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			createCriteriaLike(entidad);
			for (Object valor : criteria.getExecutableCriteria(session).list()) {
				listado.add((claseConcreta.cast(valor)));
				session.evict(valor);
			}

			tx.commit();
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			session.disconnect();
		}

		return listado;
	}

	public void actualizar(T entidad) {
		if (!session.isOpen()) {
			session = sessionUtil.getCurrentSession();
		}
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			tx.begin();
			session.update(entidad);
			tx.commit();
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
	}

	public T guardar(T entidad) {
		if (!session.isOpen()) {
			session = sessionUtil.getCurrentSession();
		}
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(entidad);
			tx.commit();
//			refrescar(entidad);

		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}

		return entidad;
	}

	/**
	 * Este metodo se encargar de mezclar dos objetos que haya cargado en una
	 * session diferente a la de trabajo
	 * 
	 * @param entidad
	 */
	public void mezclar(Object entidad) {
		if (!session.isOpen()) {
			session = sessionUtil.getCurrentSession();
		}
		Transaction tx = session.getTransaction();
		if (tx != null && tx.isActive()) {
			tx.rollback();
			tx = null;
		}
		tx = session.beginTransaction();

		session.merge(entidad);
		tx.commit();
	}

	public void evict(T entidad) {
		if (!session.isOpen()) {
			session = sessionUtil.getCurrentSession();
		}
		session.evict(entidad);
	}

	public void borrar(T entidad) {
		if (!session.isOpen()) {
			session = sessionUtil.getCurrentSession();
		}
		Transaction tx = session.beginTransaction();
		session.delete(entidad);
		tx.commit();
	}

	public void borrarPorIdentificador(ID id) {
		Transaction tx = null;
		try {
			Object obj = cargar(id);
			if (!session.isOpen()) {
				session = sessionUtil.getCurrentSession();
			}
			tx = session.beginTransaction();
			session.delete(obj);
			tx.commit();
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
	}

	public void borrarTodos() {
		getHibernateTemplate().execute(new HibernateCallback<T>() {
			public T doInHibernate(Session session) throws HibernateException {
				String hqlDelete = "delete " + claseConcreta.getName();
				int deletedEntities = session.createQuery(hqlDelete)
						.executeUpdate();
				System.out
						.println("Total registros borrados" + deletedEntities);
				return null;
			}

		});
	}

	public T replicar(ID id) {
		T entidad = cargar(id);
		Transaction tx = null;
		try {
			if (!session.isOpen()) {
				session = sessionUtil.getCurrentSession();
			}
			tx = session.beginTransaction();
			if (entidad != null) {
				session.replicate(entidad, ReplicationMode.IGNORE);
			}
			tx.commit();
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		return entidad;
	}

	public void refrescar(T entidad) {
		Transaction tx = null;
		try {
			if (!session.isOpen()) {
				session = sessionUtil.getCurrentSession();
			}
			tx = session.beginTransaction();
			if (entidad != null) {
				session.refresh(entidad);
			}
			tx.commit();
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
	}
	public int count() {

		return listado(null).size();
	}

	public List<T> ejecutarConsulta(String consulta, T parametro) {
		Transaction tx = null;
		List<T> resultados = new ArrayList<T>();
		try {
			if (!session.isOpen()) {
				session = sessionUtil.getCurrentSession();
			}
			tx = session.beginTransaction();
			Query query = session.createQuery(consulta);
			if (parametro != null)
				query.setProperties(parametro);
			for (Object valor : query.list()) {
				resultados.add(claseConcreta.cast(valor));
			}
			tx.commit();
			return resultados;
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}

	}

	public List<T> ejecutarConsulta(String consulta,
			Map<String, Object> parametros) {
		Transaction tx = null;
		List<T> resultados = new ArrayList<T>();
		try {
			if (!session.isOpen()) {
				session = sessionUtil.getCurrentSession();
			}
			tx = session.beginTransaction();
			Query query = session.createQuery(consulta);
			query.setCacheable(false);
			if (parametros != null)
				query.setProperties(parametros);
			for (Object valor : query.list()) {
				resultados.add(claseConcreta.cast(valor));
			}
			tx.commit();
			return resultados;
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> ejecutarConsulta1(String consulta,
			Map<String, Object> parametros) {
		Transaction tx = null;
		List<Object[]> resultados = new ArrayList<Object[]>();
		try {
			if (!session.isOpen()) {
				session = sessionUtil.getCurrentSession();
			}
			tx = session.beginTransaction();
			Query query = session.createQuery(consulta);
			query.setCacheable(false);
			if (parametros != null)
				query.setProperties(parametros);
			resultados = query.list();
			tx.commit();
			return resultados;
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
	}

	public List<T> ejecutarConsulta(String consulta,
			Map<String, Object> parametros, int primerResultado,
			int maxResultados) {
		Transaction tx = null;
		List<T> resultados = new ArrayList<T>();
		try {
			if (!session.isOpen()) {
				session = sessionUtil.getCurrentSession();
			}
			tx = session.beginTransaction();
			Query query = session.createQuery(consulta);
			if (parametros != null)
				query.setProperties(parametros);
			query.setFirstResult(primerResultado);
			query.setMaxResults(maxResultados);
			for (Object valor : query.list()) {
				resultados.add(claseConcreta.cast(valor));
			}
			tx.commit();
			return resultados;
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
	}

	public T consultaUnicoResultado(String consulta, T parametro) {
		Transaction tx = null;
		T resultados = null;
		try {
			if (!session.isOpen()) {
				session = sessionUtil.getCurrentSession();
			}
			tx = session.beginTransaction();
			Query query = session.createQuery(consulta);
			if (parametro != null)
				query.setProperties(parametro);
			resultados = claseConcreta.cast(query.uniqueResult());
			tx.commit();
			return resultados;
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}

	}

	public T consultaUnicoResultado(String consulta,
			Map<String, Object> parametros) {
		Transaction tx = null;
		T resultados = null;
		try {
			if (!session.isOpen()) {
				session = sessionUtil.getCurrentSession();
			}
			tx = session.beginTransaction();
			Query query = session.createQuery(consulta);
			if (parametros != null)
				query.setProperties(parametros);
			resultados = claseConcreta.cast(query.uniqueResult());
			tx.commit();
			return resultados;
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}

	}

	@SuppressWarnings("unchecked")
	public List<T> busquedaPorCriterio(Criterion... p_criterion) { // varargs
		Criteria crit = getSessionFactory().getCurrentSession().createCriteria(
				claseConcreta.getClass());
		for (Criterion c : p_criterion) {
			crit.add(c);
		}
		return crit.list();
	}

	protected void createCriteria(Object criterio) {
		// Objeto de Hibernate que se encarga de armar el query de busqueda
		// dinamicamente
		criteria = DetachedCriteria.forClass(criterio.getClass());
		PropertyDescriptor origDescriptors[] = PropertyUtils
				.getPropertyDescriptors(criterio);

		for (int i = 0; i < origDescriptors.length; i++) {
			String name = origDescriptors[i].getName();
			try {
				Object tmp = PropertyUtils.getSimpleProperty(criterio, name);
				if (tmp != null && !name.equals("class")
						&& !(tmp instanceof List)) {

					if (name.equals("pk"))
						createCriteria(criteria, tmp);
					else if (tmp instanceof String && tmp.equals("")) {
					} else
						criteria.add(Restrictions.eq(name, tmp));
				}

			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	protected void createCriteriaLike(Object criterio) {
		// Objeto de Hibernate que se encarga de armar el query de busqueda
		// dinamicamente
		criteria = DetachedCriteria.forClass(criterio.getClass());
		PropertyDescriptor origDescriptors[] = PropertyUtils
				.getPropertyDescriptors(criterio);

		for (int i = 0; i < origDescriptors.length; i++) {
			String name = origDescriptors[i].getName();
			try {
				Object tmp = PropertyUtils.getSimpleProperty(criterio, name);
				if (tmp != null && !name.equals("class")
						&& !(tmp instanceof List)) {

					if (name.equals("pk"))
						createCriteria(criteria, tmp);
					else if (tmp instanceof String && tmp.equals("")) {

					} else if (tmp.getClass().getPackage().getName()
							.equals("co.com.sigepro.entidades")) {
					} else {
						criteria.add(Restrictions.ilike(name, tmp.toString(),
								MatchMode.ANYWHERE));
					}
				}

			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	protected void createCriteria(DetachedCriteria criteria, Object criterio) {
		PropertyDescriptor origDescriptors[] = PropertyUtils
				.getPropertyDescriptors(criterio);

		for (int i = 0; i < origDescriptors.length; i++) {
			String name = origDescriptors[i].getName();
			try {
				Object tmp = PropertyUtils.getSimpleProperty(criterio, name);
				if (tmp != null && !name.equals("class")
						&& !(tmp instanceof List)) {
					name = "pk." + name;
					System.out.println(tmp.toString());
					criteria.add(Restrictions.eq(name, tmp));
				}

			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	protected void createCriterialike(DetachedCriteria criteria, Object criterio) {
		PropertyDescriptor origDescriptors[] = PropertyUtils
				.getPropertyDescriptors(criterio);

		for (int i = 0; i < origDescriptors.length; i++) {
			String name = origDescriptors[i].getName();
			try {
				Object tmp = PropertyUtils.getSimpleProperty(criterio, name);
				if (tmp != null && !name.equals("class")
						&& !(tmp instanceof List)) {
					name = "pk." + name;
					System.out.println(tmp.toString());
					criteria.add(Restrictions.ilike(name, tmp.toString(),
							MatchMode.ANYWHERE));
				}

			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public DetachedCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(DetachedCriteria criteria) {
		this.criteria = criteria;
	}

}
