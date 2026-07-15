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
    public int countNodes(TreeNode root) {
        if(root == null)return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int ans=0;
        while(!q.isEmpty()){
            TreeNode node = q.poll();
            ans++;
            if(node.left != null)q.offer(node.left);
            if(node.right != null)q.offer(node.right);
        }return ans;
    }
}