package com.example.retrofit_upload_img;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.retrofit_upload_img.api.ApiService;
import com.example.retrofit_upload_img.api.Const;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//20:30
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int MY_REQUEST_CODE = 4;
    private EditText ed_username,ed_password;
    private ImageView imgFromGalery, imgfromApi;
    private Button btnSelectImg,btnUploadImg;
    private TextView tvUserName,tvPassword;

    private Uri mUri;

    private ProgressDialog progressDialog;

    //xu ly chon 1 anh tu galary
    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult()
            , new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        if(data ==null){
                            return;
                        }
                        Uri uri = data.getData();
                        //nhan biet chon anh duoc hay chua
                        mUri = uri;
                        try{
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                            imgfromApi.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        onCLickButton();

    }


    private void initView() {
        ed_username = findViewById(R.id.ed_username);
        ed_password = findViewById(R.id.ed_password);
        imgFromGalery = findViewById(R.id.img_from_galary);
        imgfromApi = findViewById(R.id.img_response);
        tvPassword = findViewById(R.id.tv_password);
        tvUserName = findViewById(R.id.tv_userName);
        btnSelectImg = findViewById(R.id.bt_select_img);
        btnUploadImg = findViewById(R.id.bt_upload_img);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait .....");
    }
    private void onCLickButton() {
       btnSelectImg.setOnClickListener(this);
       btnUploadImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==btnSelectImg){
            onClickRequestPermission();
        }
        if(v==btnUploadImg){
            if(mUri!=null){
                callApiRegisterAccount();
            }
        }
    }



    //check version va cho phep quyen
    private void onClickRequestPermission() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            openGallery();
            return;
        }
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            openGallery();
        }else{
            String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permission, MY_REQUEST_CODE);
        }
    }
    //lang nghe nguoi dung cho phep hay tu choi permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == MY_REQUEST_CODE){
            //khi nguoi dung cho phep
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openGallery();
            }
        }

    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch((Intent.createChooser(intent, "Select Picture")));
    }

    private void callApiRegisterAccount() {
        progressDialog.show();

        String  strUserName = ed_username.getText().toString().trim();
        String strPassword = ed_password.getText().toString().trim();

        RequestBody requestBodyUserName = RequestBody.create(MediaType.parse("multipartt/form-data"), strUserName);
        RequestBody requestBodyPassword = RequestBody.create(MediaType.parse("multipartt/form-data"), strPassword);

        //Tao doi tuong MultipathBody
        String strReadPath = RealPathUtil.getRealPath(this, mUri);
        File file = new File(strReadPath);

        RequestBody requestBodyAvt = RequestBody.create(MediaType.parse("multipartt/form-data"), file);
        MultipartBody.Part multiparttBodyAvt = MultipartBody.Part.createFormData(Const.KEY_AVT, file.getName(), requestBodyAvt);

        ApiService.apiService.registerAccount(requestBodyUserName,requestBodyPassword,multiparttBodyAvt).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                progressDialog.dismiss();
                User user = response.body();

                if(user!=null){
                    tvUserName.setText(user.getUsername());
                    tvPassword.setText(user.getPassword());
                    Glide.with(MainActivity.this).load(user.getAvatar())
                            .into(imgfromApi);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //Toast.....
            }
        });
    }
}