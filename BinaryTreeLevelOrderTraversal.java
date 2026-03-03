// Time Complexity : O(n)
// Space Complexity : O(W) - width of the tree
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
/*
Have a queue where we initially add root and poll the elements level by level by taking snapshot of the
size of the queue. We also make sure to add current node's left and right nodes if available. This way of
using queue's FIFO principle to process the nodes and also taking snapshot approach helps us traverse
elements in a level order way.
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    List<List<Integer>> result;
    public List<List<Integer>> levelOrder(TreeNode root) {
        this.result = new ArrayList<>();
        traverseListHelper(root);
        return result;
    }

    private void traverseListHelper(TreeNode root) {
        if(root == null)
            return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> nodeList = new ArrayList<>();
            for(int i = 0 ; i < size ; i++) {
                TreeNode temp = queue.poll();
                nodeList.add(temp.val);

                if(temp.left != null)
                    queue.add(temp.left);
                if(temp.right != null)
                    queue.add(temp.right);
            }
            result.add(nodeList);
        }
    }
}