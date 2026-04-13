#include <vector>
#include <string>
#include <climits>
#include <algorithm>
using namespace std;

class Solution {
public:
    int minimumDeletions(string s) {
        int n = s.length();

        vector<int> b_count(n, 0);
        vector<int> a_count(n, 0);
        for(int i = 1; i < n; i++){
            b_count[i] = b_count[i-1];
            if(s[i-1] == 'b'){
                b_count[i]++;
            }
        }
        for(int i = n-2; i >= 0; i--){
            a_count[i] = a_count[i+1];
            if(s[i+1] == 'a'){
                a_count[i]++;
            }
        }

        int mini = INT_MAX;

        for(int i = 0; i < n; i++){
            mini = min(mini, a_count[i] + b_count[i]);
        }

        return mini;
    }
};