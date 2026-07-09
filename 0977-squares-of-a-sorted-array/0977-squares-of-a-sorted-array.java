class Solution {
    public int[] sortedSquares(int[] nums) {
        int n=nums.length;
        int[] ans=new int[n];
        int left=0;
        int right=n-1;
        int index=n-1;

        while(left<=right){
            int leftSqu=nums[left]*nums[left];
            int rightSqu=nums[right]*nums[right];
            if(leftSqu>rightSqu){
                ans[index]=leftSqu;
                left++;
            }else{
                ans[index]=rightSqu;
                right--;
            }
            index--;
        }
        return ans;
    }
}