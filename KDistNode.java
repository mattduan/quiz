/*
 * 
 * Printing all nodes at a given distance from a starting node in a binary 
 * tree. 
 *
 * */

public class KDistNode {
  
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
		
	private void printNode(Node root, int distance) {
		if (root != null) {
			if (distance == 0)
				System.out.println(root.data);
			else if (distance > 0) {
				printNode(root.left, distance-1);
				printNode(root.right, distance-1);
			}
		}
	}
	
	public int findKDist(Node root, Node start, int distance) {		
		if (root != null) {
			//System.out.println(root.data);
			if (root == start) {
				printNode(root.left, distance-1);
				printNode(root.right, distance-1);
				return 1;
			} else {
				int ldist = findKDist(root.left, start, distance);
				if (ldist > 0 && distance > ldist) {
					printNode(root.right, distance-ldist-1);
					return ldist+1;
				}
				// not found in left, try right
				int rdist = findKDist(root.right, start, distance);
				if (rdist > 0 && distance > rdist) {
					printNode(root.left, distance-rdist-1);
					return rdist+1;
				}
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		KDistNode kdn = new KDistNode();
		Node[] nlist = new Node[14];
		for (int i=0; i<14; i++)
			nlist[i] = kdn.new Node(i+1);
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
		
		kdn.findKDist(root, nlist[1], 2);
	}

}
