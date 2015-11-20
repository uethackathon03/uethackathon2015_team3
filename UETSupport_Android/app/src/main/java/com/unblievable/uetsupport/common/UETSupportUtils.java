package com.unblievable.uetsupport.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.unblievable.uetsupport.R;

/**
 * Created by DucAnhZ on 20/11/2015.
 */
public class UETSupportUtils {
    public static void showDialogServerProblem(Activity activity) {
        try {
            CommonUtils.showOkDialog(activity,
                    activity.getString(R.string.dialog_title_error),
                    activity.getString(R.string.dialog_content_server_problem),
                    null);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public static void showDialogNetworkProblem(Activity activity) {
        CommonUtils
                .showOkDialog(
                        activity,
                        activity.getString(R.string.dialog_title_common),
                        activity.getString(R.string.dialog_title_content_network_problem),
                        null);
    }

    public static boolean networkConnected(Activity activity) {
        if (CommonUtils.checkNetwork(activity)) {
            return true;
        } else {
            // Show msg
            showDialogNetworkProblem(activity);
            return false;
        }
    }

    public static void hideKeyboard(Activity activity, View view) {
        final InputMethodManager imm = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
