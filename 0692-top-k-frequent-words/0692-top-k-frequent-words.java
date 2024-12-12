class Solution {
    static int n;
    static List<String> answer;
    static class Data implements Comparable<Data>{
        String w;
        int cnt;

        public Data(String w, int cnt) {
            this.w = w;
            this.cnt = cnt;
        }

        public int compareTo(Data o) {
            if (this.cnt == o.cnt) {
                return this.w.compareTo(o.w);
            }
            return o.cnt - this.cnt;
        }
    }
    public List<String> topKFrequent(String[] words, int k) {
        n = words.length;
        answer = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for(String word: words) map.put(word, map.getOrDefault(word, 0) + 1);
        TreeSet<Data> treeSet = new TreeSet<>();
        for(String word: map.keySet()) {
            treeSet.add(new Data(word, map.get(word)));
            if (treeSet.size() > k) {
                treeSet.pollLast();
            }
        }
        while(k-- > 0) answer.add(treeSet.pollFirst().w);
        return answer;
    }
}