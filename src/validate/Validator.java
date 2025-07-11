package validate;

public class Validator {
    public static boolean isEmpty(String data) {
        if (data != null && !data.trim().isEmpty()) {
            return false;
        }
        return true;
    }
}
