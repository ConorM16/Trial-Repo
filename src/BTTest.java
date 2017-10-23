import static org.junit.Assert.*;

import org.junit.Test;

public class BTTest {

	@Test
	public void testEmpty() {
		BinaryTree bt = new BinaryTree();
		assertEquals("Number is present:",false, bt.contains(1)); //test empty tree
	}
	
	@Test
	public void testTwoNodes() {
		BinaryTree bt = new BinaryTree();
		bt.putNode(10);
		assertEquals("Number is present:",true, bt.contains(10));	
		
		//bt.putNode(5);
		bt.putNode(15);
		assertEquals("Number is present:",true, bt.contains(15));
		
		assertFalse("Number is not present", bt.contains(12));
		}
	
	@Test
	public void testtenNodes(){
		BinaryTree bt = new BinaryTree();
		for(int i = 1; i < 10; i++)
		{
			bt.putNode(i);
		}
		
		assertEquals("Number is present:",true, bt.contains(9));
		assertEquals("Depth of 9 node:", 8, bt.depth(9));
	}
	
	@Test
	public void testDepth() {
		BinaryTree bt = new BinaryTree();
		bt.putNode(10);
		assertEquals("Number is present:",true, bt.contains(10));	
		assertEquals("Depth of 10 node:", 0, bt.depth(10));
		
		bt.putNode(15);
		assertEquals("Number is present:",true, bt.contains(15));
		assertEquals("Depth of 15 node:", 1, bt.depth(15));
		
		assertEquals("Depth of non-existent node:", 0, bt.depth(105));
	}
	
	@Test
	public void testAncestors(){
		BinaryTree bt = new BinaryTree();
		bt.putNode(10);
		bt.putNode(15);
		assertNull("Node has no ancestors", bt.ancestors(10));
		
		int [] expected = {10};
		assertArrayEquals("Node 15 has ancestors:",expected, bt.ancestors(15));
		
		bt.putNode(1);
		assertArrayEquals("Node 1 has ancestors:",expected, bt.ancestors(1));
		
		bt.putNode(12);
		int [] expected2 = {10,15};
		assertArrayEquals("Node 12 has ancestors:",expected2, bt.ancestors(12));		
	}
	
	@Test 
	public void testMultipleAncestors(){
		BinaryTree bt = new BinaryTree();
		
		bt.putNode(10);
		bt.putNode(15);
		bt.putNode(3);
		bt.putNode(1);
		bt.putNode(5);
		bt.putNode(12);
		bt.putNode(18);
		bt.putNode(14);
		
		int [] expected = {10,15,12};
		assertArrayEquals("Node 14 has ancestors:",expected, bt.ancestors(14));
		
		int [] expected2 = {10,3};
		assertArrayEquals("Node 5 has ancestors:",expected2, bt.ancestors(5));
		
		int [] expected3 = {10,15};
		assertArrayEquals("Node 12 has ancestors:",expected3, bt.ancestors(12));
	}
	
	@Test
	public void testGCA(){
		BinaryTree bt = new BinaryTree();
		
		bt.putNode(2);
		bt.putNode(1);
		bt.putNode(6);
		bt.putNode(10);
		bt.putNode(12);
		bt.putNode(7);
		bt.putNode(4);
		
		assertEquals("GCA of 6 and 1:",2, bt.gca(6, 1));
		assertEquals("GCA of 12 and 7:",10, bt.gca(12, 7));
		assertEquals("GCA of 4 and 7:",6, bt.gca(4, 7));
	}
	
	@Test
	public void testZeroInput(){
		BinaryTree bt = new BinaryTree();

		bt.putNode(0);
		bt.depth(0);
		bt.ancestors(0);
		bt.gca(0,7);
	}
	
	@Test
	public void testLinks(){
		BinaryTree bt = new BinaryTree();

		bt.putNode(2);
		bt.putNode(1);
		bt.putNode(3);
		
		bt.putNode(6);
		bt.putNode(7);
		bt.putNode(5);
		
		int [] expected = {1,3};
		int [] expected2 = {5,7};
		
		assertArrayEquals("Expected links:",expected,bt.checkLinks(2));
		assertArrayEquals("Expected links:",expected2,bt.checkLinks(6));
	}
	
//	@Test 
//	public void testInvalidInput(){
//		BinaryTree bt = new BinaryTree();
//		
//		assertEquals("Character is not an integer.",0, bt.isInt("a"));
//		assertEquals("1 is an integer.",1, bt.isInt("1"));
//	}
}
