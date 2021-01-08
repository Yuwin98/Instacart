package com.yuwin.miniproject.Fragments;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyEmail {
    public VerifyEmail() {
    }

    boolean verifyEmailField(CharSequence str, StringBuilder sb_email, TextView emailErrorText) {

        sb_email.setLength(0);

        Pattern p = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
        Matcher m = p.matcher(str);
        boolean isAValidEmail = m.find();

        if (TextUtils.isEmpty(str)) {
            emailErrorText.setVisibility(View.VISIBLE);
            sb_email.append("\u2022 Email field is required");
            sb_email.append("\n");
        }

        if (!isAValidEmail) {
            emailErrorText.setVisibility(View.VISIBLE);
            sb_email.append("\u2022 Email entered is not valid");
            sb_email.append("\n");
        }

        if (str.length() > 255) {
            emailErrorText.setVisibility(View.VISIBLE);
            sb_email.append("\u2022 Email must be less than 255 characters");
            sb_email.append("\n");
        }


        if (TextUtils.isEmpty(sb_email.toString())) {
            emailErrorText.setText("");
            emailErrorText.setVisibility(View.INVISIBLE);
            return true;
        }

        return false;
    }
}