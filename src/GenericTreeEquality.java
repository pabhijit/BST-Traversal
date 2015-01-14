import java.util.Stack;

// Tree implements the binary search tree property

class Tree<T extends Comparable<T>> {
	public Tree(T v) {
		value = v;
		left = null;
		right = null;
	}

	public void insert(T v) {
		if (value.compareTo(v) == 0)
			return;
		if (value.compareTo(v) > 0)
			if (left == null)
				left = new Tree<T>(v);
			else
				left.insert(v);
		else if (value.compareTo(v) < 0)
			if (right == null)
				right = new Tree<T>(v);
			else
				right.insert(v);
	}

	protected T value;
	protected Tree<T> left;
	protected Tree<T> right;
}

class Iter<T extends Comparable<T>> {

	Tree<T> currTree;
	Stack<Tree<T>> stackedTrees;

	public Iter(Tree<T> treeIter) {
		this.currTree = treeIter;
		stackedTrees = new Stack<Tree<T>>();
	}

	// define here the external iterator operations, done and next
	public boolean done() {
		if( stackedTrees.isEmpty() && currTree == null ) {
			return true;
		} else {
			return false;
		}
	}

	public Tree<T> next() {
		Tree<T> retVal = null;
		while( !stackedTrees.isEmpty() || currTree != null ) {
			if( currTree != null ) {
				stackedTrees.push( currTree );
				currTree = currTree.left;
			} else {
				retVal = stackedTrees.pop();
				currTree = retVal.right;
				return retVal;
			}
		}
		return retVal;
	}
}

public class GenericTreeEquality {

	static <T extends Comparable<T>> boolean equals(Tree<T> tree1, Tree<T> tree2) {

		// define here the equality test
		boolean retVal = true;
		try {
			Iter<T> iter1 = new Iter<T>( tree1 );
			Iter<T> iter2 = new Iter<T>( tree2 );

			while( !iter1.done() && !iter2.done() ) {
				if( iter1.next().value.compareTo( iter2.next().value ) != 0 ) {
					retVal = false;
					break;
				}
			}
			if( retVal && ( !iter1.done() || !iter2.done() ) ) {
				retVal = false;
			}
		} catch( Exception exception ) {
			exception.printStackTrace();
		}
		return retVal;

	}

	public static void main(String[] args) {

		Tree<Integer> tree1 = new Tree<Integer>(50);
		tree1.insert(70);
		tree1.insert(80);	
		tree1.insert(90);
		tree1.insert(100);

		Tree<Integer> tree2 = new Tree<Integer>(100);
		tree2.insert(90);
		tree2.insert(80);	
		tree2.insert(70);
		tree2.insert(50);

		System.out.println(equals(tree1, tree2 ));
	}
}

