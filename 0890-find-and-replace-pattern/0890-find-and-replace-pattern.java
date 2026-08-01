class Solution {
    public boolean match(String currWord, String pattern)
    {
        if(currWord.length() != pattern.length())
        {
            return false;
        }
            HashMap<Character, Character> mp = new HashMap<>();
            for(int i=0; i< pattern.length(); i++)
            {
                if(mp.containsKey(pattern.charAt(i)))
                {
                    if(mp.get(pattern.charAt(i)) != currWord.charAt(i))
                    {
                        return false;
                    }
                }
                    else
                    {
                        mp.put(pattern.charAt(i), currWord.charAt(i));
                    }
            }
                return true;
     }
        
    
    public List<String> findAndReplacePattern(String[] words, String pattern) {

       List<String> ans = new ArrayList<>();

       for(int i=0; i< words.length; i++)
       {
        String currWord = words[i];
        if(match(currWord, pattern) && match(pattern, currWord))
        {
            ans.add(currWord);
        }
       } 
       return ans;
    }
}