class Solution {
    public boolean isPalindrome(String s) {
       int start = 0, end = s.length()-1;

       while(start < end)
       {
        int currFirst = s.charAt(start);
        int currLast = s.charAt(end);
        if(!Character.isLetterOrDigit(currFirst))
        {
            start++;
        }
        else if(!Character.isLetterOrDigit(currLast))
        {
            end--;
        }
        else
        {
            if(Character.toLowerCase(currFirst) != Character.toLowerCase(currLast))
            {
                return false;
            }
            start++;
            end--;
        }
       } 
       return true;
    }
}