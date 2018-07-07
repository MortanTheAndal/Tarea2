package fibonacci;

public class FNode {
	
	FNode left, right, child, parent;
	int elem, rank, prio; 
	
	public FNode(int e, int p) {
		elem= e;
		prio=p;
		left = this;
		right=this;
	}

}
