package binomial;

public class BinTree {
	/*
	 * Nodos de Arboles Binomiales. Un nodo tiene un elemento, prioridad y nivel.
	 * Como un nodo de arbol binomial puede tener un numero de hijos variable, Se
	 * utiliza la convención de que un nodo padre solo recuerda al primer hijo, Y
	 * este hace referencia al resto de sus hermanos.
	 */
	protected BinTree parent, child, bro;
	protected int element, prior, level;

	public BinTree(int e, int n) {
		prior = n;
		element = e;
		parent = null;
		child = null;
		bro = null;
		level = 0;
	}

	

}
