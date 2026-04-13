class Solution {
    public int removeDuplicates(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int count = 0;
        for(int n:nums){
            if(set.contains(n)){
                count++;
            }
            set.add(n);
        }
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        for(int i=0;i<set.size();i++){
            nums[i] = list.get(i);
        }
        return set.size();
    }
}