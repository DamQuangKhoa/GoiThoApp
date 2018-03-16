package com.goitho.customerapp.app.permission.util;

import android.support.compat.BuildConfig;
import android.util.Log;


/**
 * The type Dlog.
 */
public class Dlog {

    /**
     * The Tag.
     */
    static final String TAG = "tedpark";

    /**
     * Log Level Error  @param message the message
     */
    public static void e(String message) {
    if (BuildConfig.DEBUG) Log.e(TAG, buildLogMsg(message));
  }

    /**
     * Log Level Warning  @param message the message
     */
    public static void w(String message) {
    if (BuildConfig.DEBUG) Log.w(TAG, buildLogMsg(message));
  }

    /**
     * Log Level Information  @param message the message
     */
    public static void i(String message) {
    if (BuildConfig.DEBUG) Log.i(TAG, buildLogMsg(message));
  }

    /**
     * Log Level Debug  @param message the message
     */
    public static void d(String message) {
    if (BuildConfig.DEBUG) Log.d(TAG, buildLogMsg(message));
  }

    /**
     * Log Level Verbose  @param message the message
     */
    public static void v(String message) {
    if (BuildConfig.DEBUG) Log.v(TAG, buildLogMsg(message));
  }

    /**
     * Build log msg string.
     *
     * @param message the message
     * @return the string
     */
    public static String buildLogMsg(String message) {

		StackTraceElement ste = Thread.currentThread().getStackTrace()[4];

		StringBuilder sb = new StringBuilder();

		sb.append("[");
		sb.append(ste.getFileName().replace(".java", ""));
		sb.append("::");
		sb.append(ste.getMethodName());
		sb.append("]");
		sb.append(message);

		return sb.toString();

	}

}