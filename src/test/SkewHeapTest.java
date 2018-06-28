package test;

import junit.framework.TestCase;
import org.junit.Test;
import tarea2.DataGenerator;
import tarea2.Node;
import tarea2.SkewHeap;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class SkewHeapTest extends TestCase {
    private DataGenerator generator;
    private PrintWriter writer;
    public void setUp() throws Exception {
        super.setUp();
        generator = new DataGenerator();
    }
    @Test
    public void testSort() throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter("Results/SkewHeap_Sorting.csv", "UTF-8");
        for(int i = 15; i<=21; i++){
            ArrayList<Integer> data = generator.getData(i);
            ArrayList<Node> nodeList = new ArrayList<Node>();
            for (int j: data){
                Node to_insert = new Node(j,0);
                nodeList.add(to_insert);
            }
            System.out.println("Start Heapify");
            long heapify_start = System.currentTimeMillis() % 1000;
            System.out.println(nodeList.size());
            SkewHeap heap = new SkewHeap(nodeList);
            long heapify_finish = System.currentTimeMillis() % 1000;
            System.out.println("End Heapify");
            ArrayList<Integer> sorted_data = new ArrayList<Integer>();
            System.out.println("Start Sorting");
            long extraction_start = System.currentTimeMillis() % 1000;
            int length = heap.n_elem;
            System.out.println(length);
            for(int k =0 ; k< length;k++){
                sorted_data.add(heap.extraer_siguiente());
            }
            long extraction_finish = System.currentTimeMillis() % 1000;
            System.out.println("Stop Sorting");
            writer.println(i +";"+ (heapify_finish - heapify_start) + ";" + (extraction_finish - extraction_start));
        }
        writer.close();
    }

    @Test
    public void testInsertionAndMelding() throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter("Results/SkewHeap_InsertionAndMelding.csv", "UTF-8");
        ArrayList<Integer> n = generator.getData(20);
        for(int i=0;i<16;i++){
            ArrayList<SkewHeap> heap_list=  new ArrayList<SkewHeap>();
            int n_of_heaps = (int) Math.pow(2,20-i);
            int index = 0;
            System.out.println("Start insertions");
            long insertion_start = System.currentTimeMillis()%1000;
            for(int j = 0; j<n_of_heaps; j++){
                SkewHeap heap = new SkewHeap();
                for(int k=0; k < Math.pow(2,i);k++){
                    int to_insert = n.get(index);
                    heap.insertar(to_insert,1);
                    index++;
                }
                heap_list.add(heap);
            }

            long insertion_finish = System.currentTimeMillis()%1000;
            System.out.println("Finish insertions");
            System.out.println("Start meldings");
            long melding_start = System.currentTimeMillis()%1000;
            while (heap_list.size() > 1){
                ArrayList<SkewHeap> aux = new ArrayList<SkewHeap>();
                for(int j=0; j<heap_list.size(); j=j+2){
                    SkewHeap heap = new SkewHeap();
                    aux.add(heap.meld(heap_list.get(j),heap_list.get(j+1)));
                }
                heap_list = aux;
            }
            long melding_finish = System.currentTimeMillis()%1000;
            System.out.println("Finish meldings");
            assertEquals(1, heap_list.size());
            writer.println(i +";"+ (insertion_finish - insertion_start) + ";" + (melding_finish - melding_start));
        }
        writer.close();
    }
}