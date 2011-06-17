package ambient_network_simulation;

import java.util.List;

/**
 *
 * @author Karcsi
 */

public class Node {
	private String name;
	private List<Policy> policy;
	private boolean isSuperPeer;
	private VirtualNetwork isInThisVn;

	public void exit() {
		throw new UnsupportedOperationException();
	}

	public void updateVn() {
		throw new UnsupportedOperationException();
	}
}