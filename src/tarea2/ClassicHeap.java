package tarea2;
import java.util.ArrayList;

public class ClassicHeap implements PriorityQueue{
    private ArrayList<Node> heap;
    private int heap_length;
    public ClassicHeap(){
         this.heap = new ArrayList<>();
         this.heap_length =  heap.size();
    }

    public ClassicHeap(ArrayList<Node> list){
        this.heap = list;
        this.heap_length = list.size();
    }
    @Override
    public void insertar(int x, int p) {
        Node new_node = new Node(x,p);
        this.heap.add(new_node);
        this.heap_length =  heap.size();
        emerger(this.heap);
    }

    private void emerger(ArrayList<Node> heap) {
        for (int j = heap_length-1; j>1 && heap.get(j).prioridad > heap.get(j/2).prioridad; j /= 2){
            Node temp = heap.get(j);
            heap.add(j, heap.get(j/2));
            heap.add(j/2, temp);
        }
    }

    @Override
    public int extraer_siguiente() {
        Node first = this.heap.get(0);
        Node last = this.heap.get(this.heap_length - 1);
        this.heap.remove(this.heap_length- 1);
        this.heap.add(0, last);
        this.heap_length =  heap.size();
        sumerger(this.heap);
        return first.valor;
    }

    private void sumerger(ArrayList<Node> heap) {
        int j = 0;
        while(2*j <= this.heap_length-1){
            int k = 2*j;
            if(k+1 <= heap_length-1 && heap.get(k+1).prioridad > heap.get(k).prioridad){
                k += 1;
            }
            if (heap.get(j).prioridad > heap.get(k).prioridad){
                break;
            }
            Node temp = heap.get(j);
            heap.add(j, heap.get(k));
            heap.add(k, temp);
            j=k;
        }
    }

    public ClassicHeap meld(ClassicHeap c1, ClassicHeap c2) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(Node node: c1.heap){
            list.add(node.valor);
        }
        for(Node node: c2.heap){
            list.add(node.valor);
        }
        return this.heapify(list);
    }

    public ClassicHeap heapify(ArrayList<Integer> list) {
        return null;
    }
}
