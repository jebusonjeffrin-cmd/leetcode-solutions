class Solution {
    int[] arr;
    int Target;
    List<List<Integer>> ans = new ArrayList<>();
    //HashSet<List<Integer>> set = new HashSet<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        arr = candidates;
        Target = target;
        List<Integer> list = new ArrayList<>();
        Sum2(list,0,0);
        //List<List<Integer>> ans = new ArrayList<>(set);
        return ans;

    }
    private void Sum2(List<Integer> list,int idx,int sum){
        if(sum==Target){
            ans.add(new ArrayList(list));
            return;
        }
        if(sum>Target)return;
        for(int i=idx;i<arr.length;i++){
                if(i>idx && arr[i] == arr[i-1])continue;
                list.add(arr[i]);
                Sum2(list,i+1,sum+arr[i]);
                list.removeLast();
        }
    }
}