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
    public TreeNode insertIntoBST(TreeNode root, int val) {
        return root = insert(root,val);
    }
    private TreeNode insert(TreeNode node , int val){
        if(node == null){
            return node = new TreeNode(val);
        }

        if(val<node.val){
            node.left = insert(node.left,val);
        }
        else if(val>node.val){
            node.right = insert(node.right,val);
        }
        return node;
    }
}