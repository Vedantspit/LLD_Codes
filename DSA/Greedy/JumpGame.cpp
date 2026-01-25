class Solution {
public:
    bool canJump(vector<int>& arr) {
        int maxInd=0;
        for(int i=0;i<arr.size();i++){
            if(i>maxInd) return false;

            if(arr[i]+ i >maxInd){
                maxInd = arr[i]+ i;
            }
        }

        return true;
    }
};