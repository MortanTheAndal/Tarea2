package tarea2;
import java.util.ArrayList;

public class FibHeap implements PriorityQueue{
	/*
	 * Lista doblemente enlazada y circular. 
	 */
	private FNode max;
	int size; 
	
	public FibHeap() {
		max= null; 
		size=0;
	}
	
	public boolean isEMT() {
		return max==null; 
	}
	
	/*
	 * (non-Javadoc)
	 * @see tarea2.PriorityQueue#insertar(int, int)
	 */
	@Override
	public void insertar(int x, int p) {
		FNode fn = new FNode(x,p);
		insertFNode(fn);
		size++;
	}
	
	/*
	 * insertar arbol completo a la lista circular
	 */
	public void insertFNode(FNode fn) {
		if(isEMT()) {
			max = fn;
		}
		else {
			
			fn.left.right=fn.right;
			fn.right.left = fn.left;
		
			fn.left = max;
			fn.right = max.right;
			max.right.left = fn;
			max.right = fn;
		 
			compareMax(fn);
		}
		
	}
	
	/*
	 * cambia el maximo si corresponde 
	 */
	protected void compareMax(FNode fn){
		if(!isEMT()){
		    if (fn.prio>max.prio){
			max = fn; 
		    }   
		}
		else{
		    max = fn; 
		}
	}
	
	@SuppressWarnings("null")
	@Override
	public int extraer_siguiente() {
		if(isEMT())
			return (Integer) null;
		FNode mem = max; //guardar para retornar valor
		FNode actual = mem.child;
		int childCount = mem.rank;
		FNode temp;
		for(int i =0; i<childCount; i++) {
			temp = actual.right;
			
			insertFNode(actual);
			
			actual.parent=null;
			actual = temp;
		}

		mem.left.right=mem.right;
		mem.right.left=mem.left;
		if(mem!= mem.right) {
			max = mem.right;//maximo momentaneo
			buildForest();
		}
		else {
			max = null;
		}
		size-=1;
		return mem.elem;	
	}

	private void buildForest() {
		if(isEMT()) 
			return;
		FNode[] A = new FNode[(int)Math.ceil(Math.log(size)
				/Math.log(2))];
		
		int treeCount = 0;
		FNode counter = max;
		
		if(counter!=null) {
			treeCount+=1;
			counter=counter.right;
			while (counter!=max) {
				treeCount+=1;
				counter=counter.right;
			}
		}
		//FNode actual = max; 
		for (int i =0; i<treeCount;i++) {
			int grado = counter.rank;
			FNode sgte = counter.right;
			while(A[grado]!=null) {
				FNode node = A[grado];
				if(counter.prio<
						node.prio){
					FNode temp= node;
					node = counter;
					counter = temp;
				}
				emparentar(counter,node);
				A[grado]=null;
				grado++;
				
			}
			A[grado]=counter;
			counter = sgte;
			//updateArray(counter,A);
			//counter=counter.right;
		}
		
		max = null; //reset;
		for (int i =0; i<A.length;i++) {
			FNode newTree = A[i];
			if(newTree!=null) {
				if(isEMT()) {
					max = newTree;
				}
				else {
					newTree.left.right = newTree.right;
					newTree.right.left = newTree.left;
					
					
					newTree.left = max;
					newTree.right = max.right;
					max.right = newTree; 
					newTree.right.left = newTree;
					compareMax(newTree);
				}
			}
		}
		
		
	}
	

	private void emparentar(FNode p, FNode h) {
		h.right.left=h.left;
		h.left.right=h.right;
		h.parent = p;
		p.rank+=1;
		if(p.child!=null) {
			h.left = p.child;
			h.right = p.child.right;
			h.right.left= h;
			p.child.right = h;
			
		}
		else {
			p.child = h;
			h.right=h;
			h.left=h;
		}
		
		
	}
	
	public static FibHeap meld(FibHeap h1, FibHeap h2) {
		if(h1.isEMT() && h2.isEMT())
			return null; 
		if(h1.isEMT()) 
			return h2;
		if(h2.isEMT()) 
			return h1;
		FibHeap fh = new FibHeap();
		
		FNode m1 = h1.max;
		FNode m2 = h2.max;
		FNode aux = m1.right;
		m1.right = m2.right;
		m1.right.left = m1; 
		m2.right = aux;
		m2.right.left = m2;
		if (m1.prio>m2.prio) {
			fh.max = m1;
		}
		else {
			fh.max = m2;
		}
		fh.size = h1.size+h2.size;
		return fh;
	}
	public static void main(String[] args){
		FibHeap fh1 = new FibHeap();
		FibHeap fh2= new FibHeap();

		tarea2.DataGenerator dg = new tarea2.DataGenerator();
		ArrayList<Integer> data = dg.getData(5);
		ArrayList<Integer> da = new ArrayList<Integer>();
		ArrayList<Integer> ta = new ArrayList<Integer>();
		for(int i =0; i<data.size()/2;i++) {
			da.add(data.get(i));
			ta.add(data.get(i+data.size()/2));
		}
		
		for(int i = 0; i<da.size();i++){
		    fh1.insertar(da.get(i), da.get(i));
		    fh2.insertar(ta.get(i), ta.get(i));
		}
		FibHeap fh = FibHeap.meld(fh1,fh2);
		System.out.println("-------"+fh.size+"-------");
		for (int i = 0; i<data.size();i++){
		    System.out.println(fh.extraer_siguiente());
		}
		System.out.println("-------"+fh.size+"-------");
	}
	
}
