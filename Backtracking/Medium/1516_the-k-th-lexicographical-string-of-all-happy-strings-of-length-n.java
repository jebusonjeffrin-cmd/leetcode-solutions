class Solution {
    public String getHappyString(int n, int k) {
        
        int total = 3 * (1 << (n - 1));
        if (k > total) return "";
        
        StringBuilder result = new StringBuilder();
        char[] letters = {'a','b','c'};
        
        char prev = '#';
        
        for(int i = 0; i < n; i++) {
            
            int remaining = n - i - 1;
            int blockSize = 1 << remaining;
            
            for(char c : letters) {
                
                if(c == prev) continue;
                
                if(k > blockSize) {
                    k -= blockSize;
                } 
                else {
                    result.append(c);
                    prev = c;
                    break;
                }
            }
        }
        
        return result.toString();
    }
}