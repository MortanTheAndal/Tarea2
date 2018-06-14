package test;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import tarea2.ClassicHeap;
import tarea2.DataGenerator;
import tarea2.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class ClassicHeapTest extends TestCase {
    private DataGenerator generator;
    private PrintWriter writer;
    @Before
    public void setUp() throws Exception {
        super.setUp();
        generator = new DataGenerator();
    }

    @Test
    public void testSort() throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter("Results/ClassicHeap_Sorting.csv");
        for(int i = 15; i<=21; i++){
            ArrayList<Integer> data = generator.getData(i);
            ArrayList<Node> nodeList = new ArrayList<Node>();
            for (int j: data){
                Node to_insert = new Node(j,j);
                nodeList.add(to_insert);
            }
            System.out.println("Start Heapify");
            long heapify_start = System.currentTimeMillis() % 1000;
            System.out.println(nodeList.size());
            ClassicHeap heap = new ClassicHeap(nodeList);
            long heapify_finish = System.currentTimeMillis() % 1000;
            System.out.println("End Heapify");
            ArrayList<Integer> sorted_data = new ArrayList<Integer>();
            System.out.println("Start Sorting");
            long extraction_start = System.currentTimeMillis() % 1000;
            int length = heap.heap_length;
            for(int k =0 ; k< length;k++){
                sorted_data.add(heap.extraer_siguiente());
            }
            long extraction_finish = System.currentTimeMillis() % 1000;
            System.out.println("Stop Sorting");
            assertEquals(0, heap.heap_length);
            writer.println(i +";"+ (heapify_finish - heapify_start) + ";" + (extraction_finish - extraction_start));
        }
        writer.close();
    }

    @Test
    public void testInsertionAndMelding() throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter("Results/ClassicHeap_InsertionAndMelding.csv", "UTF-8");
        ArrayList<Integer> n = generator.getData(20);
        for(int i=0;i<16;i++){
            ArrayList<ClassicHeap> heap_list=  new ArrayList<ClassicHeap>();
            //TODO: Insercion y melding
            int n_of_heaps = (int) Math.pow(2,20-i);
            int index = 0;
            System.out.println("Start insertions");
            long insertion_start = System.currentTimeMillis()%1000;
            for(int j = 0; j<n_of_heaps; j++){
                ClassicHeap heap = new ClassicHeap();
                for(int k=0; k < Math.pow(2,i);k++){
                    int to_insert = n.get(index);
                    heap.insertar(to_insert,to_insert);
                    index++;
                }
                heap_list.add(heap);
            }

            long insertion_finish = System.currentTimeMillis()%1000;
            System.out.println("Finish insertions");
            System.out.println("Start meldings");
            long melding_start = System.currentTimeMillis()%1000;
            while (heap_list.size() > 1){
                ArrayList<ClassicHeap> aux = new ArrayList<ClassicHeap>();
                for(int j=0; j<heap_list.size(); j=j+2){
                    ClassicHeap heap = new ClassicHeap();
                    heap.meld(heap_list.get(j),heap_list.get(j+1));
                    aux.add(heap);
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