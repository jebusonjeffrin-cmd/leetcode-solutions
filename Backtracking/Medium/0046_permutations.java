class Solution {
    int[] arr;
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        arr = nums;
        List<Integer> list = new ArrayList<>();
        Permutate(list,0);
        
        return ans;
    }
    private void Permutate(List<Integer> list , int idx){
        if(list.size() == arr.length){
            ans.add(new ArrayList(list));
        }
        for(int i = idx;i<arr.length;i++){
                if(list.contains(arr[i]))continue;
                list.add(arr[i]);
                Permutate(list,0);
                list.removeLast();
            
        }
    }
}