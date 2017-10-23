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
	        public Node [] nodes;		//Nodes that initial node points to

	        public Node(int N) {
	            this.N = N;
	            left = null;
	            right = null;
	            parent = null;
	            nodes = new Node[2];
	        }
	    }
	    
	    /*
	     * takes an n, if its not 0, adds it to DAG
	     */
	    public void putNode(int n){
	    	if(!zero(n,"put"))
	    	{
		    	root = put(root, n);
		    	setParents(root, root);
		    	setLinks(findNode(root,n));
	    	}
	    }
	    
	    /*
	     * places node in graph
	     */
	    private Node put(Node x, int n) {
	        if (x == null) return new Node(n);
	        if      (n < x.N) x.left  = put(x.left, n);
	        else if (n > x.N) x.right = put(x.right, n);
	        else              x.N   = n;
	        return x;
	    }
	    
	    private Node findNode(Node x,int n){
	    	if (x == null) return null;
	        if      (n < x.N) return findNode(x.left, n);
	        else if (n > x.N) return findNode(x.right, n);
	        else              return x;
	       // return x;
	    }
	    
	    private void setLinks(Node x){
	    	if(x != root)
	    	{
	    		if(x.N < x.parent.N)
	    		{
	    			x.parent.nodes[0] = x; //left link of parent node points to x
	    		}
	    		else
	    		{
	    			x.parent.nodes[1] = x;
	    		}
	    	}
	    }
	    
	    /*
	     * checks links for node with value n
	     */
	    public int [] checkLinks(int n){
	    	Node x = findNode(root,n);
	    	int [] linkVals = new int[2];
	    	d
	    	return linkVals;
	    }
	    public boolean contains(int n)
	    {
	    	return containsNode(root, n);
	    }

	    /*
	     * checks graph for node
	     */
	    public boolean containsNode(Node x,int n)
	    {
	    	if(x == null) return false;
	    	
	    	if		(n < x.N) return containsNode(x.left,n);
	    	else if (n > x.N) return containsNode(x.right,n);
	    	else return true;
	    }
	    
	    /*
	     * sets parents if node x
	     */
	    private void setParents(Node x, Node prev)
	    {
	    	if(x.left != null) setParents(x.left, x);
	    	if(x.right != null) setParents(x.right, x);
	    	if(x != prev){
	    		x.parent = prev;
	    		//System.out.println("Parent of " + x.N + " = " + x.parent.N);
	    	}
	    }
	    
	    /*
	     * finds height of node n
	     */
	    public int depth(int n)
	    {
	    	if(!zero(n,"depth"))
	    	{
		    	if((contains(n))&& (root.N != n)){
		    		return nodeDepth(root, n, 0);	    		
		    	}
		    	else return 0;
	    	}
	    	return 0;
	    }
	    
	    private int nodeDepth(Node x, int n, int count)
	    {
	    	if (x == null) return count;
	        if      (n < x.N){
	        	count++;
	        	return nodeDepth(x.left, n, count);
	        }
	        else if (n > x.N){
	        	count++;
	        	return nodeDepth(x.right, n, count);
	        }
	        else{
	        	return count;
	        }
	    }
	    
	    /*
	     * creates array of all of node n's ancestors
	     */
	    public int [] ancestors(int n){
	    	if(!zero(n,"ancestor"))
	    	{
		    	if((contains(n))&& (root.N != n)){
		    		int [] ancestors = new int[depth(n)];
		    		findAncestors(root,n,ancestors,0);
		    		return ancestors;
		    	}
		    	else return null;
	    	}
	    	else return null;
	    }
	    private void findAncestors(Node x, int n, int [] ancestors, int i)
	    {
	    	//if (x == null) return ancestors;
	        if      (n < x.N){
	        	ancestors[i] = x.N;
	        	i++;
	        	findAncestors(x.left, n, ancestors, i);
	        }
	        else if (n > x.N){
	        	ancestors[i] = x.N;
	        	i++;
	        	findAncestors(x.right, n, ancestors, i);
	        }
	        else{
	        	//return ancestors;
	        	//System.out.println("Node found");
	        }
	        //return ancestors;
	    }
	    
	    /*
	     * greatest common ancestor of a and b
	     */
	    public int gca(int a, int b)
	    {
	    	if((!zero(a,"ancestor")) && !zero(b,"ancestor"))
	    	{
		    	int [] arr1 = ancestors(a);
		    	int [] arr2 = ancestors(b);
		    	if((arr1 == null) || (arr2 == null))
		    	{
		    		return 0;
		    	}
		    	else return gcaFind(ancestors(a),ancestors(b));
		    }
	    	else return 0;
	    }
	    
	    /*
	     *Greatest common ancestor 
	     * 
	     */
	    private int gcaFind(int [] anc1, int [] anc2){
	    	if(anc1.length >= anc2.length)
	    	{
	    		return compare(anc1,anc2);
	    	}
	    	else
	    	{
	    		return compare(anc2,anc1);
	    	}
	    }
	    
	    /*
	     * anc1 > anc2
	     */
	    private int compare(int [] anc1, int [] anc2){
	    	int i = 0;
	    	int gca = -1;
	    	while(i < anc2.length)
	    	{
	    		if(anc1[i] == anc2[i])
	    		{
	    			gca = anc1[i];
	    		}
	    		i++;
	    	}
	    	return gca;
	    }
	    
	    /*
	     * checks if n = 0 and what type of request is made
	     */
	    private boolean zero(int n, String type){
	    	if(n == 0)
	    	{
	    		if(type.equals("put"))
	    		{
	    			zeroPrint("put");
	    		}
	    		else if(type.equals("depth"))
	    		{
	    			zeroPrint("depth");
	    		}
	    		else zeroPrint("ancestor");
	    		return true;
	    	}
	    	else return false;
	    }
	    
	    private void zeroPrint(String type){
	    	if(type.equals("put"))
	    	{
	    		System.out.println("Zero cannot be added to tree.");
	    	}
	    	else if(type.equals("depth"))
	    	{
	    		System.out.println("Zero is not present.");
	    	}
	    	else System.out.println("Zero has no ancestors.");
	    }
	    
	    
	    
//	    public int isInt(String string)
//	    {
//	        try 
//	        {
//	            return Integer.parseInt(string);
//	        } catch (NumberFormatException ex)
//	        {
//	        	invalidInput();
//	            return 0;
//	        }
//	    }
//	    
//	    private void invalidInput()
//	    {
//	    	System.out.println("Invalid input.");
//	    }
	    
	   // private int parseInt()
	    

}
