class Solution {
public:
    int minOperations(string s1, string s2) {
        int ops = 0;
        if (s1 == s2) return 0;
        if (s1.size() == 1) {
            if (s1[0] == s2[0]) return 0;
            else if (s1[0] == '0' && s2[0] == '1' ) return 1;
            else return -1;
        }
        for (int i=0; i<s1.length(); i++)  {
            if (s1[i] == '0' && s2[i] == '1') ops++;

            else if (s1[i] == '1' && s2[i] == '0') {
                if (i < s1.length()-1 ) {
                    if (s1[i+1] == '1') {
                        s1[i+1] = '0'; ops++;
                    } else if (s1[i+1] == '0') {
                        ops+=2;
                    }
                }
                else if (i == s1.length() - 1) {
                    if (s1[i-1] == '0') {
                        ops += 2;
                    }else {
                       ops += 2;
                    }
                }
            }
        }
        return ops;
    }
};