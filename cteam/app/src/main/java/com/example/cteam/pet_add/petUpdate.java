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

import static com.example.cteam.Login.loginDTO;
import static com.example.cteam.PetAdd.petAddDto;

public class petUpdate extends AppCompatActivity {

    EditText petName, petAge,petWeight,petGender;

    String petname = "";
    String petage = "";
    String petweight = "";
    String petgender ="";
    ImageView petPhoto;

    Button btnLoad,btnCancle,btn_update,btnReset;

    public String petimagepath;
    public String pImgDbPathU;
    public String imageRealPathA = "", imageDbPathU = "";

    final int CAMERA_REQUEST = 1000;
    final int LOAD_IMAGE = 1001;

    File file = null;
    long fileSize = 0;


   /* @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_add_update);

        petName = findViewById(R.id.petname);
        petAge = findViewById(R.id.petage);
        petGender = findViewById(R.id.petgender);
        petWeight = findViewById(R.id.petweight);
        petPhoto = findViewById(R.id.petPhoto);

        btnLoad = findViewById(R.id.btnLoad);
        btn_update = findViewById(R.id.btn_update);
        btnCancle = findViewById(R.id.btnCancle);
        btnReset = findViewById(R.id.btnReset);

        petPhoto = findViewById(R.id.petPhoto);

        // 보내온 값 파싱
        Intent intent = getIntent();
        //PetDTO selItem = (PetDTO) intent.getSerializableExtra("selItem");

      /*  petname = selItem.getPetname();
        petage = selItem.getPetage();
        petgender = selItem.getPetgender();
        petweight = selItem.getPetweight();*/


        // 가져온 값 써 넣기
        petName.setText(petAddDto.getPetname());
        petAge.setText(petAddDto.getPetage());
        petWeight.setText(petAddDto.getPetweight());
        petGender.setText(petAddDto.getPetgender());

      //  petimagepath = selItem.getPetimage_path();
        pImgDbPathU = petimagepath;
        imageDbPathU = pImgDbPathU;

        // 선택된 이미지 보여주기
        petPhoto.setVisibility(View.VISIBLE);
        Glide.with(this).load(petimagepath).into(petPhoto);

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

                imageRealPathA = file.getAbsolutePath();
                String uploadFileName = imageRealPathA.split("/")[imageRealPathA.split("/").length - 1];
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

                imageRealPathA = path;
                String uploadFileName = imageRealPathA.split("/")[imageRealPathA.split("/").length - 1];
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

    public void btnUpdateClicked(View view){
        if(CommonMethod.isNetworkConnected(this) == true){
            if(fileSize <= 30000000) {  // 파일크기가 30메가 보다 작아야 업로드 할수 있음

                petname = petName.getText().toString();
                petage = petAge.getText().toString();
                petgender = petGender.getText().toString();
                petweight = petWeight.getText().toString();





                ListUpdate listUpdate = new ListUpdate(loginDTO.getMember_id(), petname, petage,petgender,petweight, pImgDbPathU, imageDbPathU, imageRealPathA);
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

    }
    public void btnCancelClicked(View view){
        finish();
    }*/
}
