package com.bridgelabz.recycleviewdemo.utility;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

/**
 * Created by bridgelabz5 on 23/5/16.
 */
public class ImageUtility {

    public static Bitmap stringToBitmap(String s){
        byte[] imageAsByte= Base64.decode(s.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsByte,0,imageAsByte.length);
    }
}
