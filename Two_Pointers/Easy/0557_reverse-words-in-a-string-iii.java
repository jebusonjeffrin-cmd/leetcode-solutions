class Solution {
    public String reverseWords(String s) {
        String[] str = s.split(" ");
        int n = str.length;
        for(int i=0;i<n;i++){
            str[i] = reverse(str[i]);
        }
        return String.join(" ", str);


    }
    public String reverse(String s1){
        char a[] = s1.toCharArray();
        int i=0;
        int j=a.length-1;
        while(i<j){
            char temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i++;
            j--;
        }
        return new String(a);
    }
}