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
	
	/**
	 * 
	 * @param N char value of Node to be added
	 * @return true if Node is added, false otherwise
	 */
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
	
	public void printNodes(){
		Node listNodes[] = new Node[nodes.size()];
		listNodes = nodes.toArray(listNodes);
		int size = listNodes.length;
		for(int i = 0; i < size-1; i++)
		{
			System.out.print(listNodes[i].N + ", ");
		}
		System.out.print(listNodes[size-1].N);
	}
}
