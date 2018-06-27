package tarea2;

public class Node {
    int valor;
    int prioridad;
    Node left;
    Node right;

    public Node(int x, int p){
        this.valor = x;
        this.prioridad = p;
        this.left = null;
        this.right = null;
        }
}
