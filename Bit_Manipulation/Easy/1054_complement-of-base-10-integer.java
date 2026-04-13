class Solution {
    public int bitwiseComplement(int n) {

        if(n == 0) return 1;

        StringBuilder str = new StringBuilder();

        while (n > 0) {
            int remainder = n % 2;
            str.insert(0, remainder);
            n = n / 2;
        }

        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '0')
                str.setCharAt(i, '1');
            else
                str.setCharAt(i, '0');
        }

        String binary = str.toString();
        int num = Integer.parseInt(binary, 2);

        return num;
    }
}