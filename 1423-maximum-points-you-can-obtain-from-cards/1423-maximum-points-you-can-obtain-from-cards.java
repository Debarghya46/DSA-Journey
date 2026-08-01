class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int n=cardPoints.length;
        int lsum=0;
        int rsum=0;
        for(int i=0;i<k;i++)
        {
            lsum+=cardPoints[i];
        }

        int left=k-1;
        int right=n-1;
        int maxi=lsum+rsum;
        while(left>=0)
        {
            lsum-=cardPoints[left];
            rsum+=cardPoints[right];
            maxi=Math.max(maxi,lsum+rsum);
            left--;
            right--;
        }
        return maxi;
    }
}