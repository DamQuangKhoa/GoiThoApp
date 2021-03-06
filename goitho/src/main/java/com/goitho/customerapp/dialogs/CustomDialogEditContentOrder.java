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
public class CustomDialogEditContentOrder extends DialogFragment {

    private OnSaveClickListener listener;

    public void setListener(OnSaveClickListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.setContentView(R.layout.dialog_edit_content_order);
        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        dialog.findViewById(R.id.txt_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        EditText etContent = (EditText) dialog.findViewById(R.id.et_content);
        dialog.findViewById(R.id.txt_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                listener.onSaveClick(etContent.getText().toString());
            }
        });
        return dialog;
    }

    public interface OnSaveClickListener {
        void onSaveClick(String content);
    }

}
