package tarea2;

import java.util.ArrayList;

public class ClassicHeap implements PriorityQueue{
    private ArrayList<Integer> heap;
    public ClassicHeap(){
         this.heap = new ArrayList<Integer>();
    }

    @Override
    public void insertar(int x, int p) {
        this.heap.add(x);
        emerger(this.heap, x, p);
    }

    private void emerger(ArrayList<Integer> heap, int x, int p) {

    }

    @Override
    public int extraer_siguiente() {
        int first = this.heap.get(0);
        int last = this.heap.get(this.heap.size() - 1);
        this.heap.remove(this.heap.size() - 1);
        this.heap.add(0, last);
        sumerger(this.heap);
        return first;
    }

    private void sumerger(ArrayList<Integer> heap) {
    }

    @Override
    public PriorityQueue meld(PriorityQueue c0, PriorityQueue c1) {
        return null;
    }
}
