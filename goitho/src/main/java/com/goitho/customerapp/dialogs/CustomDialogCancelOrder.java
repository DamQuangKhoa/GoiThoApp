package com.goitho.customerapp.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;

import com.goitho.customerapp.R;


/**
 * Created by yenyen on 6/20/2017.
 */
public class CustomDialogCancelOrder extends DialogFragment {

    private OnCancelClickListener listener;

    private OnNoCheckListener onNoCheckListener;

    public void setListener(OnCancelClickListener listener, OnNoCheckListener onNoCheckListener) {
        this.listener = listener;
        this.onNoCheckListener = onNoCheckListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(),android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_cancel_order);

        CheckBox cbResason = (CheckBox) dialog.findViewById(R.id.cb_reason);
        CheckBox cbResason2 = (CheckBox) dialog.findViewById(R.id.cb_reason2);
        CheckBox cbResason3 = (CheckBox) dialog.findViewById(R.id.cb_reason3);

        dialog.findViewById(R.id.layout_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!cbResason.isChecked() && !cbResason2.isChecked() && !cbResason3.isChecked()) {
                    onNoCheckListener.onNoCheck();
                } else {
                    dismiss();
                    listener.onCancelClick();
                }

            }
        });

        return dialog;
    }

    public interface OnCancelClickListener {
        void onCancelClick();
    }
    public interface OnNoCheckListener {
        void onNoCheck();
    }

}
