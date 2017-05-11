package com.vic.utils;

import android.util.Log;

/**
 * Created by yangxd on 2017/5/10.
 */

public class ConvertUtils {

    public static double parseDouble(String value)
    {
        double retValue = 0.0;
        try {
            retValue = Double.parseDouble(value);
        }
        catch (Exception ex)
        {
            Log.e("EX", "parseDouble exception");
            ex.printStackTrace();
        }
        return retValue;
    }


    public static int parseInt(String value)
    {
        int retValue = 0;
        try {
            retValue = Integer.parseInt(value);
        }
        catch (Exception ex)
        {
            Log.e("EX", "parseInt exception");
            ex.printStackTrace();
        }
        return retValue;
    }
}
