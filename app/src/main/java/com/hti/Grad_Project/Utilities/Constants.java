package com.hti.Grad_Project.Utilities;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import androidx.core.app.ActivityCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hti.Grad_Project.Model.UserModel;


import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;


public class Constants {

    private static FirebaseFirestore db;
    private static FirebaseDatabase dbReal;
    private static DatabaseReference databaseReference;
    private static FirebaseAuth mAut;
    private static SharedPreferences sharedPreferences;

    //Text
    public static final String userId = Objects.requireNonNull(Objects.requireNonNull(Constants.GetAuth()).getCurrentUser()).getUid();

    public static FirebaseAuth GetAuth() {
        if (mAut == null)
            mAut = FirebaseAuth.getInstance();

        return mAut;
    }

    public static FirebaseFirestore GetFireStoneDb() {

        if (db == null)
            db = FirebaseFirestore.getInstance();

        return db;
    }


    public static void saveUserID(Activity activity, String userId) {
        sharedPreferences = activity.getSharedPreferences("UserId", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("UserId", userId);
        editor.apply();
    }

    public static String getUid(Activity activity) {
        sharedPreferences = activity.getSharedPreferences("UserId", Context.MODE_PRIVATE);

        return sharedPreferences.getString("UserId", "empty");
    }

    public static DatabaseReference GetRef() {

        if (databaseReference == null || dbReal == null)
            dbReal = FirebaseDatabase.getInstance();
        databaseReference = dbReal.getReference();


        return databaseReference;
    }


    public static void makeIntent(Context context1, Class<?> context2) {
        context1.startActivity(new Intent(context1, context2));
    }

    public static boolean checkInternetConnection(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static String getPDFPath(Uri uri,Context context){

        final String id = DocumentsContract.getDocumentId(uri);
        final Uri contentUri = ContentUris.withAppendedId(
                Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver().query(contentUri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}