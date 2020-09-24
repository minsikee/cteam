package com.example.cteam.pet_add;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.cteam.ATask.ListUpdate;
import com.example.cteam.Common.CommonMethod;
import com.example.cteam.Dto.PetDTO;
import com.example.cteam.PetAdd;
import com.example.cteam.R;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class petUpdate extends AppCompatActivity {

    EditText petName, petAge,petWeight,petGender;

    String name = "";
    String age = "";
    String weight = "";
    String gender ="";
    ImageView petPhoto;

    Button btnLoad,btnCancle,btn_update,btnReset;

    public String imagePath;
    public String pImgDbPathU;
    public String imageRealPathU = "", imageDbPathU = "";

    final int CAMERA_REQUEST = 1000;
    final int LOAD_IMAGE = 1001;

    File file = null;
    long fileSize = 0;

    java.text.SimpleDateFormat tmpDateFormat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_add_update);

       /* petName = findViewById(R.id.petName);
        petAge = findViewById(R.id.petAge);
        petGender = findViewById(R.id.petGender);
        petWeight = findViewById(R.id.petWeight);
        petPhoto = findViewById(R.id.petPhoto);

        btnLoad = findViewById(R.id.btnLoad);
        btn_update = findViewById(R.id.btn_update);
        btnCancle = findViewById(R.id.btnCancle);
        btnReset = findViewById(R.id.btnReset);

        petPhoto = findViewById(R.id.petPhoto);

        Intent intent = getIntent();
        PetDTO selItem = (PetDTO) intent.getSerializableExtra("selItem");

        name = selItem.getName();
        age = selItem.getAge();
        gender = selItem.getGender();
        weight = selItem.getWeight();

        petName.setText(name);
        petAge.setText(age);
        petWeight.setText(weight);
        petGender.setText(gender);

        imagePath = selItem.getImage_path();
        pImgDbPathU = imagePath;
        imageDbPathU = imagePath;

        petPhoto.setVisibility(View.VISIBLE);
        Glide.with(this).load(imagePath).into(petPhoto);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                petPhoto.setVisibility(View.VISIBLE);
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent,"Select picture"), LOAD_IMAGE);


            }
        });

    }

    private File createFile() throws IOException {
        java.text.SimpleDateFormat tmpDateFormat = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss");

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

                imageRealPathU = file.getAbsolutePath();
                String uploadFileName = imageRealPathU.split("/")[imageRealPathU.split("/").length - 1];
                imageDbPathU = CommonMethod.ipConfig + "/app/resources/" + uploadFileName;

                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));

                Log.d("Sub1Update:picPath", file.getAbsolutePath());

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

                imageRealPathU = path;
                String uploadFileName = imageRealPathU.split("/")[imageRealPathU.split("/").length - 1];
                imageDbPathU = CommonMethod.ipConfig + "/app/resources/" + uploadFileName;

            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }

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

    public void btnAddClicked(View view){
        if(CommonMethod.isNetworkConnected(this) == true){
            if(fileSize <= 30000000) {  // 파일크기가 30메가 보다 작아야 업로드 할수 있음

                name = petName.getText().toString();
                age = petAge.getText().toString();
                gender = petGender.getText().toString();
                weight = petWeight.getText().toString();





                ListUpdate listUpdate = new ListUpdate( name, age,gender,weight, pImgDbPathU, imageDbPathU, imageRealPathU);
                listUpdate.execute();



                Intent showIntent = new Intent(getApplicationContext(), PetAdd.class);
                showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_SINGLE_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(showIntent);

                finish();
            }else{
                // 알림창 띄움
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("알림");
                builder.setMessage("파일 크기가 30MB초과하는 파일은 업로드가 제한되어 있습니다.\n30MB이하 파일로 선택해 주십시요!!!");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }

        }else {
            Toast.makeText(this, "인터넷이 연결되어 있지 않습니다.",
                    Toast.LENGTH_SHORT).show();
        }
*/
    }
}
