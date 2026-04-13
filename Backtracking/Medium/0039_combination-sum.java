class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    int Target;
    int[] arr;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        arr = candidates;
        Target = target;
        List<Integer> list = new ArrayList<>();
        Sum(list,0,0);
        return ans;

    }
    private void Sum(List<Integer> list,int idx,int sum){
        if(sum == Target)ans.add(new ArrayList(list));
        if(sum > Target)return;
        for(int i=idx;i<arr.length;i++){
            if(sum<Target){
                list.add(arr[i]);
                sum+=arr[i];
                Sum(list,i,sum);
                sum-=arr[i];
                list.removeLast();
            }
        }
    }
}