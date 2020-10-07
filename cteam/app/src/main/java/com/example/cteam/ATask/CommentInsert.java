package com.example.cteam.ATask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;

import java.io.File;
import java.nio.charset.Charset;

import static com.example.cteam.Common.CommonMethod.ipConfig;

public class CommentInsert extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "Boardinsert";

    private String member_id, board_num, content, writedate, writer_image, comment_num, imageDbPathA, imageRealPathA;

    public CommentInsert(String member_id, String board_num, String content, String writedate
            , String writer_image, String comment_num, String imageDbPathA, String imageRealPathA) {
        this.member_id = member_id;
        this.board_num = board_num;
        this.content = content;
        this.writedate = writedate;
        this.writer_image = writer_image;
        this.comment_num = comment_num;
        this.imageDbPathA = imageDbPathA;
        this.imageRealPathA = imageRealPathA;
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
            builder.setCharset(Charset.forName("UTF-8"));

            // 문자열 및 데이터 추가
            builder.addTextBody("member_id", member_id, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("board_num", board_num, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("content", content, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("writedate", writedate, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("comment_num", comment_num, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("writer_image", writer_image, ContentType.create("Multipart/related", "UTF-8"));
           if (imageDbPathA != null) {
                builder.addTextBody("writer_image", imageDbPathA, ContentType.create("Multipart/related", "UTF-8"));
            }
            if (imageRealPathA != null) {
                builder.addPart("image", new FileBody(new File(imageRealPathA)));
            }

            String postURL = ipConfig + "/app/boardinsert";

            // 전송
            httpClient = AndroidHttpClient.newInstance("cteam");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
/*
            // 받기
            BufferedReader br = new BufferedReader(new InputStreamReader(httpEntity.getContent()));
            String line = "";
            StringBuffer sb = new StringBuffer();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
*/

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(httpEntity != null){
                httpEntity = null;
            }
            if(httpResponse != null){
                httpResponse = null;
            }
            if(httpPost != null){
                httpPost = null;
            }
            if(httpClient != null){
                httpClient = null;
            }
        }
        Log.d(TAG, "doInBackground: 종료");
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //Log.d("Sub1Add:imageFilePath1", "추가성공");

    }
}
