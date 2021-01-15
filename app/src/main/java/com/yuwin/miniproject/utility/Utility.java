package com.yuwin.miniproject.utility;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.yuwin.miniproject.DB.Entity.CardEntity;
import com.yuwin.miniproject.R;

public class Utility {

    public static String getEntryName(Context context, int id) {
        if(context != null) {
            return context.getResources().getResourceEntryName(id);
        }else {
            return "N/A";
        }
    }

    public static String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public static int getResourceId(Context context, String name) {
        if(context != null) {
            return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        }else {
            return -1;
        }
    }

    public static void setImageIcon(ImageView cardView, CardEntity cardEntity) {
        char cardNumber = cardEntity.cardNumber.charAt(0);
        if(cardNumber == '4') {
            cardView.setImageResource(R.drawable.ic_visa);
        }else if(cardNumber == '5') {
            cardView.setImageResource(R.drawable.ic_mastercard);
        }
    }

}
