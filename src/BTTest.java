import static org.junit.Assert.*;

import org.junit.Test;

public class BTTest {

	@Test
	public void testEmpty() {
		BinaryTree bt = new BinaryTree();
		assertEquals("Number is present:",false, bt.contains(1)); //test empty tree
	}
	
	@Test
	public void testput() {
		BinaryTree bt = new BinaryTree();
		bt.putNode(10);
		assertEquals("Number is present:",true, bt.contains(10));	
		
		//bt.putNode(5);
		bt.putNode(15);
		assertEquals("Number is present:",true, bt.contains(15));
		
		assertFalse("Number is not present", bt.contains(12));
		
		for(int i = 1; i < 10; i++)
		{
			bt.putNode(i);
		}
		
		assertEquals("Number is present:",true, bt.contains(9));
		}

}
