package com.goitho.customerapp.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.goitho.customerapp.R;


/**
 * Created by yenyen on 6/20/2017.
 */
public class CustomDialogLogOut extends DialogFragment {

    private OnYesClickListener listener;

    public void setListener(OnYesClickListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.setContentView(R.layout.dialog_logout);
        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        dialog.findViewById(R.id.txt_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        dialog.findViewById(R.id.txt_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                listener.onYesClick();
            }
        });
        return dialog;
    }

    public interface OnYesClickListener {
        void onYesClick();
    }

}
