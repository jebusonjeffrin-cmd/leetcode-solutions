class Solution {
    public int maxArea(int[] height) {
        int max=0;
        int lt =0;int rt=height.length-1;
        while(lt < rt){
            int curr = Math.min(height[lt],height[rt]) * (rt-lt); 
            if(curr>max)max=curr;
            if(height[lt] <= height[rt])lt++;
            else rt--;
        }
        return max;
    }
}