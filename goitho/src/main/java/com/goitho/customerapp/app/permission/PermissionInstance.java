package com.goitho.customerapp.app.permission;

import android.content.Context;
import android.content.Intent;

import com.goitho.customerapp.R;
import com.goitho.customerapp.app.permission.busevent.BusProvider;
import com.goitho.customerapp.app.permission.busevent.PermissionEvent;
import com.goitho.customerapp.app.permission.util.Dlog;
import com.squareup.otto.Subscribe;

public class PermissionInstance {

    public PermissionListener listener;
    public String[] permissions;
    public String rationaleMessage;
    public String denyMessage;
    public String settingButtonText;
    public boolean hasSettingBtn = true;

    public String deniedCloseButtonText;
    public String rationaleConfirmText;
    Context context;


    public PermissionInstance(Context context) {

        this.context = context;

        BusProvider.getInstance().register(this);

        deniedCloseButtonText = context.getString(R.string.permission_close);
        rationaleConfirmText = context.getString(R.string.permission_confirm);
    }


    public void checkPermissions() {
        Dlog.d("");

        Intent intent = new Intent(context, PermissionActivity.class);
        intent.putExtra(PermissionActivity.EXTRA_PERMISSIONS, permissions);

        intent.putExtra(PermissionActivity.EXTRA_RATIONALE_MESSAGE, rationaleMessage);
        intent.putExtra(PermissionActivity.EXTRA_DENY_MESSAGE, denyMessage);
        intent.putExtra(PermissionActivity.EXTRA_PACKAGE_NAME, context.getPackageName());
        intent.putExtra(PermissionActivity.EXTRA_SETTING_BUTTON, hasSettingBtn);
        intent.putExtra(PermissionActivity.EXTRA_DENIED_DIALOG_CLOSE_TEXT, deniedCloseButtonText);
        intent.putExtra(PermissionActivity.EXTRA_RATIONALE_CONFIRM_TEXT, rationaleConfirmText);
        intent.putExtra(PermissionActivity.EXTRA_SETTING_BUTTON_TEXT, settingButtonText);


        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);


    }


    @Subscribe
    public void onPermissionResult(PermissionEvent event) {
        Dlog.d("");
        if (event.hasPermission()) {
            listener.onPermissionGranted();
        } else {
            listener.onPermissionDenied(event.getDeniedPermissions());
        }
        BusProvider.getInstance().unregister(this);


    }

}
