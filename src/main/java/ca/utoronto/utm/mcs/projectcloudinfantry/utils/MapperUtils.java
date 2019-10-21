package ca.utoronto.utm.mcs.projectcloudinfantry.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapperUtils {

    public static String toEmptyIfNull(Object input){
        if(input == null){
            return "";
        }
        return input.toString();
    }

    public static List<String> objToListOfString(Object input){
        if(input == null){
            return new ArrayList<>();
        }
        String objString = input.toString();
        objString = objString.replace("[","").replace("]","");

        return Arrays.asList(objString.split(","));
    }

}
