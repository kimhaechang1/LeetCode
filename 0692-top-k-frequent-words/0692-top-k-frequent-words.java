class Solution {
    static int n;
    static List<String> answer;
    public List<String> topKFrequent(String[] words, int k) {
        n = words.length;
        answer = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for(String word: words) map.put(word, map.getOrDefault(word, 0) + 1);
        TreeSet<Map.Entry<String, Integer>> treeSet = new TreeSet<>((a, b) -> {
            if (a.getValue() == b.getValue()) {
                return a.getKey().compareTo(b.getKey());
            }
            return b.getValue() - a.getValue();
        });
        for(Map.Entry<String, Integer> entry: map.entrySet()) {
            treeSet.add(entry);
        }
        while(k-- > 0) answer.add(treeSet.pollFirst().getKey());
        return answer;
    }
}