package com.example.cteam.ATask;

import android.app.ProgressDialog;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.cteam.Adapter.BoardAdapter;
import com.example.cteam.Dto.BoardDTO;
import com.example.cteam.Dto.BoardDetailDTO;
import com.example.cteam.Dto.MemberDTO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.example.cteam.Common.CommonMethod.ipConfig;
import static com.example.cteam.Login.loginDTO;

public class BoardDetailSelect extends AsyncTask<Void, Void, Void> {

    private static final String TAG = "BoardDetailSelect";

    String num, member_id;

    public BoardDetailSelect(String num, String member_id) {
        this.num = num;
        this.member_id = member_id;
    }

    HttpClient httpClient;
    HttpPost httpPost;
    HttpResponse httpResponse;
    HttpEntity httpEntity;

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            Log.d(TAG, "doInBackground: 실행");

            // MultipartEntityBuild 생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            // 문자열 및 데이터 추가
            builder.addTextBody("num", num, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("member_id", member_id, ContentType.create("Multipart/related", "UTF-8"));

            String postURL = ipConfig + "/app/boarddetail";
            // 전송
            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("cteam");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();

            // 하나의 오브젝트 가져올때
            //loginDTO = readMessage(inputStream);

            //inputStream.close();

        } catch (Exception e) {
            Log.d("main:boarddetailselect", e.getMessage());
            e.printStackTrace();
        } finally {
            if (httpEntity != null) {
                httpEntity = null;
            }
            if (httpResponse != null) {
                httpResponse = null;
            }
            if (httpPost != null) {
                httpPost = null;
            }
            if (httpClient != null) {
                httpClient = null;
            }

        }
        Log.d(TAG, "doInBackground: 종료");
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

    }

    public BoardDetailDTO readMessage(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        String num = "", subject = "", title = "", content = "", member_id = "", petname = "", imagepath =""
                , city = "", region = "", petimagepath = "";

        reader.beginObject();
        while (reader.hasNext()) {
            String readStr = reader.nextName();
            if (readStr.equals("board_num")) {
                num = reader.nextString();
            } else if (readStr.equals("board_subject")) {
                subject = reader.nextString();
            } else if (readStr.equals("board_title")) {
                title = reader.nextString();
            } else if (readStr.equals("board_content")) {
                content = reader.nextString();
            } else if (readStr.equals("member_id")) {
                member_id = reader.nextString();
            } else if (readStr.equals("petname")) {
                petname = reader.nextString();
            } else if (readStr.equals("board_imagepath")) {
                imagepath = reader.nextString();
            } else if (readStr.equals("board_city")) {
                city = reader.nextString();
            } else if (readStr.equals("board_region")) {
                region = reader.nextString();
            } else if (readStr.equals("petimagepath")) {
                petimagepath = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        Log.d("board:boarddetail : ", num + "," + subject + "," + title + "," + content + "," + member_id
                + "," + petname + "," + imagepath + "," + city + "," + region + "," + petimagepath);

        return new BoardDetailDTO(num, subject, title, content, member_id, petname, imagepath, city, region, petimagepath);

    }
}