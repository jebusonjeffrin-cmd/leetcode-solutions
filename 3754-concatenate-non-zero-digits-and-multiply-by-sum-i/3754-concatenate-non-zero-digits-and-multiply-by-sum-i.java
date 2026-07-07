class Solution {
    public long sumAndMultiply(int n) {
        if(n==0)return 0;
       long ans=0;
       long sum=0;
       String s = Long.toString(n);
       StringBuilder sb = new StringBuilder();
       for(char ch:s.toCharArray()){
        sum += (ch-'0');
        if(ch != '0')sb.append(ch);
       }
       s = new String(sb);
       ans = Long.parseLong(s);
       return ans*sum;
    }
}