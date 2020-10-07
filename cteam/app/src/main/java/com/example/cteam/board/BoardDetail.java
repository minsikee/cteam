package com.example.cteam.board;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.cteam.Dto.BoardDTO;
import com.example.cteam.Dto.BoardDetailDTO;
import com.example.cteam.Dto.PetDTO;
import com.example.cteam.R;
import com.google.android.gms.maps.model.Circle;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.cteam.PetAdd.petAddDto;

public class BoardDetail extends AppCompatActivity {

    private static final String TAG = "BoardDetail";

    TextView board_detail_title, board_detail_id, board_detail_date,
            board_detail_content, board_detail_city, board_detail_region;
    CircleImageView board_detail_writer_img, board_detail_comment_img;
    EditText board_detail_comment_write;
    Button board_detail_comment_submit;
    RecyclerView board_detail_comment;
    ImageView board_detail_image;
    List<BoardDetailDTO> list;
    BoardDetailDTO boardDetailDTO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_detail);

        boardDetailDTO = new BoardDetailDTO();

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
            String member_id = intent.getStringExtra("member_id");
            Log.i(TAG, "num: " + num);
            Log.i(TAG, "member_id: " + member_id);
            BoardDetailSelect boardDetailSelect = new BoardDetailSelect(num, member_id);
            boardDetailSelect.execute();
            try {
                boardDetailDTO = boardDetailSelect.get();
                Log.i(TAG, "dto " + boardDetailDTO.getBoard_title());
            }catch(Exception e) {
                e.getMessage();
            }

        }

    }
    @Override
    public void onStart(){
        super.onStart();

        board_detail_title.setText(boardDetailDTO.getBoard_title());
        board_detail_id.setText(boardDetailDTO.getmember_id2());
        board_detail_date.setText(boardDetailDTO.getBoard_date());
        board_detail_content.setText(boardDetailDTO.getBoard_content());
        board_detail_city.setText(boardDetailDTO.getBoard_city());
        board_detail_region.setText(boardDetailDTO.getBoard_region());

        Glide.with(this).load(boardDetailDTO.getBoard_imagepath()).into(board_detail_image);
        Glide.with(this).load(boardDetailDTO.getPetimagepath()).circleCrop().into(board_detail_writer_img);
        Glide.with(this).load(petAddDto.getPetimage_path()).circleCrop().into(board_detail_comment_img);
    }

    @Override
    public void onResume(){
        super.onResume();

        board_detail_title.setText(boardDetailDTO.getBoard_title());
        board_detail_id.setText(boardDetailDTO.getmember_id2());
        board_detail_date.setText(boardDetailDTO.getBoard_date());
        board_detail_content.setText(boardDetailDTO.getBoard_content());
        board_detail_city.setText(boardDetailDTO.getBoard_city());
        board_detail_region.setText(boardDetailDTO.getBoard_region());

        Glide.with(this).load(boardDetailDTO.getBoard_imagepath()).into(board_detail_image);
        Glide.with(this).load(boardDetailDTO.getPetimagepath()).circleCrop().into(board_detail_writer_img);
        Glide.with(this).load(petAddDto.getPetimage_path()).circleCrop().into(board_detail_comment_img);
    }
}