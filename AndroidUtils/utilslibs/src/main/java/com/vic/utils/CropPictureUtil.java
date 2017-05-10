package com.vic.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

/**
 * 裁剪图片
 * Created by liusong on 17/3/1.
 */
public class CropPictureUtil {
    public static final int REQUEST_CROP_PICTURE = 3;

    //裁剪大图片
    public static void startBigPhotoCrop(Activity activity, Uri uri, int width, int height) {
        Intent intent = getCropBigPhotoIntent(uri, width, height);
        activity.startActivityForResult(intent, REQUEST_CROP_PICTURE);
    }

    //裁剪大图片
    public static void startBigPhotoCrop(Fragment fragment, Uri uri, int width, int height) {
        Intent intent = getCropBigPhotoIntent(uri, width, height);
        fragment.startActivityForResult(intent, REQUEST_CROP_PICTURE);
    }

    @NonNull
    private static Intent getCropBigPhotoIntent(Uri uri, int width, int height) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", width);
        intent.putExtra("outputY", height);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        return intent;
    }

    //裁剪小图片
    public static void startSmallPhotoCrop(Activity activity, Uri uri, int width, int height) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", width);
        intent.putExtra("outputY", height);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        activity.startActivityForResult(intent, REQUEST_CROP_PICTURE);
    }
}
