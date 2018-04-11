package com.goitho.customerapp.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
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
    private boolean isClicked = false;

    public void setListener(OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.dialog_forget_password);
        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        EditText etUsername = dialog.findViewById(R.id.et_username);
        EditText etPhone = dialog.findViewById(R.id.et_phone);
        EditText etNewPassword = dialog.findViewById(R.id.et_new_password);
        dialog.findViewById(R.id.btn_get_pass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                listener.onClick(etUsername.getText().toString(), etPhone.getText().toString(),
                        etNewPassword.getText().toString());
            }
        });
        dialog.findViewById(R.id.img_see_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isClicked) {
                    isClicked = true;
                } else {
                    isClicked = false;
                }
                etNewPassword.setInputType(
                        !isClicked ? InputType.TYPE_TEXT_VARIATION_PASSWORD : InputType.TYPE_CLASS_TEXT);
                etNewPassword.setTransformationMethod(
                        !isClicked ? PasswordTransformationMethod.getInstance() : null);
            }
        });

        return dialog;
    }

    public interface OnClickListener {
        void onClick(String username, String phone, String newPassword);
    }
}
