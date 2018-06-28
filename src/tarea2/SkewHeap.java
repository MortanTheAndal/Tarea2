package tarea2;

import java.util.ArrayList;

public class SkewHeap implements PriorityQueue{
    private Node root;
    public int n_elem;
    public SkewHeap(){
        this.root = null;
        this.n_elem = 0;
    }

    public SkewHeap(ArrayList<Node> list){
        this.root = null;
        this.n_elem = list.size();
        this.heapify(list);
    }

    @Override
    public void insertar(int x, int p) {
        this.merge(this.root, new Node(x,p));
    }

    @Override
    public int extraer_siguiente() {
        int val = root.valor;
        this.root = merge(root.left, root.right);
        return val;
    }

    public SkewHeap meld(SkewHeap a, SkewHeap b){
        if(a == b){
            return this;
        }
        this.root = merge(a.root, b.root);
        return this;
    }

    private Node merge(Node a, Node b) {
        if(a == null){
            return b;
        }
        if(b == null){
            return a;
        }
        if (a.valor > b.valor){
            Node temp = a.left;
            a.left = merge(a.right, b);
            a.right = temp;
            return a;
        }
        else{
            Node temp = b.right;
            b.right = merge(b.left, a);
            b.left = temp;
            return b;
        }
    }

    private void heapify(ArrayList<Node> list) {
        for (Node node:list){
            this.root = merge(node,this.root);
        }
    }
}
