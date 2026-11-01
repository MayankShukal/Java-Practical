package practical12;


import java.util.*;

public class LeaderBoard {
    public static void main(String[] args) {

        HashMap<String, Integer> scores = new HashMap<>();

        scores.put("Rahul", 95);
        scores.put("Mayank", 98);
        scores.put("Amit", 90);
        scores.put("Priya", 98);
        scores.put("Karan", 85);
        scores.put("Neha", 92);

        int N = 4;

        ArrayList<Map.Entry<String, Integer>> players =
                new ArrayList<>(scores.entrySet());

        Collections.sort(players, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> a,
                               Map.Entry<String, Integer> b) {

                int result = b.getValue().compareTo(a.getValue());

                if (result == 0) {
                    return a.getKey().compareTo(b.getKey());
                }

                return result;
            }
        });

        System.out.println("Top " + N + " Players:");

        for (int i = 0; i < N && i < players.size(); i++) {

            System.out.println(
                    players.get(i).getKey() + " - " +
                    players.get(i).getValue()
            );
        }
    }
}

