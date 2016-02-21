package co.com.sigepro.control;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.richfaces.model.TreeNode;

import co.com.sigepro.entidades.Nodo;

public class TreeNodoImpl implements TreeNode<Nodo<?>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TreeMap<Object, TreeNode<Nodo<?>>> childs = new TreeMap<Object, TreeNode<Nodo<?>>>();
	private Nodo<?> nodo;
	private TreeNode<Nodo<?>> parent;
	private String id;

	public TreeNodoImpl() {
	}

	public TreeNodoImpl(Nodo<?> nodo) {
		this.nodo = nodo;
	}

	private Map<Object, TreeNode<Nodo<?>>> getChlids() {
		return this.childs;
	}

	public void addChild(Object identifier, TreeNode<Nodo<?>> child) {
		getChlids().put(identifier, child);
	}

	public TreeNode<Nodo<?>> getChild(Object id) {
		return (TreeNode<Nodo<?>>) getChlids().get(id);
	}

	public Iterator<Entry<Object, TreeNode<Nodo<?>>>> getChildren() {
		return getChlids().entrySet().iterator();
	}

	public boolean isLeaf() {
		return getChlids().isEmpty();
	}

	public void removeChild(Object id) {
		getChlids().remove(id);
	}

	public Nodo<?> getData() {
		return nodo;
	}

	public void setData(Nodo<?> data) {
		nodo = data;
	}

	public void setData(String nombre) {
		nodo.setNombre(nombre);
	}

	public TreeNode<Nodo<?>> getParent() {
		return parent;
	}

	public void setParent(TreeNode<Nodo<?>> parent) {
		this.parent = parent;
	}

	public String getType() {
		return "library";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Nodo<?> getNodo() {
		return nodo;
	}

	public void setNodo(Nodo<?> nodo) {
		this.nodo = nodo;
	}

	public TreeMap<Object, TreeNode<Nodo<?>>> getChilds() {
		return childs;
	}

	public void setChilds(TreeMap<Object, TreeNode<Nodo<?>>> childs) {
		this.childs = childs;
	}

}