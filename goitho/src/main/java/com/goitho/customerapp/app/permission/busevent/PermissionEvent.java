package com.goitho.customerapp.app.permission.busevent;

import java.util.ArrayList;

/**
 * Created by TedPark on 16. 2. 17..
 */
public class PermissionEvent {

    public boolean permission;
    public ArrayList<String> deniedPermissions;


    public PermissionEvent(boolean permission, ArrayList<String> deniedPermissions
    ) {
        this.permission = permission;
        this.deniedPermissions = deniedPermissions;
    }



    public boolean hasPermission() {
        return permission;
    }


    public ArrayList<String> getDeniedPermissions() {
        return deniedPermissions;
    }
}
