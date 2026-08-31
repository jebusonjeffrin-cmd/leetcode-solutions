/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    vector<int> nodesBetweenCriticalPoints(ListNode* head) {
        vector<int> v;
        ListNode* prev = head;
        ListNode* temp = head->next;
        int i=2;
        while(temp->next != nullptr){
            if(temp->val > prev->val && temp->val > temp->next->val ||temp->val < prev->val && temp->val < temp->next->val){
                v.push_back(i);
            }
            prev = temp;
            temp = temp->next;
            i++;
        }
        if(v.size() < 2)return {-1,-1};
        int mn=v[0],mx=v.back();
        int mini=INT_MAX;
        for(int i=0;i<v.size()-1;i++){
            mini = min(mini,v[i+1]-v[i]);
        }
        return {mini,mx-mn};
    }
};