import static org.junit.Assert.*;

import org.junit.Test;

public class DAGTest {

	@Test
	public void testAdd() {
		DAG dag = new DAG();
		assertTrue("Node is present", dag.addNode('A'));
	}
	
	@Test 
	public void testPrint(){
		DAG dag = new DAG();
		dag.addNode('A');
		dag.addNode('B');
		dag.addNode('C');
		dag.printNodes();
	}

}
