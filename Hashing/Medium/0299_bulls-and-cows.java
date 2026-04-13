class Solution {
    public String getHint(String secret, String guess) {
        HashMap<Character,Integer> map = new HashMap<>();
        for(char ch :secret.toCharArray()){
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        int bulls=0;
        for(int i=0;i<secret.length();i++){
            if(secret.charAt(i) == guess.charAt(i)){
                bulls++;
            }
        }
        int cows=0;
        for(int i=0;i<secret.length();i++){
            if(map.containsKey(guess.charAt(i))){
                int freq = map.get(guess.charAt(i));
                if(freq == 0)map.remove(guess.charAt(i));
                else{ map.put(guess.charAt(i),freq-1);
                cows++;
                }
            }
        }
        cows-=bulls;
        return Integer.toString(bulls) + "A"+Integer.toString(cows)+"B";
    }  
}