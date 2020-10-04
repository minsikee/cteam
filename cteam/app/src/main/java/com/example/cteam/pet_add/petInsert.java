package com.example.cteam.pet_add;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.AutoText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.cteam.ATask.Listinsert;
import com.example.cteam.Common.CommonMethod;
import com.example.cteam.Login;
import com.example.cteam.PetAdd;
import com.example.cteam.PetSelect;
import com.example.cteam.R;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;

import static com.example.cteam.Login.loginDTO;

public class petInsert extends AppCompatActivity {

    EditText petName, petAge,petWeight,petGender;
    Button goMain;

    String petname = "", petage = "",petweight = "",petgender ="";
    ImageView petPhoto;
    String id;

    Button btnLoad,btnCancle,btn_add;

    public String imageRealPathA, imageDbPathA;

    final int CAMERA_REQUEST = 1000;
    final int LOAD_IMAGE = 1001;

    File file = null;
    long fileSize = 0;

    java.text.SimpleDateFormat tmpDateFormat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_add_insert);

        petName = findViewById(R.id.petName);
        petAge = findViewById(R.id.petAge);
        petGender = findViewById(R.id.petGender);
        petWeight = findViewById(R.id.petWeight);
        petPhoto = findViewById(R.id.petPhoto);

        btnLoad = findViewById(R.id.btnLoad);
        btn_add = findViewById(R.id.btn_add);
        btnCancle = findViewById(R.id.btnCancle);


        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                petPhoto.setVisibility(View.VISIBLE);

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), LOAD_IMAGE);

            }
        });

        goMain = findViewById(R.id.goMain);
        goMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PetSelect.class);
                startActivity(intent);
            }
        });

    }

    private File createFile() throws IOException {

        String imageFileName = "My" + tmpDateFormat.format(new Date()) + ".jpg";
        File storageDir = Environment.getExternalStorageDirectory();
        File curFile = new File(storageDir, imageFileName);

        return curFile;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {

            try {
                // 이미지 돌리기 및 리사이즈
                Bitmap newBitmap = CommonMethod.imageRotateAndResize(file.getAbsolutePath());
                if(newBitmap != null){
                    petPhoto.setImageBitmap(newBitmap);
                }else{
                    Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                }

                imageRealPathA = file.getAbsolutePath();
                String uploadFileName = imageRealPathA.split("/")[imageRealPathA.split("/").length - 1];
                imageDbPathA = CommonMethod.ipConfig + "/app/resources/" + uploadFileName;

            } catch (Exception e){
                e.printStackTrace();
            }
        }else if (requestCode == LOAD_IMAGE && resultCode == RESULT_OK) {

            try {
                String path = "";
                // Get the url from data
                Uri selectedImageUri = data.getData();
                if (selectedImageUri != null) {
                    // Get the path from the Uri
                    path = getPathFromURI(selectedImageUri);
                }
                // 이미지 돌리기 및 리사이즈
                Bitmap newBitmap = CommonMethod.imageRotateAndResize(path);
                if(newBitmap != null){
                    petPhoto.setImageBitmap(newBitmap);
                }else{
                    Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                }

                imageRealPathA = path;
                Log.d("petInsert", "imageFilePathA Path : " + imageRealPathA);
                String uploadFileName = imageRealPathA.split("/")[imageRealPathA.split("/").length - 1];
                imageDbPathA = CommonMethod.ipConfig + "/app/resources/" + uploadFileName;

            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    // Get the real path from the URI
    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    public void btnAddClicked(View view) {
        if (CommonMethod.isNetworkConnected(this) == true) {
            if (fileSize <= 30000000) {
                id = loginDTO.getMember_id().toString();
                petname = petName.getText().toString();
                petage = petAge.getText().toString();
                petgender = petGender.getText().toString();
                petweight = petWeight.getText().toString();

                Listinsert listinsert = new Listinsert(id, petname, petage, petweight, petgender, imageDbPathA, imageRealPathA);
                listinsert.execute();

                Intent showIntent = new Intent(getApplicationContext(), PetAdd.class);
                showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_SINGLE_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(showIntent);

                Intent refresh = new Intent(this, PetAdd.class);
                startActivity(refresh);
                finish();
            } else {
                Toast.makeText(this, "인터넷이 연결되어 있지 않습니다.",
                        Toast.LENGTH_SHORT).show();
            }
        }

        }
    }
