class WordDictionary {
    // 트라이 접근
    // . 의 경우에는 아무문자가 와도 상관없으니까, bfs로 접근해도 될듯
    static class Trie {

        Node head;

        public Trie() {
            head = new Node();
        }
        
        public void add(String word) {
            char[] ch = word.toCharArray();
            Node pres = head;
            for(int i = 0; i < ch.length; i++) {
                if (pres.map.containsKey(ch[i])) {
                    pres = pres.map.get(ch[i]);
                } else {
                    pres.map.put(ch[i], new Node());
                    pres = pres.map.get(ch[i]);
                }
            }
            pres.isEnd = true;
        }
        public boolean search(String word) {
            char[] ch = word.toCharArray();
            Queue<Node> queue = new ArrayDeque<>();
            if (ch[0] == '.') {
                for(Map.Entry<Character, Node> entry: this.head.map.entrySet()) {
                    if (entry.getValue() != null) {
                        queue.add(entry.getValue());
                    }
                }
            } else if (ch[0] != '.' && this.head.map.containsKey(ch[0])) {
                queue.add(this.head.map.get(ch[0]));
            }
            int len = 1;
            while(!queue.isEmpty()) {
                int size = queue.size();
                for(int i = 0; i < size; i ++){
                    Node now = queue.poll();
                    if (len == word.length() && now.isEnd) {
                        return true;
                    }

                    if (len < word.length()) {
                        if (ch[len] == '.') {
                            for(Map.Entry<Character, Node> entry: now.map.entrySet()) {
                                if (entry.getValue() != null) {
                                    queue.add(entry.getValue());
                                }
                            }
                        } else if (ch[len] != '.' && now.map.containsKey(ch[len])) {
                            queue.add(now.map.get(ch[len]));
                        }
                    }
                }
                len++;
            }
            return false;
        }
    }
    static class Node { 
        
        HashMap<Character, Node> map;
        boolean isEnd;

        public Node() {
            map = new HashMap<>();
        }
    }
    static Trie trie;
    public WordDictionary() {
        trie = new Trie();
    }
    
    public void addWord(String word) {
        trie.add(word);
    }
    
    public boolean search(String word) {
        return trie.search(word);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */