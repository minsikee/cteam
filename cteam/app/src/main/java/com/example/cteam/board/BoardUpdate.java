package com.example.cteam.board;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cteam.Dto.BoardDetailDTO;
import com.example.cteam.Dto.BoardinsertDTO;
import com.example.cteam.Login;
import com.example.cteam.R;

import java.io.File;

public class BoardUpdate extends AppCompatActivity {

    private static final String TAG = "BoardUpdate";

    Spinner Uboard_write_subject;
    Spinner Uboard_write_region1;
    Spinner Uboard_write_region2;
    EditText Uboard_write_title, Uboard_write_content;
    Button Uboard_write_filebutton, Uboard_write_cancel, Uboard_write_submit;
    ImageView Uboard_write_image;

    public String imagePath;
    public String pImgDbPathU;
    public String imageRealPathU = "", imageDbPathU = "";

    final int CAMERA_REQUEST = 1010;
    final int LOAD_IMAGE = 1011;

    File file = null;
    long fileSize = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_write_update);

        Uboard_write_subject = findViewById(R.id.Uboard_write_subject);
        Uboard_write_region1 = findViewById(R.id.Uboard_write_region1);
        Uboard_write_region2 = findViewById(R.id.Uboard_write_region2);
        Uboard_write_title = findViewById(R.id.Uboard_write_title);
        Uboard_write_content = findViewById(R.id.Uboard_write_content);
        Uboard_write_filebutton = findViewById(R.id.Uboard_write_filebutton);
        Uboard_write_cancel = findViewById(R.id.Uboard_write_cancel);
        Uboard_write_submit = findViewById(R.id.Uboard_write_submit);
        Uboard_write_image = findViewById(R.id.Uboard_write_image);

        Intent intent = getIntent();
        BoardDetailDTO boardDetailDTO = (BoardDetailDTO)intent.getSerializableExtra("boardDetailDTO");

        Uboard_write_subject = boardDetailDTO.getBoard_subject();

    }

}
