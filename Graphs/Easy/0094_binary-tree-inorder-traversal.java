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
    List<Integer> lst = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        InOrder(root);
        return lst;
    }
    private void InOrder(TreeNode node){
        if(node == null){
            return ;
        }
        InOrder(node.left);
        lst.add(node.val);
        InOrder(node.right);
    }
}