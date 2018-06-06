package tarea2;

public interface PriorityQueue {
    void insertar(int x, int p);
    int extraer_siguiente();
    PriorityQueue meld (PriorityQueue c0, PriorityQueue c1);
}
