package practical13;


class Box<T> {
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}

class Pair<A, B> {
    private A first;
    private B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }
}

public class GenericContainers {

    public static <T> T getFirst(T[] array) {
        return array[0];
    }

    public static void main(String[] args) {

        Box<Integer> intBox = new Box<>();
        intBox.set(100);

        Box<String> stringBox = new Box<>();
        stringBox.set("Hello Java");

        System.out.println("Integer Box: " + intBox.get());
        System.out.println("String Box: " + stringBox.get());

        Pair<String, Integer> student =
                new Pair<>("Mayank", 94);

        System.out.println("Student Name: " + student.getFirst());
        System.out.println("Student Marks: " + student.getSecond());

        Integer[] numbers = {10, 20, 30};
        String[] names = {"Rahul", "Amit", "Priya"};

        System.out.println("First Number: " + getFirst(numbers));
        System.out.println("First Name: " + getFirst(names));
    }
}

