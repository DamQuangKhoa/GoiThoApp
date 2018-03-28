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
public class CustomDialogLibraryCapture extends DialogFragment {

    private OnOpenCameraListener onOpenCameraListener;
    private OnOpenGalleryListener onOpenGalleryListener;

    public void setListener(OnOpenCameraListener onOpenCameraListener,
                            OnOpenGalleryListener onOpenGalleryListener) {
        this.onOpenCameraListener = onOpenCameraListener;
        this.onOpenGalleryListener = onOpenGalleryListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.dialog_library_capture);
        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        dialog.findViewById(R.id.txt_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                onOpenCameraListener.onOpenCamera();
            }
        });
        dialog.findViewById(R.id.txt_choose_album).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                onOpenGalleryListener.onOpenGallery();
            }
        });
        dialog.findViewById(R.id.txt_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();

            }
        });

        return dialog;
    }

    public interface OnOpenCameraListener {
        void onOpenCamera();
    }

    public interface OnOpenGalleryListener {
        void onOpenGallery();
    }
}
