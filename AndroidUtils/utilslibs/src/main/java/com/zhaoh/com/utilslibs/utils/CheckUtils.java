package com.zhaoh.com.utilslibs.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 赵桓 on 2017/5/3.
 */

public class CheckUtils {

    /**
     * 校验工具类
     *
     *
     * @param type 校验类型
     *             0.   密码：只能包含字母、数字和下划线，长度在6-20之间
     *             1.   用户名：中文、英文但不包括数字、下划线等符号
     *             2.   URL
     *             3.   Unicode编码中的汉字范围
     *             4.   电话号码
     * @param name 校验内容
     * @return
     */
    public static boolean checkPwd(int type, String name) {

        String regEx = "";

        switch (type) {
            case 0:
                regEx = "^[A-Za-z0-9_]{6,20}$";
                break;
            case 1:
                regEx = "^[\\u4E00-\\u9FA5A-Za-z]+$";
                break;
            case 2:
                regEx = "^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$";
                break;
            case 3:
                regEx = "^[\\u2E80-\\u9FFF]+$";
                break;
            case 4:
                regEx = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
                break;
            default:
                break;
        }

        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(name);
        return m.matches();
    }
}
