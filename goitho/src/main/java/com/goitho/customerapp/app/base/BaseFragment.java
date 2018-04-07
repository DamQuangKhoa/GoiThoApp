package com.goitho.customerapp.app.base;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.inputmethod.InputMethodManager;

import static com.goitho.customerapp.app.di.Precondition.checkNotNull;


/**
 * Created by uyminhduc on 12/16/16.
 */

public class BaseFragment extends Fragment {
    public void showProgressDialog() {
        if (isActive() && getActivity() != null)
            ((BaseActivity) getActivity()).showProgressDialog();
    }

    public void hideProgressDialog() {
        if (isActive() && getActivity() != null)
            ((BaseActivity) getActivity()).hideProgressDialog();
    }

    public boolean isActive() {
        return isAdded();
    }

    public void hideSoftInput() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    public void showSoftInput() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(getView(), InputMethodManager.SHOW_FORCED);
    }

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}.
     */
    public void addFragment(@NonNull Fragment fragment, @NonNull String tag, int frameId) {
        checkNotNull(fragment);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(frameId, fragment, tag);
        transaction.addToBackStack(tag);
        transaction.commit();
    }
    public void replaceFragment(@NonNull Fragment fragment, @NonNull String tag, int frameId) {
        checkNotNull(fragment);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(frameId, fragment, tag);
        transaction.commit();
    }

    public void popFragment() {
        getChildFragmentManager().popBackStack();
    }


}