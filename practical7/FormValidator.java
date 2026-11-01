package practical7;
import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

class SignupForm {

    @NotBlank
    @MaxLength(20)
    private String username;

    @NotBlank
    @MaxLength(30)
    private String email;

    @NotBlank
    @MaxLength(10)
    private String password;

    SignupForm(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

public class FormValidator {

    public static List<String> validate(Object object)
            throws IllegalAccessException {

        List<String> errors = new ArrayList<>();

        Class<?> cls = object.getClass();

        Field[] fields = cls.getDeclaredFields();

        for (Field field : fields) {

            field.setAccessible(true);

            Object value = field.get(object);

            if (field.isAnnotationPresent(NotBlank.class)) {

                if (value == null || value.toString().trim().isEmpty()) {
                    errors.add(field.getName() + " cannot be blank");
                }
            }

            if (field.isAnnotationPresent(MaxLength.class)) {

                MaxLength annotation =
                        field.getAnnotation(MaxLength.class);

                int max = annotation.value();

                if (value != null && value.toString().length() > max) {
                    errors.add(field.getName()
                            + " must not exceed " + max + " characters");
                }
            }
        }

        return errors;
    }

    public static void main(String[] args)
            throws IllegalAccessException {

        SignupForm form = new SignupForm(
                "",
                "mayank@example.com",
                "verylongpassword123"
        );

        List<String> errors = validate(form);

        if (errors.isEmpty()) {
            System.out.println("Form is valid!");
        } else {
            System.out.println("Validation Errors:");

            for (String error : errors) {
                System.out.println(error);
            }
        }
    }
}
