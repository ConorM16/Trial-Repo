import java.util.ArrayList;

public class DAG {
	public ArrayList<Node> nodes = new ArrayList<Node>();
	
	private class Node {
        private char N;             // character value of node
        public ArrayList<Node> links = new ArrayList<Node>();

        public Node(char N) {
            this.N = N;
        }
    }
	
	/**
	 * 
	 * @param ch = char val we check for
	 * @return true if character present, false otherwise
	 */
	public boolean containsNode(char ch){
		Node array[] = toArray();
		int i = 0;
		while(i < array.length)
		{
			if(equal(array[i], ch))
			{
				return true;
			}
			i++;
		}
		return false;
	}
	/**
	 * 
	 * @param N char value of Node to be added
	 * @return true if Node is added, false otherwise
	 */
	public boolean addNode(char N){
		char capital = Character.toUpperCase(N); //converts N to uppercase letter
/*		Node newNode = new Node(capital);*/
		boolean added = false;
		if(!containsNode(capital))
		{
			Node newNode = new Node(capital);
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
	
	/**
	 * 
	 * @param ch1 - char val of parent node of requested link
	 * @param ch2 - child node of link
	 */
	public void requestLink(char ch1, char ch2){
		Node node1 = new Node(ch1);
		Node node2 = new Node(ch2);
		boolean added = false;
		if(!containsNode(ch1))
		{
			added = nodes.add(node1);
		}
		if(!containsNode(ch2))
		{
			added = nodes.add(node2);
		}
		setLink(nodes.indexOf(node1),nodes.indexOf(node2));
	}
	
	/**
	 * 
	 * @param ch - char of present Node we want, node is always present
	 * @return requested Node
	 */
	private Node returnNode(char ch){
		Node array[] = toArray();
		for(int i = 0; i < array.length; i++)
		{
			
		}
	}
	
	public boolean checkEqual(char ch1, char ch2){
		char upper1 = Character.toUpperCase(ch1);
		char upper2 = Character.toUpperCase(ch2);
		Node node = new Node(ch1);
		return equal(node,ch2);
	}
	/**
	 * 
	 * @param n - node we want to compare ch to
	 * @param ch
	 * @return if n.N = ch
	 */
	private boolean equal(Node n, char ch){
		String strNode = Character.toString(n.N);
		String strCh = Character.toString(ch);
		return strNode.equals(strCh);
	}
	
	private void setLink(int i, int j){
		Node node1 = nodes.get(i);
		Node node2 = nodes.get(j);
	}
	
	/**
	 * 
	 * @return DAG array list as array of nodes
	 */
	private Node[] toArray(){
		Node listNodes[] = new Node[nodes.size()];
		listNodes = nodes.toArray(listNodes);
		return listNodes;
	}
	
	/**
	 * Prints all nodes
	 */
	public void printNodes(){
		Node listNodes[] = toArray();
		int size = listNodes.length;
		for(int i = 0; i < size-1; i++)
		{
			System.out.print(listNodes[i].N + ", ");
		}
		System.out.print(listNodes[size-1].N);
	}
}
