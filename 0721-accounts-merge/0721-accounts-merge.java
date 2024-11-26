class Solution {

    static int[] p;
    static int[] rank;

    static void init(int len) {
        p = new int[len];
        rank = new int[len];
        for(int i = 0;i<len;i++) {
            p[i] = i;
            rank[i] = 1;
        }
    }

    static int find(int x) {
        if (x == p[x]) return p[x];
        return p[x] = find(p[x]);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return false;
        if (rank[a] > rank[b]) {
            p[b] = a;
            rank[a] += rank[b];
        } else {  
            p[a] = b;
            rank[b] += rank[a];
        }
        return true;
    }
    

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        
        // 유니온 파인드
        // 서로 같은 이름의 다른사람이 있을 수 있음, 하지만 서로 사용하는 이메일이 같다면 같은 사람임
        // 결국 어떤 이메일들끼리 서로 묶이는지가 중요하다.
        // 만약 서로다른 account에서 같은 이메일이 나온거라면 같은 사람으로 취급해야 한다.
        HashMap<String, Integer> emailMap = new HashMap<>();
        init(accounts.size());
        for(int i = 0;i<accounts.size();i++) {
            for(int j = 1;j< accounts.get(i).size(); j++) {
                String email = accounts.get(i).get(j);
                if (emailMap.containsKey(email)) {
                    // 해당 이메일을 사용하는 사람이 등록되어 있고
                    // 다른 i번째 사람이 해당 이메일을 사용했엇다면
                    // 둘은 같은사람으로 취급함
                    union(emailMap.get(email), i);
                } else {
                    // 이 이메일은 i번째 사람이 사용하고 있음
                    emailMap.put(email, i);
                }
            }
        }

        HashMap<Integer, ArrayList<String>> personMap = new HashMap<>();
        for(Map.Entry<String, Integer> entry: emailMap.entrySet()) {
            String email = entry.getKey();
            int parent = find(emailMap.get(email));
            
            ArrayList<String> list = personMap.get(parent);
            if (list == null) list = new ArrayList<>();
            list.add(email);
            personMap.put(parent, list);
        }

        List<List<String>> answer = new ArrayList<>();
        for(Map.Entry<Integer, ArrayList<String>> entry: personMap.entrySet()) {
            List<String> ansList = new ArrayList<>();
            Collections.sort(entry.getValue());
            ansList.add(accounts.get(entry.getKey()).get(0));
            ansList.addAll(entry.getValue());
            answer.add(ansList);
        }
        return answer;


        
    }
}