package com.example.cteam.ATask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import com.example.cteam.Login;

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
import static com.example.cteam.Login.loginDTO;


public class Listinsert extends AsyncTask<Void,Void,Void> {

    String id,name,age,weight,gender,imageDBPathA,imageRealPathA;

    public Listinsert(String name, String age, String weight, String gender, String imageDBPathA, String imageRealPathA) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.gender = gender;
        this.imageDBPathA = imageDBPathA;
        this.imageRealPathA = imageRealPathA;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
    id= loginDTO.getMember_id();

        try {
            // MultipartEntityBuild 생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            // 문자열 및 데이터 추가

            builder.addTextBody("id", id, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("name", name, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("age", age, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("weight", weight , ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("gender", gender , ContentType.create("Multipart/related", "UTF-8"));

            builder.addTextBody("imageDBPathA", imageDBPathA, ContentType.create("Multipart/related", "UTF-8"));
            builder.addPart("image", new FileBody(new File(imageRealPathA)));

            String postURL = ipConfig + "/app/PetaddInsert";

            HttpClient httpClient = AndroidHttpClient.newInstance("cteam");
            HttpPost httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //Log.d("Sub1Add:imageFilePath1", "추가성공");

    }

}
