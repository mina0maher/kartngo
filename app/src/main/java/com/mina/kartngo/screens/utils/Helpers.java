package com.mina.kartngo.screens.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

public class Helpers {
    public static Bitmap decodeBase64ToBitmap(String base64Image) {

        if (base64Image == null || base64Image.trim().isEmpty()) {
            return null;
        }

        try {
            base64Image = base64Image.replaceFirst("^data:image/[^;]+;base64,", ""); // لو فيها prefix
            byte[] decodedBytes = Base64.decode(base64Image, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
