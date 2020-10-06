package com.example.cteam.board;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cteam.ATask.BoardDetailSelect;
import com.example.cteam.R;
import com.google.android.gms.maps.model.Circle;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.cteam.PetAdd.petAddDto;

public class BoardDetail extends AppCompatActivity {

    private static final String TAG = "BoardDetail";

    TextView board_detail_title, board_detail_id, board_detail_date,
            board_detail_content, board_detail_city, board_detail_region;
    CircleImageView board_detail_writer_img, board_detail_comment_img;
    EditText board_detail_comment_write;
    Button board_detail_comment_submit;
    ListView board_detail_comment;
    ImageView board_detail_image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_detail);

        board_detail_title = findViewById(R.id.board_detail_title);
        board_detail_id = findViewById(R.id.board_detail_id);
        board_detail_date = findViewById(R.id.board_detail_date);
        board_detail_content = findViewById(R.id.board_detail_content);
        board_detail_writer_img = findViewById(R.id.board_detail_writer_img);
        board_detail_comment_img = findViewById(R.id.board_detail_comment_img);
        board_detail_comment_write = findViewById(R.id.board_detail_comment_write);
        board_detail_comment_submit = findViewById(R.id.board_detail_comment_submit);
        board_detail_comment = findViewById(R.id.board_detail_comment);
        board_detail_city = findViewById(R.id.board_detail_city);
        board_detail_region = findViewById(R.id.board_detail_region);
        board_detail_image = findViewById(R.id.board_detail_image);


        if (getIntent() != null) {
            Intent intent = getIntent();
            String num = intent.getStringExtra("num");
            Log.i(TAG, "num: " + num);
            intent.putExtra("num", num);
            BoardDetailSelect boardDetailSelect = new BoardDetailSelect(num);
            boardDetailSelect.execute();
        }

        if(board_detail_image != null) {
            board_detail_image.setVisibility(View.VISIBLE);
        }

        Glide.with(this).load(petAddDto.getPetimage_path()).circleCrop().into(board_detail_comment_img);

    }
}