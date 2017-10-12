 //import BinaryTree.Node;

public class BinaryTree {
	 public Node root;             // root of BST
//	 private boolean isEmpty = true;
	    /**
	     * Private node class.
	     */
	    private class Node {
	        private Node left,right,parent;  // left and right subtrees
	        private int N;             // number of nodes in subtree

	        public Node(int N) {
	            this.N = N;
	            left = null;
	            right = null;
	            parent = null;
	        }
	    }
	    
	    public void putNode(int n){
	    	root = put(root, n);
	    }
	    
	    private Node put(Node x, int n) {
	        if (x == null) return new Node(n);
	        if      (n < x.N) x.left  = put(x.left, n);
	        else if (n > x.N) x.right = put(x.right, n);
	        else              x.N   = n;
	        return x;
	    }
	    
	    public boolean contains(int n)
	    {
	    	return containsNode(root, n);
	    }
	    
	    public boolean containsNode(Node x,int n)
	    {
	    	if(x == null) return false;
	    	
	    	if		(n < x.N) return containsNode(x.left,n);
	    	else if (n > x.N) return containsNode(x.right,n);
	    	else return true;
	    }
	

}
