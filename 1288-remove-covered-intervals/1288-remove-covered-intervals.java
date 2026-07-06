class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
       Arrays.sort(intervals,(a,b)->{
        if(a[0] != b[0])return a[0]-b[0];
        return b[1]-a[1];
       });
       int i=0,j=0,ans=0,n=intervals.length;
       while(i<n){
        j=i+1;
        int a = intervals[i][0],b=intervals[i][1];
        while(j<n && a<=intervals[j][0] && b>=intervals[j][1]){
            j++;
        }
        i=j;
        ans++;
       } 
       return ans;
    }
}