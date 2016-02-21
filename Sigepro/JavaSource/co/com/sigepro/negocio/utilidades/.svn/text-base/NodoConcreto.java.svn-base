package co.com.sigepro.negocio.utilidades;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un nodo de la clase Arbol<T> . El NodoConcreto<T> is also a container, and
 * can be thought of as instrumentation to determine the location of the type T
 * in the Tree<T>.
 */
public class NodoConcreto<T> {
 
    private T dato;
    private String nombre;
    private List<NodoConcreto<T>> hijos;
 
    /**
     * Constructor por defecto.
     */
    public NodoConcreto() {
        super();
    }
 
    /**
     * Convenience ctor to create a Node<T> with an instance of T.
     * @param data an instance of T.
     */
    public NodoConcreto(T dato) {
        this();
        setDato(dato);
    }
     
    /**
     * Return the children of Node<T>. The Tree<T> is represented by a single
     * root Node<T> whose children are represented by a List<Node<T>>. Each of
     * these Node<T> elements in the List can have children. The getChildren()
     * method will return the children of a Node<T>.
     * @return the children of Node<T>
     */
    public List<NodoConcreto<T>> getHijos() {
        if (this.hijos == null) {
            return new ArrayList<NodoConcreto<T>>();
        }
        return this.hijos;
    }
 
    /**
     * Sets the children of a Node<T> object. See docs for getChildren() for
     * more information.
     * @param children the List<Node<T>> to set.
     */
    public void setHijos(List<NodoConcreto<T>> children) {
        this.hijos = children;
    }
 
    /**
     * Returns the number of immediate children of this Node<T>.
     * @return the number of immediate children.
     */
    public int getNumberOfChildren() {
        if (hijos == null) {
            return 0;
        }
        return hijos.size();
    }
     
    /**
     * Adds a child to the list of children for this Node<T>. The addition of
     * the first child will create a new List<Node<T>>.
     * @param child a Node<T> object to set.
     */
    public void addChild(NodoConcreto<T> child) {
        if (hijos == null) {
            hijos = new ArrayList<NodoConcreto<T>>();
        }
        hijos.add(child);
    }
     
    /**
     * Inserts a Node<T> at the specified position in the child list. Will     * throw an ArrayIndexOutOfBoundsException if the index does not exist.
     * @param index the position to insert at.
     * @param child the Node<T> object to insert.
     * @throws IndexOutOfBoundsException if thrown.
     */
    public void insertChildAt(int index, NodoConcreto<T> child) throws IndexOutOfBoundsException {
        if (index == getNumberOfChildren()) {
            // this is really an append
            addChild(child);
            return;
        } else {
            hijos.get(index); //just to throw the exception, and stop here
            hijos.add(index, child);
        }
    }
     
    /**
     * Remove the Node<T> element at index index of the List<Node<T>>.
     * @param index the index of the element to delete.
     * @throws IndexOutOfBoundsException if thrown.
     */
    public void removeChildAt(int index) throws IndexOutOfBoundsException {
        hijos.remove(index);
    }
 
    public T getDato() {
        return this.dato;
    }
 
    public void setDato(T dato) {
        this.dato = dato;
    }
     
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{").append(getDato().toString()).append(",[");
        int i = 0;
        for (NodoConcreto<T> e : getHijos()) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(e.getDato().toString());
            i++;
        }
        sb.append("]").append("}").append("/n");
        return sb.toString();
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
