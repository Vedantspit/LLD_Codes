

class Solution {
  public:
    long long solve(vector<int>& arr) {
        // code here
        sort(arr.begin(),arr.end());
        int wtTime = 0;
        int t=0;
        for(int i=0;i<arr.size();i++){
            wtTime+=t;
            t+=arr[i];
        }
        return wtTime/arr.size();
    }
};