package com.goitho.customerapp.app.bus;

/**
 * Created by admin on 5/18/17.
 */

public final class BusEvent {

    public static abstract class BaseEvent {
        protected final String TAG = this.getClass().getCanonicalName();
    }

    public static class NavigationShowHideEvent extends BaseEvent {
        public NavigationShowHideEvent() {

        }
    }

    public static class GoToOtherPageOnDashboard extends BaseEvent {
        private String page;

        public GoToOtherPageOnDashboard(String page) {
            this.page = page;
        }

        public String getPage() {
            return page;
        }
    }
}