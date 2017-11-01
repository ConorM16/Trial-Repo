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
		Node array[] = toArray(nodes);
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
		Node node1;
		boolean added = false;
		if(!containsNode(ch1))
		{
			node1 = new Node(ch1);
			added = nodes.add(node1);
		}
		if(!containsNode(ch2))
		{
			node1 = new Node(ch2);
			added = nodes.add(node1);
		}
		setLink(ch1,ch2);
	}
	
	/**
	 * 
	 * @param ch1 - char val of parent node
	 * @param ch2 - ch val of child node
	 */
	private void setLink(char ch1, char ch2){
		Node node1 = returnNode(ch1);
		Node node2 = returnNode(ch2);
		if(!node1.links.contains(node2))
		{
			node1.links.add(node2);
			System.out.println("Reached");
		}
	}
	
	/**
	 * 
	 * @param ch - char of present Node we want, node is always present
	 * @return requested Node
	 */
	private Node returnNode(char ch){
		Node array[] = toArray(nodes);
		for(int i = 0; i < array.length; i++)
		{
			if(equal(array[i],ch))
			{
				return array[i];
			}
		}
		return null;
	}
	
	/**
	 * Used for test access only
	 * @param ch1 
	 * @param ch2
	 * @return if ch1 == ch2
	 */
	public boolean checkEqual(char ch1, char ch2){
		char upper1 = Character.toUpperCase(ch1);
		char upper2 = Character.toUpperCase(ch2);
		Node node = new Node(ch1);
		return equal(node,ch2);
	}
	
	/**
	 * 
	 * @param ch1 - node1 with char val ch1
	 * @param ch2 - node2 with char val ch1
	 * @return if node1 is parent of node2
	 */
	public boolean linked(char ch1, char ch2){
		Node node = returnNode(ch1);
		Node [] nodes = toArray(node.links);
		Node testNode;
		int size = nodes.length;
		int i;
		for(i = 0; i < size; i++)
		{
			testNode = nodes[i];
			if(equal(node,testNode.N))
			{
				return true;
			}
		}
		return false;
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
	
	/**
	 * 
	 * @return DAG array list as array of nodes
	 */
	private Node[] toArray(ArrayList<Node> list){
		Node listNodes[] = new Node[list.size()];
		listNodes = list.toArray(listNodes);
		return listNodes;
	}
	
	/**
	 * Prints all nodes
	 */
	public void printNodes(){
		Node listNodes[] = toArray(nodes);
		int size = listNodes.length;
		for(int i = 0; i < size-1; i++)
		{
			System.out.print(listNodes[i].N + ", ");
		}
		System.out.println(listNodes[size-1].N);
	}
}
