package com.yuwin.miniproject.Fragments;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyPassword {
    public VerifyPassword() {
    }

    boolean verifyPasswordField(CharSequence str, StringBuilder sb_pass, TextView passwordErrorText) {

        sb_pass.setLength(0);

        Pattern p = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        boolean containsSpecialCharacters = m.find();

        if (TextUtils.isEmpty(str)) {
            passwordErrorText.setVisibility(View.VISIBLE);
            sb_pass.append("\u2022 Password field required");
            sb_pass.append("\n");
        }

        if (str.length() < 6) {
            passwordErrorText.setVisibility(View.VISIBLE);
            sb_pass.append("\u2022 Password must be at least 6 characters long");
            sb_pass.append("\n");
        }

        if (str.length() > 18) {
            passwordErrorText.setVisibility(View.VISIBLE);
            sb_pass.append("\u2022 Password must be less than 18 characters");
            sb_pass.append("\n");
        }

        if (containsSpecialCharacters) {
            passwordErrorText.setVisibility(View.VISIBLE);
            sb_pass.append("\u2022 Password can only contain letters and numbers");
            sb_pass.append("\n");
        }

        if (TextUtils.isEmpty(sb_pass.toString())) {
            passwordErrorText.setText("");
            passwordErrorText.setVisibility(View.INVISIBLE);
            return true;
        }

        return false;
    }

    boolean verifyPasswordField(CharSequence str,CharSequence str2 ,StringBuilder sb_pass, TextView passwordErrorText) {
        boolean isValid = verifyPasswordField(str, sb_pass, passwordErrorText);

        if(str.toString().equals(str2.toString())) {
            return isValid;
        }else  {
            sb_pass.append("\u2022 Password mismatch");
            passwordErrorText.setVisibility(View.VISIBLE);
            return false;
        }
    }
}