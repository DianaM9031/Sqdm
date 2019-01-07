package com.example.dianamoron.sqdm.view.util;

import android.text.Html;
import android.text.Spanned;

public class StringUtil {

    public static Spanned fromHtml(String html){
            return Html.fromHtml(html);
    }
}
