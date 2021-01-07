package com.example.psm.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * 根据传入的月份得到相应的表名后缀
 *
 */
public class CustomizeUtil {

    /**
     * 根据月份进行返回表名后缀 适用于3个月一张表
     * @param month 月份
     * @return 返回表名后缀
     */
    public static String ThreeMonth(int month){
        String monthResult = "";
        switch (month) {
            case 1:
            case 2:
            case 3:
                monthResult = "01";
                break;
            case 4:
            case 5:
            case 6:
                monthResult = "04";
                break;
            case 7:
            case 8:
            case 9:
                monthResult = "07";
                break;
            case 10:
            case 11:
            case 12:
                monthResult = "10";
                break;
        }
        return monthResult;
    }

    /**
     * 根据月份进行返回表名后缀 适用于10天一张表
     * @param month 月份
     * @return 返回表名后缀
     */
    public static String TenDaysMonth(int month){
        String monthResult;
        if (month < 11) {
            monthResult = "01";
        } else if (month > 20) {
            monthResult = "21";
        } else {
            monthResult = "11";
        }
        return monthResult;
    }


}
