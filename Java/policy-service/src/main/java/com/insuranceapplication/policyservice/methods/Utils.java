package com.insuranceapplication.policyservice.methods;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insuranceapplication.policyservice.models.InsuredObjects;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Utils {
    public static Object mapToObject(LinkedHashMap map,Class clazz){
        ObjectMapper mapper = new ObjectMapper();
        Object o = mapper.convertValue(map,clazz);
        return o;
    }
    public static List mapToList(List<LinkedHashMap> mapList,Class clazz){

        List<Object> list = new ArrayList<>();
        for (LinkedHashMap map : mapList){
            list.add(mapToObject(map,clazz));
        }
        return list;
    }

}
