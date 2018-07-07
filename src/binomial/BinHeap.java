package binomial;

import java.util.ArrayList;
 
import tarea2.PriorityQueue;

public class BinHeap implements PriorityQueue {
	BinTree first, cs, max;
	int size;

	public BinHeap() {
		first = null;
		cs = null;
		size = 0;
	}

	public BinHeap(BinTree bt) {
		first = bt;
		cs = null;
		size = (int) Math.pow(2, bt.level);
	}

	@Override
	public void insertar(int x, int p) {
		BinTree bt = new BinTree(x, p);
		BinHeap bh = new BinHeap(bt);
		this.heapSum(bh);

	}

	public void insertTree(BinTree bt) {
		size += (int) Math.pow(2, bt.level);///TODO
		if (first == null) {
			first = bt;
			return;
		}
		BinTree rec = first;
		while (rec.bro != null) {
			rec = rec.bro;
		}
		rec.bro = bt;
		
	}

	private void heapSum(BinHeap bh) {
		int newSize = this.size + bh.size;
		ArrayList<BinTree> trans = new ArrayList<BinTree>();
		BinTree rec = bh.first, next;
		while (rec != null) {
			next = rec.bro;
			rec.bro = null;
			trans.add(rec);
			rec = next;
		}
		rec = this.first;
		while (rec != null) {
			next = rec.bro;
			rec.bro = null;
			trans.add(rec);
			rec = next;
		}
		String binStr = Integer.toBinaryString(newSize);
		ArrayList<BinTree> subset = new ArrayList<BinTree>();
		for (int k = 0; k < binStr.length(); k++) {

			for (BinTree bt : trans) {
				if (bt.level == k) {
					subset.add(bt);
				}
			}
			for (BinTree rem : subset) {
				trans.remove(rem);
			}
			trans.trimToSize();

			if (subset.size() == 1) {
				BinTree newNode = subset.get(0);
				concat(newNode);

			} else if (subset.size() == 2) {
				BinTree dad = subset.get(0);
				BinTree son = subset.get(1);
				if (son.prior > dad.prior) {
					BinTree aux = dad;
					dad = son;
					son = aux;
				}
				emparentar(dad, son);
				trans.add(dad);
			} else if (subset.size() == 3) {
				BinTree newNode = subset.get(2);
				BinTree dad = subset.get(0);
				BinTree son = subset.get(1);
				if (son.prior > dad.prior) {
					BinTree aux = dad;
					dad = son;
					son = aux;
				}
				emparentar(dad, son);
				trans.add(dad);
				concat(newNode);
			}
			subset.clear();
		}
		this.first = cs;
		this.size = newSize;
		this.cs = null;
		updateMax();
	}

	@Override
	public int extraer_siguiente() {
		int ret = max.element;
		if (first == max) {
			first = first.bro;
		} else {

			BinTree bn = first;

			while (bn.bro != max)
				bn = bn.bro;
			bn.bro = max.bro;
		}
		size-=(int)Math.pow(2, max.level);

		BinHeap maxSons = new BinHeap();
		BinTree son = max.child, next;
		max = null;
		while (son != null) {
			next = son.bro;
			son.parent = null; 
			son.bro = null;
			maxSons.insertTree(son);
			son = next;
		}
		heapSum(maxSons);
		return ret;
	}

	public void updateMax() {
		BinTree bn = first;
		while (bn != null) {

			if (max == null)
				max = bn;
			else {
				if (max.prior < bn.prior)
					max = bn;
			}
			bn = bn.bro;

		}
	}

	public void emparentar(BinTree padre, BinTree hijo) {
		hijo.parent = padre;
		hijo.bro = padre.child;
		padre.child = hijo;
		padre.level++;
	}

	public void concat(BinTree newNode) {
		if (cs != null) {
			newNode.bro = cs.bro;
			cs.bro = newNode;
		} else {
			cs = newNode;
		}
	}
	
	public static BinHeap meld (BinHeap h1, BinHeap h2) {
		h1.heapSum(h2);
		return h1;
	}

	public static void main(String[] args) {
		BinHeap fh1 = new BinHeap();
		BinHeap fh2 = new BinHeap();
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
		BinHeap fh = BinHeap.meld(fh1,fh2);
		System.out.println("-------"+fh.size+"-------");
		for (int i = 0; i<data.size();i++){
		    System.out.println(fh.extraer_siguiente());
		}
		System.out.println("-------"+fh.size+"-------");

	}

}
