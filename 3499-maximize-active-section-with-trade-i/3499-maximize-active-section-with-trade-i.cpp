class Solution {
public:
    int maxActiveSectionsAfterTrade(string s) {
        vector<int> v;
        int ans=0,n=s.length(),i=0,ones=0;
        if(n==1 && s[0] == '1')return 1;
        if(n==1 && s[0] == '0')return 0;
        while(i<n){
            int j = i+1;
            if(s[i] == '0'){
                int curr = 1;
                while(j<n && s[j] == '0'){
                    curr++;j++;
                }
                v.push_back(curr);
                i=j;
            }else{
                ones++;
                i++;
            }
        }
        for(int i=0;i+1<(int)v.size();i++){
            ans = max(ans,v[i]+v[i+1]);
        }
        if(ones == 0)return 0;
        return ans+ones;
    }
};