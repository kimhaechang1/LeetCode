class Solution {
    static int n;
    static List<String> answer;
    static class Data implements Comparable<Data>{
        String w;
        int cnt;

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
        Arrays.sort(words);
        String prev = "";
        int cnt = 0;
        PriorityQueue<Data> pq = new PriorityQueue<>();
        for(int i = 0;i < n ;i++) {
            if (prev.equals(words[i])) {
                cnt++;
            } else {
                Data newData = new Data();
                newData.w = prev;
                newData.cnt = cnt;
                cnt = 1;
                prev = words[i];
                if (cnt == 0) continue;
                pq.add(newData);
            }
        }
        Data newData = new Data();
        newData.w = words[n - 1];
        newData.cnt = cnt;
        pq.add(newData);
        
        while(k-- > 0) {
            answer.add(pq.poll().w);
        }

        return answer;
    }
}