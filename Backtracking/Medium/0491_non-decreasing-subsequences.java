class Solution {
    int arr[];
    HashSet<List<Integer>> set = new HashSet<>();
    public List<List<Integer>> findSubsequences(int[] nums) {
       arr = nums;
       List<Integer> list = new ArrayList<>();
       subsequence(list,0);
       List<List<Integer>> ans = new ArrayList<>(set);
       return ans;
    }
    private void subsequence(List<Integer> list,int idx){
        if(list.size() >=2)set.add(new ArrayList(list));

        for(int i=idx;i<arr.length;i++){
            if(list.size() == 0 || arr[i] >= list.get(list.size()-1)){
                list.add(arr[i]);
                subsequence(list,i+1);
                list.remove(list.size()-1);
            }
        }
    }
}