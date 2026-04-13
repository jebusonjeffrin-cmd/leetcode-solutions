class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    int n2 = nums2.length,n1 = nums1.length;
    int[] ans = new int[n1];
    Stack<Integer> stack = new Stack<>();
    HashMap<Integer,Integer> map = new HashMap<>();
    for(int i=n2-1;i>=0;i--){
        if(!stack.isEmpty()){
            while(!stack.isEmpty() && stack.peek()<nums2[i]){
                stack.pop();
            }
            if(stack.isEmpty())map.put(nums2[i],-1);
            else map.put(nums2[i],stack.peek());
            stack.push(nums2[i]);

        }
        else{
            stack.push(nums2[i]);
            map.put(nums2[i],-1);
        }
        
    }
    for(int j=0;j<n1;j++){
            ans[j] = map.get(nums1[j]);
        }
    return ans;
    }
}