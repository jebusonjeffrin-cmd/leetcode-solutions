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
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return dfs(root,targetSum,0);
    }
    private boolean dfs(TreeNode node , int target , int sum){
        if(node == null)return false;
        sum+=node.val;
        if(sum == target && node.left == null && node.right == null)return true;
        
        return dfs(node.left,target,sum) || dfs(node.right,target,sum);
    }
}