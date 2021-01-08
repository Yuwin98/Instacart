package com.yuwin.miniproject.utility;

import android.content.Context;

public class Utility {

    public static String getEntryName(Context context, int id) {
        if(context != null) {
            return context.getResources().getResourceEntryName(id);
        }else {
            return "N/A";
        }
    }

    public static int getResourceId(Context context, String name) {
        if(context != null) {
            return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        }else {
            return -1;
        }
    }



}
