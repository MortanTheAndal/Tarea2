package test;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import tarea2.ClassicHeap;
import tarea2.DataGenerator;
import tarea2.Node;

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
        writer = new PrintWriter("Results/ClassicHeap_Sorting.csv", "UTF-8");
        for(int i = 15; i<=21; i++){
            ArrayList<Integer> data = generator.getData(i);
            ArrayList<Node> nodeList = new ArrayList<Node>();
            for (int j: data){
                Node to_insert = new Node(j,j);
                nodeList.add(to_insert);
            }
            long heapify_start = System.currentTimeMillis() % 1000;
            ClassicHeap heap = heap_a.heapify(nodeList);
            long heapify_finish = System.currentTimeMillis() % 1000;
            ArrayList<Integer> sorted_data = new ArrayList<Integer>();
            long extraction_start = System.currentTimeMillis() % 1000;
            for(int k =0 ; k< nodeList.size();k++){
                sorted_data.add(heap.extraer_siguiente());
            }
            long extraction_finish = System.currentTimeMillis() % 1000;
            writer.println(i +";"+ (heapify_finish - heapify_start) + ";" + (extraction_finish - extraction_start));
        }
    }
    @Test
    public void testInsertionAndMelding() throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter("Results/ClassicHeap_InsertionAndMelding.csv", "UTF-8");

    }


}