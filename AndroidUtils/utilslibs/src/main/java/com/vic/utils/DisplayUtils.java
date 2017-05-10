package com.vic.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.view.WindowManager;

import java.lang.reflect.Method;


/**
 * Created by Venus Software on 2017/5/5.
 */

public class DisplayUtils {
    /**
     *  dp转px
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dpVal,
                context.getResources().getDisplayMetrics());
    }

    /**
     *  sp转px
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                spVal,
                context.getResources().getDisplayMetrics());
    }

    /**
     *  px转dp
     */
    public static int px2dp(Context context, float pxVal) {
        return (int) (pxVal / context.getResources().getDisplayMetrics().density);
    }

    /**
     *  px转sp
     */
    public static int px2sp(Context context, float pxVal) {
        return (int) (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }

    private static int getDpi(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        int height = 0;
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            height = dm.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return height;
    }

    private static int[] getScreenWH(Context poCotext) {
        WindowManager wm = (WindowManager) poCotext
                .getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        /*
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        */
        int width = point.x;
        int height = point.y;

        return new int[] { width, height };
    }

    /**
     *  获得虚拟按键的高度。注意这个主要是为了测试。
     */
    public static int getVirtualBtnHeight(Context poCotext) {
        int location[] = getScreenWH(poCotext);
        int realHeiht = getDpi((Activity) poCotext);
        int virvalHeight = realHeiht - location[1];
        return virvalHeight;
    }

    /**
     *  给一个rootView，实现背景变暗的效果
     */
    public static void applyDim(@NonNull ViewGroup parent, float dimAmount){
        Drawable dim = new ColorDrawable(Color.BLACK);
        dim.setBounds(0, 0, parent.getWidth(), parent.getHeight());
        dim.setAlpha((int) (255 * dimAmount));

        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.add(dim);
    }

    /**
     *  给一个rootView，去除背景变暗的效果
     */
    public static void clearDim(@NonNull ViewGroup parent) {
        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.clear();
    }
}
