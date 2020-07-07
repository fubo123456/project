package com.ps.sec.filter.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fb
 **/
public class StringToArray {
    public final static List<Integer> IntegerList(String ids){
        ArrayList<Integer> list = new ArrayList<>();
        if (ids != null && !ids.equals("")) {
            if (ids.contains(",")) {
                String[] split = ids.split(",");
                for (String str : split) {
                    Integer integer = Integer.valueOf(str);
                    list.add(integer);
                }
            }else{
                list.add(Integer.valueOf(ids));
            }
        }
        return list;
    }

    public final static List<String> StringList(String ids){
        ArrayList<String> list = new ArrayList<>();
        if (ids != null && !ids.equals("")) {
            if (ids.contains(",")) {
                String[] split = ids.split(",");
                for (String str : split) {
                    list.add(str);
                }
            }
        }else{
            list.add(ids);
        }
        return list;
    }
}