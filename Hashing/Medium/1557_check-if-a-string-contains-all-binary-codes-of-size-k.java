class Solution {
    public boolean hasAllCodes(String s, int k) {
        if(k>s.length())return false;
        HashSet<String> set = new HashSet<>();
        StringBuilder currstr = new StringBuilder();
        for(int i=0;i<k;i++){
            currstr.append(s.charAt(i));
        }
        set.add(currstr.toString());
        for(int i=k;i<s.length();i++){
            currstr.deleteCharAt(0);
            currstr.append(s.charAt(i));
            if(!set.contains(currstr)){
                set.add(currstr.toString());
            }
        }
        if(set.size() == Math.pow(2,k)){
            return true;
        }
        return false;
    }
}