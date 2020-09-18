package com.example.cteam.ATask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.nio.charset.Charset;

import static com.example.cteam.Common.CommonMethod.ipConfig;

public class PetbarListInsert extends AsyncTask<Void,Void,Void> {

    String memo,icon,hour2,minute,date;

    public PetbarListInsert(String date,String memo, String icon, String hour2, String minute )
    {
        this.date=date;
        this.memo=memo;
        this.icon=icon;
        this.hour2=hour2;
        this.minute=minute;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            // MultipartEntityBuild 생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            // 문자열 및 데이터 추가

            builder.addTextBody("date", date, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("memo", memo, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("icon", icon, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("hour", hour2, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("minute", minute, ContentType.create("Multipart/related", "UTF-8"));


            String postURL = ipConfig + "/app/cPetBarInsert";

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
