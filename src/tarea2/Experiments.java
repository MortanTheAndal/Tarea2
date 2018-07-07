package tarea2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Experiments {
    private DataGenerator generator = new DataGenerator();
    private PrintWriter writer;

    private void testSort_ClassicHeap() throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter("Results/ClassicHeap_Sorting.csv", "UTF-8");
        for(int q = 0; q<15;q++){
            for(int i = 15; i<=21; i++){
                ArrayList<Integer> data = generator.getData(i);
                ArrayList<Node> nodeList = new ArrayList<Node>();
                for (int j: data){
                    Node to_insert = new Node(j,j);
                    nodeList.add(to_insert);
                }
                long heapify_start = System.nanoTime();
                System.out.println(nodeList.size());
                ClassicHeap heap = new ClassicHeap(nodeList);
                long heapify_finish = System.nanoTime();
                ArrayList<Integer> sorted_data = new ArrayList<Integer>();
                long extraction_start = System.nanoTime();
                int length = heap.heap_length;
                for(int k =0 ; k< length;k++){
                    sorted_data.add(heap.extraer_siguiente());
                }
                long extraction_finish = System.nanoTime();
                writer.println(i +";"+ (heapify_finish - heapify_start) + ";" + (extraction_finish - extraction_start));
            }
        }
        writer.close();
    }

    private void testInsertionAndMelding_ClassicHeap() throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter("Results/ClassicHeap_InsertionAndMelding.csv", "UTF-8");
        for(int q = 0; q<15; q++ ){
            ArrayList<Integer> n = generator.getData(20);
            for(int i=0;i<16;i++){
                ArrayList<ClassicHeap> heap_list=  new ArrayList<ClassicHeap>();
                int n_of_heaps = (int) Math.pow(2,20-i);
                int index = 0;
                long insertion_start = System.nanoTime();
                for(int j = 0; j<n_of_heaps; j++){
                    ClassicHeap heap = new ClassicHeap();
                    for(int k=0; k < Math.pow(2,i);k++){
                        int to_insert = n.get(index);
                        heap.insertar(to_insert,to_insert);
                        index++;
                    }
                    heap_list.add(heap);
                }

                long insertion_finish = System.nanoTime();
                long melding_start = System.nanoTime();
                while (heap_list.size() > 1){
                    ArrayList<ClassicHeap> aux = new ArrayList<ClassicHeap>();
                    for(int j=0; j<heap_list.size(); j=j+2){
                        ClassicHeap heap = new ClassicHeap();
                        heap.meld(heap_list.get(j),heap_list.get(j+1));
                        aux.add(heap);
                    }
                    heap_list = aux;
                }
                long melding_finish = System.nanoTime();
                writer.println(i +";"+ (insertion_finish - insertion_start) + ";" + (melding_finish - melding_start));
            }
        }
        writer.close();
    }

    private void testSort_LeftistTree() throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter("Results/LeftistTree_Sorting.csv", "UTF-8");
        for(int q = 0 ; q < 15; q++){
            for(int i = 15; i<=21; i++){
                ArrayList<Integer> data = generator.getData(i);
                ArrayList<Node> nodeList = new ArrayList<Node>();
                for (int j: data){
                    Node to_insert = new Node(j,0);
                    nodeList.add(to_insert);
                }
                long heapify_start = System.nanoTime();
                System.out.println(nodeList.size());
                LeftistTree heap = new LeftistTree(nodeList);
                long heapify_finish = System.nanoTime();
                ArrayList<Integer> sorted_data = new ArrayList<Integer>();
                long extraction_start = System.nanoTime();
                int length = heap.n_elem;
                System.out.println(length);
                for(int k =0 ; k< length;k++){
                    sorted_data.add(heap.extraer_siguiente());
                }
                long extraction_finish = System.nanoTime();
                writer.println(i +";"+ (heapify_finish - heapify_start) + ";" + (extraction_finish - extraction_start));
            }
        }
        writer.close();
    }
    private void testInsertionAndMelding_LeftistTree() throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter("Results/LeftistTree_InsertionAndMelding.csv", "UTF-8");
        for(int q = 0; q<15;q++){
            ArrayList<Integer> n = generator.getData(20);
            for(int i=0;i<16;i++){
                ArrayList<LeftistTree> heap_list=  new ArrayList<LeftistTree>();
                int n_of_heaps = (int) Math.pow(2,20-i);
                int index = 0;
                long insertion_start = System.nanoTime();
                for(int j = 0; j<n_of_heaps; j++){
                    LeftistTree heap = new LeftistTree();
                    for(int k=0; k < Math.pow(2,i);k++){
                        int to_insert = n.get(index);
                        heap.insertar(to_insert,1);
                        index++;
                    }
                    heap_list.add(heap);
                }

                long insertion_finish = System.nanoTime();
                long melding_start = System.nanoTime();
                while (heap_list.size() > 1){
                    ArrayList<LeftistTree> aux = new ArrayList<LeftistTree>();
                    for(int j=0; j<heap_list.size(); j=j+2){
                        LeftistTree heap = new LeftistTree();
                        heap.meld(heap_list.get(j),heap_list.get(j+1));
                        aux.add(heap);
                    }
                    heap_list = aux;
                }
                long melding_finish = System.nanoTime();
                writer.println(i +";"+ (insertion_finish - insertion_start) + ";" + (melding_finish - melding_start));
            }
        }
        writer.close();
    }

    private void testSort_SkewHeap() throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter("Results/SkewHeap_Sorting.csv", "UTF-8");
        for(int q = 0; q<15; q++){
            for(int i = 15; i<=21; i++){
                ArrayList<Integer> data = generator.getData(i);
                ArrayList<Node> nodeList = new ArrayList<Node>();
                for (int j: data){
                    Node to_insert = new Node(j,0);
                    nodeList.add(to_insert);
                }
                long heapify_start = System.nanoTime();
                System.out.println(nodeList.size());
                SkewHeap heap = new SkewHeap(nodeList);
                long heapify_finish = System.nanoTime();
                ArrayList<Integer> sorted_data = new ArrayList<Integer>();
                long extraction_start = System.nanoTime();
                int length = heap.n_elem;
                System.out.println(length);
                for(int k =0 ; k< length;k++){
                    sorted_data.add(heap.extraer_siguiente());
                }
                long extraction_finish = System.nanoTime();
                writer.println(i +";"+ (heapify_finish - heapify_start) + ";" + (extraction_finish - extraction_start));
            }
        }
        writer.close();
    }

    private void testInsertionAndMelding_SkewHeap() throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter("Results/SkewHeap_InsertionAndMelding.csv", "UTF-8");
        for(int q = 0; q < 15; q++){
            ArrayList<Integer> n = generator.getData(20);
            for(int i=0;i<16;i++){
                ArrayList<SkewHeap> heap_list=  new ArrayList<SkewHeap>();
                int n_of_heaps = (int) Math.pow(2,20-i);
                int index = 0;
                long insertion_start = System.nanoTime();
                for(int j = 0; j<n_of_heaps; j++){
                    SkewHeap heap = new SkewHeap();
                    for(int k=0; k < Math.pow(2,i);k++){
                        int to_insert = n.get(index);
                        heap.insertar(to_insert,1);
                        index++;
                    }
                    heap_list.add(heap);
                }

                long insertion_finish = System.nanoTime();
                long melding_start = System.nanoTime();
                while (heap_list.size() > 1){
                    ArrayList<SkewHeap> aux = new ArrayList<SkewHeap>();
                    for(int j=0; j<heap_list.size(); j=j+2){
                        SkewHeap heap = new SkewHeap();
                        aux.add(heap.meld(heap_list.get(j),heap_list.get(j+1)));
                    }
                    heap_list = aux;
                }
                long melding_finish = System.nanoTime();
                writer.println(i +";"+ (insertion_finish - insertion_start) + ";" + (melding_finish - melding_start));
            }
        }
        writer.close();
    }

    private void testInsertionAndMelding_BinHeap() throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter("Results/BinHeap_InsertionAndMelding.csv", "UTF-8");
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
                writer.println(i + ";" + (insertion_finish - insertion_start) + ";" + (melding_finish - melding_start));
            }
        }

        writer.close();
    }

    private void testSort_BinHeap() throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter("Results/BinHeap_Sorting.csv", "UTF-8");
        for (int a = 0; a <= 15; a++) {
            for (int i = 15; i <= 21; i++) {
                ArrayList<Integer> data = generator.getData(i);
                BinHeap heap = new BinHeap();
                long heapify_start = System.nanoTime();
                System.out.println(data.size());
                for (int j : data) {
                    heap.insertar(j, j);
                }
                long heapify_finish = System.nanoTime();
                ArrayList<Integer> sorted_data = new ArrayList<Integer>();
                long extraction_start = System.nanoTime();
                int length = heap.size;
                for (int k = 0; k < length; k++) {
                    sorted_data.add(heap.extraer_siguiente());
                }
                long extraction_finish = System.nanoTime();
                writer.println(
                        i + ";" + (heapify_finish - heapify_start) + ";" + (extraction_finish - extraction_start));
            }
        }

        writer.close();
    }

    private void testSort_FibHeap() throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter("Results/FibHeap_Sorting.csv", "UTF-8");
        for (int a = 0; a <= 15; a++) {

            for (int i = 15; i <= 21; i++) {
                ArrayList<Integer> data = generator.getData(i);
                FibHeap heap = new FibHeap();
                long heapify_start = System.nanoTime();
                for (int j : data) {
                    heap.insertar(j, j);
                }

                long heapify_finish = System.nanoTime();
                ArrayList<Integer> sorted_data = new ArrayList<Integer>();
                long extraction_start = System.nanoTime();
                int length = heap.size;
                for (int k = 0; k < length; k++) {
                    sorted_data.add(heap.extraer_siguiente());
                }
                long extraction_finish = System.nanoTime();
                writer.println(
                        i + ";" + (heapify_finish - heapify_start) + ";" + (extraction_finish - extraction_start));
            }
        }
        writer.close();
    }

    private void testInsertionAndMelding_FibHeap() throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter("Results/FibHeap_InsertionAndMelding.csv", "UTF-8");
        ArrayList<Integer> n = generator.getData(20);
        for (int a = 0; a <= 15; a++) {
            for (int i = 0; i < 16; i++) {
                ArrayList<FibHeap> heap_list = new ArrayList<FibHeap>();
                int n_of_heaps = (int) Math.pow(2, 20 - i);
                int index = 0;
                long insertion_start = System.nanoTime();
                for (int j = 0; j < n_of_heaps; j++) {
                    FibHeap heap = new FibHeap();
                    for (int k = 0; k < Math.pow(2, i); k++) {
                        int to_insert = n.get(index);
                        heap.insertar(to_insert, to_insert);
                        index++;
                    }
                    heap_list.add(heap);
                }

                long insertion_finish = System.nanoTime();
                long melding_start = System.nanoTime();
                while (heap_list.size() > 1) {
                    ArrayList<FibHeap> aux = new ArrayList<FibHeap>();
                    for (int j = 0; j < heap_list.size(); j = j + 2) {
                        FibHeap heap = FibHeap.meld(heap_list.get(j), heap_list.get(j + 1));
                        aux.add(heap);
                    }
                    heap_list = aux;
                }
                long melding_finish = System.nanoTime();
                writer.println(i + ";" + (insertion_finish - insertion_start) + ";" + (melding_finish - melding_start));
            }
        }

        writer.close();
    }

    public static void main(String args[]) throws FileNotFoundException, UnsupportedEncodingException{
        Experiments experiments = new Experiments();
        experiments.testInsertionAndMelding_ClassicHeap();
        experiments.testSort_ClassicHeap();
        experiments.testInsertionAndMelding_LeftistTree();
        experiments.testSort_LeftistTree();
        experiments.testInsertionAndMelding_SkewHeap();
        experiments.testSort_SkewHeap();
        experiments.testInsertionAndMelding_BinHeap();
        experiments.testSort_BinHeap();
        experiments.testInsertionAndMelding_FibHeap();
        experiments.testSort_FibHeap();
    }
}
