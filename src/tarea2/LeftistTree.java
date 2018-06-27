package tarea2;

import java.util.ArrayList;

public class LeftistTree implements PriorityQueue {
    private Node root;
    public LeftistTree(){
        this.root = null;
    }

    public LeftistTree(ArrayList <Node> list){
        this.root = null;
        this.heapify(list);
    }

    private Node merge(Node a, Node b){
        if(a == null){
            return b;
        }
        if(b == null){
            return a;
        }
        if(a.valor < b.valor){
            return merge(b,a);
        }
        a.right = merge(a.right, b);
        if (a.left == null){
            a.left = b.left;
            a.right = null;
        }
        else{
            if(a.left.prioridad < a.right.prioridad){
                Node temp = a.left;
                a.left = a.right;
                a.right = temp;
            }
            a.prioridad = a.right.prioridad + 1;
        }
        return a;
    }

    @Override
    public void insertar(int x, int p) {
        this.root = this.merge(this.root, new Node(x,p));
    }

    @Override
    public int extraer_siguiente() {
        int val = this.root.valor;
        this.root = this.merge(this.root.left, this.root.right);
        return val;
    }

    public LeftistTree meld(LeftistTree a, LeftistTree b){
        if(a == b){
            return this;
        }
        this.root = merge(a.root, b.root);
        return this;
    }

    private void heapify(ArrayList<Node> list) {
        for (Node node:list){
            this.root = merge(this.root, node);
        }
    }


}
