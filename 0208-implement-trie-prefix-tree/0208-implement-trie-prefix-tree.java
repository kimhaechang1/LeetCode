class Node {
    HashMap<Character, Node> map;

    boolean isEnd;

    public Node() {
        map = new HashMap<>();
        isEnd = false;
    }

    public Node(boolean isEnd) {
        map = new HashMap<>();
        this.isEnd = isEnd;
    }
}
class Trie {

    Node head;

    public Trie() {
        head = new Node();
    }
    
    public void insert(String word) {
        Node node = head;
        char[] frags = word.toCharArray();
        for(int i = 0;i<frags.length;i++) {
            if (node.map.containsKey(frags[i])) {
                node = node.map.get(frags[i]);
            } else {
                node.map.put(frags[i], new Node());
                node = node.map.get(frags[i]);
            }
        }
        node.isEnd = true;
    }
    
    public boolean search(String word) {
        Node node = head;
        char[] frags = word.toCharArray();
        for(int i = 0;i<frags.length;i++) {
            if (node.map.get(frags[i]) != null) {
                node = node.map.get(frags[i]);
            } else {
                return false;
            }
        }
        return node.isEnd;
    }
    
    public boolean startsWith(String prefix) {
        Node node = head;
        char[] frags = prefix.toCharArray();
        for(int i = 0;i<frags.length;i++) {
            if (node.map.get(frags[i]) != null) {
                node = node.map.get(frags[i]);
            } else {
                return false;
            }
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */