package com.goitho.customerapp.app.permission;

import android.content.Context;
import android.os.Build;
import android.support.annotation.StringRes;

import com.goitho.customerapp.app.permission.util.Dlog;
import com.goitho.customerapp.app.permission.util.ObjectUtils;


/**
 * Created by TedPark on 16. 2. 17..
 */
public class PermissionHelper {


    private static PermissionInstance instance;


    public PermissionHelper(Context context) {
         instance = new PermissionInstance(context);
    }


    public PermissionHelper setPermissionListener(PermissionListener listener) {

        instance.listener = listener;

        return this;
    }


    public PermissionHelper setPermissions(String... permissions) {

        instance.permissions = permissions;
        return this;
    }

    public PermissionHelper setRationaleMessage(String rationaleMessage) {

        instance.rationaleMessage = rationaleMessage;
        return this;
    }


    public PermissionHelper setRationaleMessage(@StringRes int stringRes) {

        if (stringRes <= 0)
            throw new IllegalArgumentException("Invalid value for RationaleMessage");

        instance.rationaleMessage = instance.context.getString(stringRes);
        return this;
    }



    public PermissionHelper setDeniedMessage(String denyMessage) {

        instance.denyMessage = denyMessage;
        return this;
    }



    public PermissionHelper setDeniedMessage(@StringRes int stringRes) {

        if (stringRes <= 0)
            throw new IllegalArgumentException("Invalid value for DeniedMessage");

        instance.denyMessage = instance.context.getString(stringRes);
        return this;
    }


    public PermissionHelper setGotoSettingButton(boolean hasSettingBtn) {

        instance.hasSettingBtn = hasSettingBtn;
        return this;
    }







    public PermissionHelper setGotoSettingButtonText(String rationaleConfirmText) {

        instance.settingButtonText = rationaleConfirmText;
        return this;
    }


    public PermissionHelper setGotoSettingButtonText(@StringRes int stringRes) {

        if (stringRes <= 0)
            throw new IllegalArgumentException("Invalid value for setGotoSettingButtonText");


        instance.settingButtonText = instance.context.getString(stringRes);

        return this;
    }




    public PermissionHelper setRationaleConfirmText(String rationaleConfirmText) {

        instance.rationaleConfirmText = rationaleConfirmText;
        return this;
    }


    public PermissionHelper setRationaleConfirmText(@StringRes int stringRes) {

        if (stringRes <= 0)
            throw new IllegalArgumentException("Invalid value for RationaleConfirmText");


        instance.rationaleConfirmText = instance.context.getString(stringRes);

        return this;
    }



    public PermissionHelper setDeniedCloseButtonText(String deniedCloseButtonText) {

        instance.deniedCloseButtonText = deniedCloseButtonText;
        return this;
    }


    public PermissionHelper setDeniedCloseButtonText(@StringRes int stringRes) {

        if (stringRes <= 0)
            throw new IllegalArgumentException("Invalid value for DeniedCloseButtonText");


        instance.deniedCloseButtonText = instance.context.getString(stringRes);

        return this;
    }


    public void check() {
        if (instance.listener == null) {
            throw new NullPointerException("You must setPermissionListener() on PermissionHelper");
        } else if (ObjectUtils.isEmpty(instance.permissions)) {
            throw new NullPointerException("You must setPermissions() on PermissionHelper");
        }


        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            Dlog.d("preMarshmallow");
            instance.listener.onPermissionGranted();

        } else {
            Dlog.d("Marshmallow");
            instance.checkPermissions();
        }


    }


}
