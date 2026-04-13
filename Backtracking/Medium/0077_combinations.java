class Solution {
    int[] arr;
    int N,K;
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        int[] array = new int[n+1];
        for(int i=1;i<=n;i++){
            array[i] = i;
        }
        arr = array;N=n;K=k;
        List<Integer> list = new ArrayList<>();
        Backtrack(list,1);
        return ans;
    }
    private void Backtrack(List<Integer> list,int idx){
        //if(list.size() > K)return;
        if(list.size() == K){
            ans.add(new ArrayList(list));
            return;
        }
        for(int i=idx;i<arr.length;i++){
            list.add(arr[i]);
            Backtrack(list,i+1);
            list.removeLast();
        }
    }
}