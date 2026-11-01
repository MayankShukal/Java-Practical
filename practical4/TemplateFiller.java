package practical4;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TemplateFiller {

    public static void fill(String template, String[] names, String[] values) {

        Pattern pattern = Pattern.compile("\\{(\\w+)\\}");

        Matcher matcher = pattern.matcher(template);

        StringBuilder result = new StringBuilder();

        int last = 0;

        while (matcher.find()) {

            result.append(template.substring(last, matcher.start()));

            String key = matcher.group(1);

            String replacement = "[?]";

            for (int i = 0; i < names.length; i++) {

                if (names[i].equals(key)) {
                    replacement = values[i];
                    break;
                }
            }

            result.append(replacement);

            last = matcher.end();
        }

        result.append(template.substring(last));

        System.out.println(result);
    }
}
