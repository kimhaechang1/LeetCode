class LRUCache {
    
    static TreeMap<Integer, Integer> timeMap;
    static int[] keys;
    static int pc;
    static int sz;
    static int[] keyTime;
    static int caps;

    public LRUCache(int capacity) {
        // 가장 최근에 사용하지 않은 페이지를 교체
        timeMap = new TreeMap<>(); // time LRU
        keys = new int[10001]; // key -> value
        keyTime = new int[10001]; // key -> time
        Arrays.fill(keys, -1);
        pc = 0;
        sz = 0;
        caps = capacity;
    }
    
    public int get(int key) {
        if (keys[key] != -1) {
            timeMap.remove(keyTime[key]);
            timeMap.put(++pc, key);
            keyTime[key] = pc;
        }
        return keys[key];
    }
    
    public void put(int key, int value) {
        if (keys[key] != -1) {
            timeMap.remove(keyTime[key]);
            timeMap.put(++pc, key);
            keyTime[key] = pc;
            keys[key] = value;
        } else {
            // 새롭게 넣을껀데 검사를 해야지
            if (sz == caps) {
                Map.Entry<Integer, Integer> entry = timeMap.pollFirstEntry();
                int lastKey = entry.getValue();
                keys[lastKey] = -1;
                keyTime[lastKey] = 0;
                keys[key] = value;
                keyTime[key] = ++pc;
                timeMap.put(pc, key);
            } else if (sz < caps) {
                ++sz;
                keys[key] = value;
                keyTime[key] = ++pc;
                timeMap.put(pc, key);
                
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */