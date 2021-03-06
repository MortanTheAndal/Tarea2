package tarea2;
import java.util.ArrayList;

public class ClassicHeap implements PriorityQueue{
    public ArrayList<Node> heap;
    public int heap_length;

    public ClassicHeap(){
         this.heap = new ArrayList<>();
         this.heap_length =  heap.size();
    }

    public ClassicHeap(ArrayList<Node> list){
        this.heap = list;
        this.heap_length = list.size();
        this.heap = this.heapify();
    }

    @Override
    public void insertar(int x, int p) {
        this.heap.add(new Node(x,p));
        this.heap_length =  this.heap.size();
        emerger();
    }

    private void emerger() {
        for (int j = this.heap_length-1; j>1 && this.heap.get(j).prioridad > this.heap.get(j/2).prioridad; j /= 2){
            this.swap(j,j/2);
        }
    }

    @Override
    public int extraer_siguiente() {
        if (this.heap_length == 1){
            int result = this.heap.get(0).valor;
            this.heap.remove(this.heap_length- 1);
            this.heap_length--;
            return result;
        }
        Node first = this.heap.get(0);
        Node last = this.heap.get(this.heap_length - 1);
        this.heap.remove(this.heap_length- 1);
        this.heap.set(0, last);
        this.heap_length--;
        buildHeap(0);
        return first.valor;
    }

    public ArrayList<Node> meld(ClassicHeap c1, ClassicHeap c2) {
        c1.heap.addAll(c2.heap);
        this.heap = c1.heap;
        this.heap_length = this.heap.size();
        return this.heapify();
    }

    private ArrayList<Node> heapify(){
        for(int i = this.heap.size()/2 -1; i>=0;i--){
            buildHeap(i);
        }
        return this.heap;
    }
    private void buildHeap(int index) {
        int largest = index;
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 *index + 2;
        if(leftIndex < this.heap_length && this.heap.get(index).prioridad <this.heap.get(leftIndex).prioridad){
            largest = leftIndex;
        }
        if(rightIndex < this.heap_length && this.heap.get(index).prioridad <this.heap.get(rightIndex).prioridad){
            largest = rightIndex;
        }
        if(largest != index){
            swap(index,largest);
            buildHeap(largest);
        }
    }

    private void swap(int first, int second){
        Node temp = this.heap.get(first);
        heap.set(first, heap.get(second));
        heap.set(second, temp);
    }
}
