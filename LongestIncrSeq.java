/*
 * Given a vector of integers, find the longest consecutive sub-sequence 
 * of increasing numbers. If two sub-sequences have the same length, use 
 * the one that occurs first. An increasing sub-sequence must have a 
 * length of 2 or greater to qualify.
 * 
 * Example input:
 * [1 0 1 2 3 0 4 5]
 * Result:
 * [0 1 2 3]
 * */

import java.util.Arrays;

public class LongestIncrSeq {

  public static final int[] INVALID = {};
	
	public static int[] longestIncrSeq(int[] A) {
		// if length of the array is less than 2, return INVALID
		if (A.length < 2)
			return INVALID;
		
		// variables holding start and end indexes of the max sequence
		int max_start = 0;
		int max_end = 0;
		// variables holding start and end indexes of current sequence
		int current_start = 0;
		int current_end = 0;
		
		for (int i=1; i<A.length; i++) {
			if (A[i] < A[i-1]) {
				// order decrease, compare current length with max length
				if (current_end-current_start > max_end-max_start) {
					// find new max length, replace the max value
					max_start = current_start;
					max_end = current_end;
				}
				// reset the current value
				current_start = i;
				current_end = i;
			} else {
				// order increase, increase current_end
				current_end++;
			}
		}
		
		// check the last current length
		if (current_end-current_start > max_end-max_start) {
			max_start = current_start;
			max_end = current_end;
		}
		
		// if max start and end are same, return INVALID
		if (max_start == max_end)
			return INVALID;
		
		return Arrays.copyOfRange(A, max_start, max_end+1);
	}
	
	public static void main(String[] args) {
		int[] A = {1, 0, 1, 2, 3, 0, 4, 5};
		int[] subseq = longestIncrSeq(A);
		System.out.println(Arrays.toString(subseq));
	}

}
