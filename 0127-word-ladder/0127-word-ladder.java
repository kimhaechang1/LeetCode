class Solution {
    static HashMap<String, Integer> reverse;
    static int min;
    static final int MAX = 987654321;
    static class Node { 

        String w;
        int cost;
    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // beginword에서 endword로의 일연의 변화과정, wordList딕셔너리를 사용함으로서 
        // bfs?

        min = MAX;
        reverse = new HashMap<>();
        for(int i = 0;i<wordList.size();i++) {
            reverse.put(wordList.get(i), i);
        }
        
        if (!reverse.containsKey(endWord)) return 0;

        int ans = bfs(beginWord, endWord, wordList);
        
        return ans == MAX ? 0 : ans;
        
    }
    static int bfs(String start, String end, List<String> wordList) {
        int[] v = new int[wordList.size()];
        Arrays.fill(v, MAX);
        Queue<Node> queue = new ArrayDeque<>();
        Node node = new Node();
        node.w = start;
        node.cost = 1;
        System.out.println(node.w);
        queue.add(node);
        while(!queue.isEmpty()) {
            
            Node now = queue.poll();
            for(int i = 0;i<now.w.length();i++) {
                for(int j = 0;j<26;j++) {
                    if (now.w.charAt(i) == (char)('a' + j)) continue;
                    StringBuilder sb = new StringBuilder();
                    if (i > 0) sb.append(now.w.substring(0, i));
                    sb.append((char)('a' + j));
                    if (i + 1 < now.w.length()) sb.append(now.w.substring(i + 1, now.w.length()));
                    
                    if (reverse.containsKey(sb.toString()) && v[reverse.get(sb.toString())] > now.cost + 1) {
                        v[reverse.get(sb.toString())] = now.cost + 1;
                        Node nxt = new Node();
                        nxt.w = sb.toString();
                        nxt.cost = now.cost + 1;
                        queue.add(nxt);
                    }
                }
            }
        }
        return v[reverse.get(end)];
    }
}