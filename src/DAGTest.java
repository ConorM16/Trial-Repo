import static org.junit.Assert.*;

import org.junit.Test;

public class DAGTest {

	@Test
	public void testContains(){
		DAG dag = new DAG();
		dag.addNode('A');
		assertTrue("Node is present",dag.containsNode('A'));
		assertFalse("Node is present",dag.containsNode('Z'));
	}
	
	@Test
	public void testAdd() {
		DAG dag = new DAG();
		assertTrue("Node is not present", dag.addNode('A'));
		assertFalse("Node is already present", dag.addNode('A'));
	}
	
	@Test 
	public void testPrint(){
		DAG dag = new DAG();
		dag.addNode('A');
		dag.addNode('B');
		dag.addNode('C');
		dag.printNodes();
	}
	
	@Test
	public void testEqual(){
		DAG dag = new DAG();
		dag.addNode('A');
		assertTrue("Two characters are the same.",dag.checkEqual('A','A'));
		assertFalse("Two characters are not the same.",dag.checkEqual('B','A'));
	}
	
	@Test
	public void testRequests(){
		DAG dag = new DAG();
		dag.requestLink('A','B');
	}
	
//	@Test
//	public void testArrLists(){
//		DAG dag = new DAG();
//		dag.addNode('A');
//		assertTrue("Node is present", dag.containsNode('A'));
//	}

}
