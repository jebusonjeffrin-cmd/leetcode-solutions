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
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<Integer> list = new ArrayList<>();
        Sum(root,list,0,targetSum);
        return ans;
    }
    private void Sum(TreeNode node ,List<Integer> list, int sum,int target){
        if(node == null)return;
        //if(sum>target)return;
        sum+=node.val;
        list.add(node.val);
        if(sum == target && node.left == null && node.right == null)ans.add(new ArrayList(list));
        Sum(node.left,list,sum,target);
        Sum(node.right,list,sum,target);
        list.removeLast();
    }
}