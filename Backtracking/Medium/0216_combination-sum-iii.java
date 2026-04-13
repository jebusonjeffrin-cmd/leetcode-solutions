class Solution {
    int[] arr = {1,2,3,4,5,6,7,8,9};
    int K;
    int N;
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        K=k;
        N=n;
        List<Integer> list = new ArrayList<>();
        Sum3(list,0,0);
        return ans;

    }
    private void Sum3(List<Integer> list,int idx,int sum){
        if(sum>N)return;
        if(sum==N && list.size() == K)ans.add(new ArrayList(list));
        for(int i=idx;i<arr.length;i++){
            list.add(arr[i]);
            Sum3(list,i+1,sum+arr[i]);
            list.removeLast();
        }
    }
}