class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        HashMap<Character,Integer> map = new HashMap<>();
        int count=0;
        for(char i='a';i<='z';i++){
            map.put(i,weights[count++]);
        }
        StringBuilder str = new StringBuilder();
        for(int i=0;i<words.length;i++){
            str.append(calcweight(words[i],map));
        }
        String s = new String(str);
        return s;
    }
    private Character calcweight(String s,HashMap<Character,Integer> map){
        int n=0;
        for(char c:s.toCharArray()){
          n=n+map.get(c);
        }
        n=n%26;
        char key;
        return (char)('z'-n);
    }
}