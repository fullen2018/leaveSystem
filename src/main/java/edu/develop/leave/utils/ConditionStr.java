package edu.develop.leave.utils;

/**
 * @version 1.0
 * @anthor zsl on 2019/3/31
 * @since jdk8
 *
 * 搜索条件模板
 */
public class ConditionStr {

    public static String sqlTemplate(String[] strs){
        if(strs.length == 0){
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(strs[0]).append(" like  ?");
        if(strs.length >= 2){
            for(int i = 1; i < strs.length; i++){
                stringBuilder.append(" and ");
                stringBuilder.append(strs[i]).append(" like ?");

            }
        }
        return stringBuilder.toString();
    }

}
