class Solution {
    public int maximumPopulation(int[][] logs) {
        int year = -1;
        int n = logs.length;
        int[][] intervals = new int[2*n][2];
        for(int i=0;i<n;i++){
        intervals[2*i][0] = logs[i][0];intervals[2*i][1] = 1;
        intervals[2*i+1][0] = logs[i][1];intervals[2*i+1][1] = -1;
        }
        Arrays.sort(intervals,(a,b)->{
        if(a[0] != b[0])return a[0]-b[0];
        return a[1]-b[1];
        });
        int mxpop=0,currpop=0;
        for(int[] interval:intervals){
            System.out.println(Arrays.toString(interval));
        }
        for(int[] interval:intervals){
            currpop += interval[1];
            
            if(currpop > mxpop){
                mxpop = currpop;
                year = interval[0];
            }
        }
        return year;
    }
}