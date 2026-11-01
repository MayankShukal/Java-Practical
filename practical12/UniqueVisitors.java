package practical12;


import java.util.*;

public class UniqueVisitors {
    public static void main(String[] args) {

        ArrayList<String> visitors = new ArrayList<>();

        visitors.add("Mayank");
        visitors.add("Rahul");
        visitors.add("Amit");
        visitors.add("Mayank");
        visitors.add("Priya");
        visitors.add("Rahul");
        visitors.add("Karan");

        HashSet<String> uniqueUsers = new HashSet<>(visitors);

        LinkedHashSet<String> orderedUsers =
                new LinkedHashSet<>(visitors);

        System.out.println("Unique visitors count: " + uniqueUsers.size());

        System.out.println("First three distinct visitors:");

        int count = 0;

        for (String name : orderedUsers) {
            if (count == 3) {
                break;
            }

            System.out.println(name);
            count++;
        }
    }
}

