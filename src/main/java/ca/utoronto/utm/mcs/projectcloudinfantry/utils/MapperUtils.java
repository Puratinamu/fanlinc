package ca.utoronto.utm.mcs.projectcloudinfantry.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    public static List<String> objToListOfString(Object input){
        if(input == null){
            return new ArrayList<>();
        }

        return (List<String>) input;
    }

    public static List<Map<String, Object>> objToListOfObj(Object input){
        if(input == null){
            return new ArrayList<>();
        }

        return (List<Map<String, Object>>) input;
    }

}
