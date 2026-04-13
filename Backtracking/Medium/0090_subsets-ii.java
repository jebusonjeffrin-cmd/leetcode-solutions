class Solution {
    HashSet<List<Integer>> set = new HashSet<>();
    int[] arr;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        arr = nums;
        List<Integer> list = new ArrayList<>();
        subset(list,0);
        List<List<Integer>> ans = new ArrayList<>(set);
        return ans;
    }
    private void subset(List<Integer> list , int idx){
        set.add(new ArrayList(list));
        for(int i=idx;i<arr.length;i++){
            list.add(arr[i]);
            subset(list,i+1);
            list.removeLast();
        }
    }
}

    
    