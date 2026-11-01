package practical7;


import java.lang.annotation.*;
import java.lang.reflect.Field;
// import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column {
    String name();
}

class Student {

    @Column(name = "student_name")
    private String name;

    @Column(name = "student_age")
    private int age;

    @Column(name = "student_city")
    private String city;

    public String toString() {
        return "Name: " + name +
                ", Age: " + age +
                ", City: " + city;
    }
}

public class ColumnMapper {

    public static void populate(
            Object object, String[] headers, String[] data)
            throws Exception {

        Class<?> cls = object.getClass();

        Field[] fields = cls.getDeclaredFields();

        for (Field field : fields) {

            if (field.isAnnotationPresent(Column.class)) {

                Column column = field.getAnnotation(Column.class);

                String columnName = column.name();

                int index = -1;

                for (int i = 0; i < headers.length; i++) {
                    if (headers[i].equals(columnName)) {
                        index = i;
                        break;
                    }
                }

                if (index == -1) {
                    System.out.println(
                            "Warning: Missing column " + columnName);
                    continue;
                }

                if (index >= data.length) {
                    System.out.println(
                            "Warning: Missing data for " + columnName);
                    continue;
                }

                field.setAccessible(true);

                String value = data[index];

                if (field.getType() == String.class) {
                    field.set(object, value);

                } else if (field.getType() == int.class) {
                    field.setInt(object, Integer.parseInt(value));

                } else if (field.getType() == double.class) {
                    field.setDouble(object, Double.parseDouble(value));

                } else if (field.getType() == boolean.class) {
                    field.setBoolean(object, Boolean.parseBoolean(value));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        Student student = new Student();

        String[] headers = {
            "student_name",
            "student_age",
            "student_city"
        };

        String[] data = {
            "Mayank",
            "19",
            "Ahmedabad"
        };

        populate(student, headers, data);

        System.out.println("\nStudent Details:");
        System.out.println(student);
    }
}
