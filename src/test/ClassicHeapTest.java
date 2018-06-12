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
    DataGenerator generator;
    ClassicHeap heap_a;
    PrintWriter writer;
    @Before
    public void setUp() throws Exception {
        super.setUp();
        generator = new DataGenerator();
        heap_a = new ClassicHeap();
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
            System.out.println(heap.heap_length);
            long heapify_finish = System.currentTimeMillis() % 1000;
            System.out.println("End Heapify");
            ArrayList<Integer> sorted_data = new ArrayList<Integer>();
            System.out.println("Start Sorting");
            long extraction_start = System.currentTimeMillis() % 1000;
            int length = heap.heap_length;
            for(int k =0 ; k< length;k++){
                sorted_data.add(heap.extraer_siguiente());
            }
            System.out.println(nodeList.size());
            long extraction_finish = System.currentTimeMillis() % 1000;
            System.out.println("Stop Sorting");
            System.out.println(sorted_data.size());
            assertEquals(0, heap.heap_length);
            writer.println(i +";"+ (heapify_finish - heapify_start) + ";" + (extraction_finish - extraction_start));
        }
        writer.close();
    }

    @Test
    public void testInsertionAndMelding() throws FileNotFoundException, UnsupportedEncodingException {
        //writer = new PrintWriter("Results/ClassicHeap_InsertionAndMelding.csv", "UTF-8");
    }


}