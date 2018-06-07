package tarea2;

import java.util.ArrayList;
import java.util.Collections;

public class DataGenerator {

    public ArrayList<Integer> getData(int k){
	ArrayList<Integer> data = new ArrayList<Integer>();
	for (int i = 1; i <= Math.pow(2, k); i++){
	    data.add(i);
	}
	Collections.shuffle(data);
	
	return data; 
    }
    
    public static void main(String[] args){
	DataGenerator dg = new DataGenerator();
	System.out.println(dg.getData(4));
    }
}
