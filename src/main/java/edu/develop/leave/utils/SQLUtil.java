package edu.develop.leave.utils;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * SQL工具类
 *
 * @author Ming
 */
public class SQLUtil {

    /**
     * 格式化字段
     *
     * @param parameters 字段
     * @return 格式化结果
     */
    public static String formatParameters(String[] parameters) {
        if (parameters == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(parameters[0]);
        for (int i = 1, length = parameters.length; i < length; i++) {
            stringBuilder.append("," + parameters[i]);
        }

        return stringBuilder.toString();
    }

    /**
     * 条件SQL参数填充
     *
     * @param condition 条件
     * @param values    参数
     * @return 条件SQL
     */
    @SuppressWarnings("deprecation")
    public static String fillCondition(String condition, Object... values) {
        if (condition == null) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder(condition);

        for (int i = 0, length = values.length; i < length; i++) {
            int index = stringBuilder.indexOf("?");
            Object value = values[i];
            String param;
            if (value instanceof String) {
                param = "\'%" + ((String) value).replaceAll("'", "''") + "%\'";
            } else if (value instanceof Date) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                param = "\'%" + simpleDateFormat.format((Date) value) + "%\'";
            } else if (value instanceof java.sql.Date) {
                param = "\'%" + ((java.sql.Date) value).toLocaleString() + "%\'";
            } else {
                if (value == null) {
                    param = "\'\'";
                } else {
                    param = "\'%"+value.toString()+"%\'";
                }

            }
            stringBuilder.replace(index, index + 1, param);
        }

        return stringBuilder.toString();
    }

    public static Integer getOffset(Integer curPage, Integer limit) {
        if (curPage == null || limit == null) {
            return null;
        }
        return (curPage - 1) * limit;
    }

    public static String stringToStringgroup(String targetString) {
        if (targetString == null) {
            return null;
        }
        StringBuffer endString = new StringBuffer("%");
        String string = targetString.replaceAll(" ", "%");
        endString.append(string + "%");
        return endString.toString();
    }


//	public static void main(String[] args) throws ParseException {
//
//		Date date=new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
//		date=sdf.parse("1995-02-04");
//		System.out.println(date.toString());
//
//
//		String[] parameters = new String[] {"username", "password"};
//		System.out.println(formatParameters(parameters));
//
//
//
//		String condition = "username = ? and sex = ? and create_date = ? and update_date = ?";
//		Object[] values = new Object[] {"Ming", 1, new Date(), new java.sql.Date(System.currentTimeMillis())};
//		System.out.println(fillCondition(condition, values));
//	}
}
