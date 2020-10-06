package com.example.cteam.ATask;

import android.app.ProgressDialog;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.cteam.Adapter.BoardAdapter;
import com.example.cteam.Dto.BoardDTO;
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

    String num;

    public BoardDetailSelect(String num) {
        this.num = num;
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

    public BoardDTO readMessage(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        String member_id = "", member_pw = "", member_name = "", member_phonenum = "", member_question = "", member_answer = "";

        reader.beginObject();
        while (reader.hasNext()) {
            String readStr = reader.nextName();
            if (readStr.equals("member_id")) {
                member_id = reader.nextString();
            } else if (readStr.equals("member_pw")) {
                member_pw = reader.nextString();
            } else if (readStr.equals("member_name")) {
                member_name = reader.nextString();
            } else if (readStr.equals("member_phonenum")) {
                member_phonenum = reader.nextString();
            } else if (readStr.equals("member_question")) {
                member_question = reader.nextString();
            } else if (readStr.equals("member_answer")) {
                member_answer = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        Log.d("main:loginselect : ", member_id + "," + member_pw + "," + member_name + "," + member_phonenum + "," + member_question
                + "," + member_answer);
        return null;//new BoardDTO(member_id, member_pw, member_name, member_phonenum, member_question, member_answer);

    }
}