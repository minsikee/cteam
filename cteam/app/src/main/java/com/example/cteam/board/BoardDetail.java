package com.example.cteam.board;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cteam.ATask.BoardDetailSelect;
import com.example.cteam.ATask.CommentInsert;
import com.example.cteam.ATask.Listinsert;
import com.example.cteam.Adapter.CommentAdapter;
import com.example.cteam.Common.CommonMethod;
import com.example.cteam.Dto.BoardDTO;
import com.example.cteam.Dto.BoardDetailDTO;
import com.example.cteam.Dto.CommentDTO;
import com.example.cteam.Dto.PetDTO;
import com.example.cteam.PetAdd;
import com.example.cteam.PetSelect;
import com.example.cteam.R;
import com.example.cteam.pet_add.petInsert;
import com.google.android.gms.maps.model.Circle;

import org.w3c.dom.Comment;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.cteam.Login.loginDTO;
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
    BoardDetailDTO boardDetailDTO;

    String member_id, board_num,content="";
    public String imageRealPathA, writer_image;

    ArrayList<CommentDTO> commentList;

    RecyclerView recyclerView;
    CommentAdapter adapter;

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

        commentList = new ArrayList<>();
        adapter = new CommentAdapter(this, commentList);
        recyclerView = findViewById(R.id.board_detail_comment);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        board_detail_comment_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSubmitClicked();
            }
        });

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

    public void btnSubmitClicked() {
        member_id= loginDTO.getMember_id();
        board_num = boardDetailDTO.getboard_num2();
        content = board_detail_comment_write.getText().toString();
        writer_image = petAddDto.getPetimage_path();

        CommentInsert commentInsert = new CommentInsert(member_id, board_num, content, writer_image, imageRealPathA);
        commentInsert.execute();

        Intent showIntent = new Intent(getApplicationContext(), BoardDetail.class);
        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_SINGLE_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(showIntent);
        board_detail_comment_write.setText(null);
//        Intent refresh = new Intent(this, BoardDetail.class);
//        startActivity(refresh);
       // finish();

    }
}