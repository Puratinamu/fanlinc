package ca.utoronto.utm.mcs.projectcloudinfantry.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

}
