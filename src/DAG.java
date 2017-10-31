import java.util.ArrayList;

public class DAG {
	public ArrayList<Node> nodes = new ArrayList<Node>();
	
	private class Node {
        //private Node left,right,parent;  // left and right subtrees
        private char N;             // character value of node
        public ArrayList<Node> links = new ArrayList<Node>();

        public Node(char N) {
            this.N = N;
            //left = null;
            //right = null;
            //parent = null;
        }
    }
	
	public boolean addNode(char N){
		char capital = Character.toUpperCase(N); //converts N to uppercase letter
		Node newNode = new Node(capital);
		boolean added = false;
		if(!nodes.contains(newNode))
		{
			while(added == false)
			{
				added = nodes.add(newNode);				
			}
			return added;
		}
		else 
		{
			System.out.println("Node already present");
			return added;
		}
	}
}
