package practical13;


class GenericStack<T> {

    private Object[] stack;
    private int top;

    public GenericStack(int size) {
        stack = new Object[size];
        top = -1;
    }

    public void push(T value) {

        if (top == stack.length - 1) {
            System.out.println("Stack Overflow");
            return;
        }

        stack[++top] = value;
    }

    @SuppressWarnings("unchecked")
    public T pop() {

        if (top == -1) {
            System.out.println("Stack is Empty");
            return null;
        }

        return (T) stack[top--];
    }

    @SuppressWarnings("unchecked")
    public T peek() {

        if (top == -1) {
            System.out.println("Stack is Empty");
            return null;
        }

        return (T) stack[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}

public class GenericStackDemo {

    public static void main(String[] args) {

        GenericStack<Integer> numbers = new GenericStack<>(5);

        numbers.push(10);
        numbers.push(20);
        numbers.push(30);

        System.out.println("Integer Stack:");

        System.out.println(numbers.pop());
        System.out.println(numbers.pop());

        GenericStack<String> names = new GenericStack<>(5);

        names.push("Rahul");
        names.push("Amit");
        names.push("Mayank");

        System.out.println("String Stack:");

        System.out.println(names.pop());
        System.out.println(names.peek());

        System.out.println("Is Empty: " + names.isEmpty());
    }
}

