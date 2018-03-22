package com.goitho.customerapp.widgets.scviewpager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.View;

/**
 * Created by Samuel on 2015-07-06.
 */
public class SCViewAnimationUtil {

    public static Point getDisplaySize(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);
        return size;
    }

}
