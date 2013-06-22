/*
 * Implement insert and delete in a tri-nary tree. Much like a binary
 * tree but with 3 child nodes for each parent instead of two – with 
 * the left node being values < parent, the right node values > parent,
 * and the middle node values == parent. 
 * */

import java.util.ArrayList;

public class TriTree {

  public class Node {
		public int value;
		public Node left;
		public Node middle;
		public Node right;
		
		public Node(int i) {
			this.value = i;
			this.left = null;
			this.right = null;
			this.middle = null;
		}
		
		public String toString() {
			return ""+this.value;
		}
	}
	
    private Node root;
    private int depth;
    
    public TriTree() {
    	this.root = null;
    	this.depth = 0;
    }
    
	public TriTree(int value) {
		Node node = new Node(value);
		this.root = node;
		this.depth = 0;
	}
	
	public void insert(int value) {
		if (this.root == null) {
			// create the root node
			this.root = new Node(value);
		} else {
			int level = this._insert(this.root, value);
			if (this.depth < level)
				this.depth = level;
		}
	}
	
	private int _insert(Node node, int value) {
		if (null == node) {
			node = new Node(value);
			return 0;
		}
		if (value > node.value) {
			if (node.right == null) {
				node.right = new Node(value);
				return 1;
			} else
				return this._insert(node.right, value)+1;
		} else if (value < node.value) {
			if (node.left == null) {
				node.left = new Node(value);
				return 1;
			} else
				return this._insert(node.left, value)+1;
		} else {
			if (node.middle == null) {
				node.middle = new Node(value);
				return 1;
			}
			else
				return this._insert(node.middle, value)+1;
		}
	}
	
	public void delete(int value) {
		this.root = this._delete(this.root, value);
	}
	
	private Node _delete(Node node, int value) {
		if (node.value == value) {
			if (node.middle != null)
				return this.deleteMiddle(node);
			else if (node.left != null) {
				Node root = this.bubbleBiggest(node.left);
				root.right = node.right;
				return root;
			} else if (node.right != null) {
				Node root = this.bubbleSmallest(node.right);
				root.left = node.left;
				return root;
			}
		} else if (node.value < value) {
			if (node.right != null)
				node.right = this._delete(node.right, value);
			return node;
		} else {
			if (node.left != null)
				node.left = this._delete(node.left, value);
			return node;
		}
		return null;
	}
	
	private Node deleteMiddle(Node root) {
		Node parent = null;
		Node node = root;
		while (node.middle != null) {
			parent = node;
			node = node.middle;
		}
		if (parent != null)
			parent.middle = null;
		return root;
	}
	
	private Node bubbleBiggest(Node root) {
		Node parent = null;
		Node node = root;
		while (node.right != null) {
		    parent = node;
			node = node.right;
		}
		if (parent != null) {
			parent.right = node.left;
			node.left = root;
		}
		return node;
	}
	
	private Node bubbleSmallest(Node root) {
		Node parent = null;
		Node node = root;
		while (node.left != null) {
			parent = node;
			node = node.left;
		}
		if (parent != null) {
			parent.left = node.right;
			node.right = root;
		}
		return node;
	}
	
	public String toString() {
		String s = "";
		ArrayList<Node> list = new ArrayList<Node>();
		list.add(this.root);
		list.add(null);
	    Node node;
		int size = list.size();
		int i = 0;
		while (i < size) {
			node = (Node)list.get(i);
			if (node == null) {
				if (i < size-1)
					list.add(null);
			} else {
				if (node.left != null)
					list.add(node.left);
				if (node.middle != null)
					list.add(node.middle);
				if (node.right != null)
					list.add(node.right);
			}
			size = list.size();
			i++;
		}
		int level = (this.depth+1)*2-1;
		String line1 = "";
		String line2 = "";
		for (i=0; i<list.size(); i++) {
			node = (Node)list.get(i);
			if (node == null) {
				s += "\n" + line1 + "\n" + line2;
				line1 = "";
				line2 = "";
				level -= 2;
			} else {
				if (line1 == "")
					line1 += new String(new char[level]).replace("\0", " ");
				if (line2 == "")
					line2 += new String(new char[level-1]).replace("\0", " ");
				line1 += node.value;
				if (node.left != null) {
					line2 += " /";
					line1 += "   ";
				}
				if (node.middle != null) {
					line2 += " |";
					line1 += "   ";
				}
				if (node.right != null) {
					line2 += " \\";
					line1 += "   ";
				}
				line2 += " ";
				line1 += " ";
			}
		}
		return s;
	}
	
	public static void main(String[] args) {
        int[] input = {5, 4, 9, 5, 7, 2, 2};
        
        TriTree tt = new TriTree();
        for (int i=0; i<input.length; i++) {
        	System.out.println(input[i]);
        	tt.insert(input[i]);
        }
        System.out.println(tt);
        
        tt.delete(9);
        System.out.println(tt);
        
        tt.delete(3);
        System.out.println(tt);
        
        tt.delete(5);
        System.out.println(tt);
        
        tt.delete(5);
        System.out.println(tt);
	}

}
