package hashmap;

public class FixedSizeHashMap {

	private int _capacity;
	private int _size;
	private int POINT_TO_REBALANCE = 15;
	private FixedSizeHashMapNode _root;
	private int _inorderIndex = 0;
	
	public FixedSizeHashMap(int capacity) {
		_capacity = capacity;
		_size = 0;
	}
	
	public boolean set(String key, Object value) {
		
		if (_size == _capacity) {
			return false;
		}
		
		int hashCode = key.hashCode();
		FixedSizeHashMapNode newNode = new FixedSizeHashMapNode(hashCode, value);
		if (add(_root, newNode)) {
			if (_size % POINT_TO_REBALANCE == 0) {
				_root = balance(toList(), 0, _size - 1);
			}
			
			return true;
		} 
		
		return false;
	}
	
	/*
	 * Recursive method for adding the node to the hashmap.
	 * @param - root: root node to add children to
	 * 			node: child node to add
	 */
	private boolean add(FixedSizeHashMapNode root, FixedSizeHashMapNode node) {
		if (_root == null) {
			_root = node;
			_size++;
			return true;
		}
		
		if (node.getKey() > root.getKey()) {
			if (!root.hasRight()) {
				root.setRight(node);
				_size++;
				return true;
			}
			return add(root.getRight(), node);
		} else if (node.getKey() < root.getKey()){
			if (!root.hasLeft()) {
				root.setLeft(node);
				_size++;
				return true;
			}
			return add(root.getLeft(), node);
		} else {
			root.setValue(node.getValue());
			return true;
		}

	}
	
	/*
	 * Re-balances the binary search tree to ensure look up time is O(log n). Executed when every
	 * POINT_TO_REBALNCE-th element is added to the hashmap, to ensure amortized runtime of set() is kept
	 * at a minimum.
	 * @param - nodes: a sorted list of existing nodes in the hashmap
	 * 			start: start index
	 * 			end: end index
	 */
	public FixedSizeHashMapNode balance(FixedSizeHashMapNode[] nodes, int start, int end) {
		if (start == end) {
			nodes[start].setLeft(null);
			nodes[start].setRight(null);
			return nodes[start];
		} else if (end < start) {
			return null;
		}
		
		int medianIndex = (start + end) / 2;
		FixedSizeHashMapNode median = nodes[medianIndex];
		median.setLeft(balance(nodes, start, medianIndex - 1));
		median.setRight(balance(nodes, medianIndex + 1, end));
		return median;
	}
	
	public Object get(String key) {
		if (_root == null) {
			return null;
		}
		
		FixedSizeHashMapNode found = find(_root, key);
		if (found != null) {
			return found.getValue();
		}
		
		return null;
	}
	
	/*
	 * Recursive function for finding the Object with the given key.
	 * @param - root: node at which to look for object with given key
	 * 			key: the given key
	 */
	public FixedSizeHashMapNode find(FixedSizeHashMapNode root, String key) {
		int rootKey = root.getKey();
		int nodeKey = key.hashCode();
		if (nodeKey == rootKey) {
			return root;
		} else if (nodeKey > rootKey) {
			if (root.hasRight()) {
				return find(root.getRight(), key);
			} 			
		} else if (nodeKey < rootKey) {
			if (root.hasLeft()) {
				return find(root.getLeft(), key);
			}
		}
		
		return null;
	}
	
	public Object delete(String key) {
		
		FixedSizeHashMapNode nodeToDelete = find(_root, key);
		if (nodeToDelete == null) {
			return null;
		}
		Object value = nodeToDelete.getValue();
		
		// temporarily set the value of the node to null so that tree remains balanced;
		// null-valued nodes will be removed upon balancing
		nodeToDelete.setValue(null);
		_size--;
		
		if (_size % POINT_TO_REBALANCE == 0) {
			_root = balance(toList(), 0, _size - 1);
		}
		
		return value;
	}
	
	public float load() {
		return (float) _size / _capacity;
	}
	
	/*
	 * Returns an array of all existing nodes, sorted by key.
	 */
	public FixedSizeHashMapNode[] toList() {
		if (_size == 0) {
			return null;
		}
		
		FixedSizeHashMapNode[] nodes = new FixedSizeHashMapNode[_size];
		inorderTraverse(_root, nodes);
		_inorderIndex = 0;
		return nodes;
	}
	
	/*
	 * Stores the nodes in the hashmap in-order in the given array.
	 * @param - root: root node of the hashmap
	 * 			nodes: an array instantiated at the size of the hashmap
	 * 			currIndex: index at which to store the next node in-order 
	 */
	private void inorderTraverse(FixedSizeHashMapNode root, FixedSizeHashMapNode[] nodes) {
		if (root.hasLeft()) {
			inorderTraverse(root.getLeft(), nodes);
		}
		
		if (root.getValue() != null) {
			nodes[_inorderIndex] = root;
			_inorderIndex++;
		}
		
		if (root.hasRight()) {
			inorderTraverse(root.getRight(), nodes);
		}
	}
	 
	/*
	 * For testing purposes, to see if balanced binary tree is maintained.
	 * @param - node: node to treat as root node, at which to begin depth calculation
	 */
	protected int depth(FixedSizeHashMapNode node) {
		if (node == null) {
			return 0;
		}
		
		return 1 + Math.max(depth(node.getLeft()), depth(node.getRight())); 
	}
	
	protected FixedSizeHashMapNode getRoot() {
		return _root;
	}
	
	
	private class FixedSizeHashMapNode {
		
		private int _key;
		private Object _value;
		private FixedSizeHashMapNode _left;
		private FixedSizeHashMapNode _right;
		
		private FixedSizeHashMapNode(int key, Object value) {
			_key = key;
			_value = value;
			_left = null;
			_right = null;
		}
		
		private int getKey() {
			return _key;
		}
		
		private Object getValue() {
			return _value;
		}
		
		private boolean hasLeft() {
			if (_left == null) {
				return false;
			} 
			return true;
		}
		
		private boolean hasRight() {
			if (_right == null) {
				return false;
			} 
			return true;
		}
		
		private FixedSizeHashMapNode getLeft() {
			return _left;
		}
		
		private FixedSizeHashMapNode getRight() {
			return _right;
		}
		
		private void setLeft(FixedSizeHashMapNode node) {
			_left = node;
		}
		
		private void setRight(FixedSizeHashMapNode node) {
			_right = node;
		}
		
		private void setValue(Object value) {
			_value = value;
		}

	}
	
}
