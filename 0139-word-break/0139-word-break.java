class Solution {
    static boolean can;
    static HashMap<String, Integer> memo;
    public boolean wordBreak(String s, List<String> wordDict) {
        // dictionary의 단어들 중 하나 혹은 그 이상으로 s를 만들 수 있다면 true, 없으면 false;
        can = false;
        memo = new HashMap<>();
        memo.put(s, -1);
        dfs(s, wordDict);
        System.out.println(memo);
        return memo.get(s) == 1;
    }
    static int dfs(String s, List<String> wordDict) {
        
        if (memo.containsKey(s) && memo.get(s) != -1) {
            return memo.get(s);
        }

        if ("".equals(s)) {
            return 1;
        }
        memo.put(s, 0);
        for(int i = 0;i<wordDict.size();i++) {
            //System.out.println("target: "+s+" spliter: "+wordDict.get(i));
            String[] splits = s.split(wordDict.get(i));
            //System.out.println("split result: "+Arrays.toString(splits));
            if (splits.length == 0) {
                memo.put(s, 1);
                return memo.get(s);
            }
            boolean isFrags = true;
            for(int j = 0;j<splits.length;j++) {
                if (splits[j].equals("")) continue;
                int result = dfs(splits[j], wordDict);
                if (result == 0) {
                    isFrags = false;
                    break;
                }
                //System.out.println("s: "+s+" splits: "+splits[j]);
            }
            if (isFrags) memo.put(s, 1);
        }
        return memo.get(s);
    }
}