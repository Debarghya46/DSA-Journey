class Solution {
    public int numOfPairs(String[] nums, String target) {
    HashMap<String, Integer> mp = new HashMap<>();
    for(String str : nums)
    {
        mp.put(str, mp.getOrDefault(str, 0) + 1);
    } 

    int ans = 0;
    for(String s : mp.keySet())
    {
        if(target.startsWith(s))
        {
            if(target.equals(s+s))
            {
                int freq = mp.get(s);
                ans += (freq * (freq-1));
            }
            else
            {
                String substr = target.substring(s.length());
                if(mp.containsKey(substr))
                {
                    ans += (mp.get(s) * mp.get(substr));
                }
            }
        }
    }
    return ans;
    }
}