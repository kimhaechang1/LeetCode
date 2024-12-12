class Solution {

    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        TreeSet<Map.Entry<String, Integer>> pq = new TreeSet<>((a, b) -> {
            if (a.getValue() == b.getValue()) {
                return a.getKey().compareTo(b.getKey());
            }
            return b.getValue() - a.getValue();
        });
        List<String> answer = new ArrayList<>();
        
        for(String word: words) map.put(word, map.getOrDefault(word, 0) + 1);
        
        for(Map.Entry<String, Integer> entry: map.entrySet()) {
            pq.add(entry);
        }

        while(k-- > 0) answer.add(pq.pollFirst().getKey());

        return answer;
    }
}