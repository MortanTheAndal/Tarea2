package binomial;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


import junit.framework.TestCase;
import tarea2.DataGenerator;

public class BinHeapTest extends TestCase {

	private DataGenerator generator;
	private PrintWriter writer;

	@Before 
	public void setUp() throws Exception {
		super.setUp();
		generator = new DataGenerator();
	}
 
	@Test
	public void testInsertionAndMelding() throws FileNotFoundException, UnsupportedEncodingException {
		writer = new PrintWriter("C:\\Users\\Mortan\\Desktop\\Results\\BinHeap_InsertionAndMelding.csv");
		ArrayList<Integer> n = generator.getData(20);
		for (int a = 0; a <= 15; a++) {
			for (int i = 0; i < 16; i++) {
				ArrayList<BinHeap> heap_list = new ArrayList<BinHeap>();
				
				int n_of_heaps = (int) Math.pow(2, 20 - i);
				int index = 0;
				System.out.println("Start insertions " + a + ":" + i);
				long insertion_start = System.nanoTime();
				for (int j = 0; j < n_of_heaps; j++) {
					BinHeap heap = new BinHeap();
					for (int k = 0; k < Math.pow(2, i); k++) {
						int to_insert = n.get(index);
						heap.insertar(to_insert, to_insert);
						index++;
					}
					heap_list.add(heap);
				}

				long insertion_finish = System.nanoTime();
				System.out.println("Finish insertions");
				System.out.println("Start meldings");
				long melding_start = System.nanoTime();
				while (heap_list.size() > 1) {
					ArrayList<BinHeap> aux = new ArrayList<BinHeap>();
					for (int j = 0; j < heap_list.size(); j = j + 2) {
						BinHeap heap = BinHeap.meld(heap_list.get(j), heap_list.get(j + 1));
						aux.add(heap);
					}
					heap_list = aux;
				}
				long melding_finish = System.nanoTime();
				System.out.println("Finish meldings");
				assertEquals(1, heap_list.size());
				writer.println(i + ";" + (insertion_finish - insertion_start) + ";" + (melding_finish - melding_start));
			}
		}
 
		writer.close();
	}
	
	@Test
	public void testSort() throws FileNotFoundException, UnsupportedEncodingException {
		writer = new PrintWriter("C:\\Users\\Mortan\\Desktop\\Results\\BinHeap_Sorting.csv");
		for (int a = 0; a <= 15; a++) {
			
			for (int i = 15; i <= 21; i++) {
				ArrayList<Integer> data = generator.getData(i);
				BinHeap heap = new BinHeap();

				System.out.println("Start Heapify"+a +":"+i);
				long heapify_start = System.nanoTime();
				System.out.println(data.size());
				for (int j : data) {
					heap.insertar(j, j); 
				}

				long heapify_finish = System.nanoTime();
				System.out.println("End Heapify");
				ArrayList<Integer> sorted_data = new ArrayList<Integer>();
				System.out.println("Start Sorting");
				long extraction_start = System.nanoTime();
				int length = heap.size;
				for (int k = 0; k < length; k++) {
					sorted_data.add(heap.extraer_siguiente());
				}
				long extraction_finish = System.nanoTime();
				System.out.println("Stop Sorting");
				assertEquals(0, heap.size);
				writer.println(
						i + ";" + (heapify_finish - heapify_start) + ";" + (extraction_finish - extraction_start));
			}
		}
 
		writer.close();
	}
	
}
