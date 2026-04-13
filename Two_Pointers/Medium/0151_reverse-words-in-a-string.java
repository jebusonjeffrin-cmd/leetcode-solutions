class Solution {
    public String reverseWords(String s) {
       StringBuilder str = new StringBuilder();
       String[] arr = s.trim().split("\\s+");
       for(int i=arr.length-1;i>=0;i--){
            if(i==0){
                str.append(arr[i]);
            }
            else{
            str.append(arr[i] + " ");
            }
       } 
       return str.toString();
    }
}