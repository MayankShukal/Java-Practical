package practical7;


import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {
}

class MyTests {

    @Run
    public void testAddition() {
        System.out.println("Addition Test Passed");
    }

    @Run
    public void testSubtraction() {
        System.out.println("Subtraction Test Passed");
    }

    public void normalMethod() {
        System.out.println("This method should not run");
    }

    @Run
    public void testMultiplication() {
        System.out.println("Multiplication Test Passed");
    }
}

public class MiniTestRunner {

    public static void main(String[] args) {

        MyTests testObject = new MyTests();

        int count = 0;

        Method[] methods = testObject.getClass().getDeclaredMethods();

        System.out.println("Running Tests:\n");

        for (Method method : methods) {

            if (method.isAnnotationPresent(Run.class)
                    && method.getParameterCount() == 0) {

                try {
                    method.setAccessible(true);
                    method.invoke(testObject);
                    count++;

                } catch (Exception e) {
                    System.out.println("Test failed: " + method.getName());
                }
            }
        }

        System.out.println("\nTotal tests run: " + count);
    }
}
