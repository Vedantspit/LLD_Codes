class Solution {
public
    int findContentChildren(vectorint& g, vectorint& s) {
        int n=g.size(),m=s.size();
        sort(g.begin(),g.end());
        sort(s.begin(),s.end());
        int l=0,r=0;
        while(ln && rm){
            if(g[l]=s[r]){
              l++;
            }
            r++;
        }
        return l;
    }
};