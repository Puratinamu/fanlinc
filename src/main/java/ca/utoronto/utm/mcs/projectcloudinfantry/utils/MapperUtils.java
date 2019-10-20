package ca.utoronto.utm.mcs.projectcloudinfantry.utils;

public class MapperUtils {

    public static String toEmptyIfNull(Object input) {
        if(input == null) {
            return "";
        }
        return input.toString();
    }

    public static Long toLong(Object input) {
        return Long.valueOf((Integer) input);
    }

}
