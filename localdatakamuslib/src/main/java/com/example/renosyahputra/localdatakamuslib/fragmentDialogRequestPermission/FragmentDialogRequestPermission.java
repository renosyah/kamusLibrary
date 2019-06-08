package com.example.renosyahputra.localdatakamuslib.fragmentDialogRequestPermission;

import android.Manifest;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.renosyahputra.localdatakamuslib.R;
import com.example.renosyahputra.localdatakamuslib.tambahKata.TambahKata;

public class FragmentDialogRequestPermission extends DialogFragment {

    private Context context;
    private TambahKata.OnRequestStoragePermissionListener onRequestPermissionListener;


    public void setOnRequestPermissionListener(TambahKata.OnRequestStoragePermissionListener onRequestPermissionListener) {
        this.onRequestPermissionListener = onRequestPermissionListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.empty_layout, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        context = getActivity();

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions( new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
        }


        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        this.onRequestPermissionListener.onPermissionResult((ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_DENIED
                || ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_DENIED));

        getDialog().dismiss();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        this.onRequestPermissionListener.onFinishRequestPermission();
        super.onDismiss(dialog);
    }

}
