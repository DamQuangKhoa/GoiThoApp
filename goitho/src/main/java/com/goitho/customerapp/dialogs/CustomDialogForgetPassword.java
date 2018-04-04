package com.goitho.customerapp.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.goitho.customerapp.R;


/**
 * Created by yenyen on 6/20/2017.
 */
public class CustomDialogForgetPassword extends DialogFragment {

    private OnClickListener listener;
    public void setListener(OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.setContentView(R.layout.dialog_forget_password);
        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        EditText etUsername = dialog.findViewById(R.id.et_username);
        EditText etPhone = dialog.findViewById(R.id.et_phone);
        dialog.findViewById(R.id.btn_get_pass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                listener.onClick(etUsername.getText().toString(), etPhone.getText().toString());
            }
        });

        return dialog;
    }

    public interface OnClickListener {
        void onClick(String username, String phone);
    }
}
