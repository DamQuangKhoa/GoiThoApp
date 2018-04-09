package com.goitho.customerapp.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.goitho.customerapp.R;
import com.goitho.customerapp.app.CoreApplication;


/**
 * Created by yenyen on 6/20/2017.
 */
public class CustomNotiDialog extends DialogFragment {

    private String content;
    private String closeButtonText;
    private View.OnClickListener listener;

    public void setCloseButtonText(String closeButtonText) {
        this.closeButtonText = closeButtonText;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CustomNotiDialog(){
        closeButtonText = CoreApplication.getInstance().getString(R.string.text_re_enter);
    }

    public void setListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.dialog_noti_register);
        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        TextView txtContent = dialog.findViewById(R.id.txt_content);
        txtContent.setText(content);
        TextView btnClose = dialog.findViewById(R.id.txt_close);
        btnClose.setOnClickListener(listener == null ? new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        } : listener);
        btnClose.setText(closeButtonText);

        return dialog;
    }

}
