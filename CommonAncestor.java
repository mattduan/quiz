/*
 * Finding the Lowest Common Ancestor of two given nodes in a Binary Tree.
 * */

public class CommonAncestor {

  public class Node {
		int data;
		Node left;
		Node right;
		
		public Node (int d) {
			data = d;
			left = null;
			right = null;
		}
	}
	
	private static int help(Node root, Node n1, Node n2) {
		if (root != null) {
			//System.out.println(root.data);
			int result = 0;
			if (root == n1 || root == n2)
				result = 1;
			result += help(root.left, n1, n2);
			if (result > 1)
				return result;
			return result + help(root.right, n1, n2);			
		}
		return 0;
	}
	
	public static Node commonAncestor(Node root, Node n1, Node n2) {
		if (root != null) {
			//System.out.println(root.data);
			if (root==n1 && root==n2)
				return root;
			int lr = help(root.left, n1, n2);
			if (lr > 1) {
				return commonAncestor(root.left, n1, n2);
			}
			int rr = help(root.right, n1, n2);
			if (lr==1 && rr==1)
				return root;
			if ((lr==1 || rr==1) && (root==n1 || root==n2))
				return root;
			return commonAncestor(root.right, n1, n2);
		}
		return null;
	}
	
	public static void main(String[] args) {
		CommonAncestor ca = new CommonAncestor();
		Node[] nlist = new Node[14];
		for (int i=0; i<14; i++)
			nlist[i] = ca.new Node(i+1);
		Node root = nlist[0];
		root.left = nlist[1];
		root.left.left = nlist[2];
		root.left.left.left = nlist[3];
		root.left.left.right = nlist[4];
		root.left.right = nlist[5];
		root.left.right.left = nlist[6];
		root.left.right.left.left = nlist[7];
		root.left.right.left.left.right = nlist[8];
		root.left.right.right = nlist[9];
		root.right = nlist[10];
		root.right.right = nlist[11];
		root.right.right.left = nlist[12];
		root.right.right.right = nlist[13];
        
		Node n = commonAncestor(root, nlist[3], nlist[8]);
		System.out.println(n==null?"null":n.data);
	}

}
