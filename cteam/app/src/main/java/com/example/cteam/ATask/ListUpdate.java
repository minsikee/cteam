package com.example.cteam.ATask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

import com.example.cteam.Common.CommonMethod;

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

public class ListUpdate extends AsyncTask<Void,Void,Void> {

    public String memeber_id,petname,petage,petweight,petgender;
    public String petimagepath,imageRealPathA="";

    public ListUpdate(String member_id, String memeber_id, String petname, String petage, String petweight, String petgender, String petimagepath, String imageRealPathA) {
        this.memeber_id = memeber_id;
        this.petname = petname;
        this.petage = petage;
        this.petweight = petweight;
        this.petgender = petgender;
        this.petimagepath = petimagepath;
        this.imageRealPathA = imageRealPathA;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            // MultipartEntityBuild 생성
            String postURL = "";
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            // 문자열 및 데이터 추가
            builder.addTextBody("memeber_id", memeber_id, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("petname", petname, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("petage", petage, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("petweight", petweight , ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("petgender", petgender , ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("petimagepath", petimagepath, ContentType.create("Multipart/related", "UTF-8"));
            builder.addPart("image", new FileBody(new File(imageRealPathA)));

            Log.d("PetUpdate", memeber_id);
            Log.d("PetUpdate", petname);
            Log.d("PetUpdate", petage);
            Log.d("PetUpdate", petweight);
            Log.d("PetUpdate", petgender);
            Log.d("PetUpdate", petimagepath);
            Log.d("PetUpdate", imageRealPathA);

            // 이미지를 새로 선택했으면 선택한 이미지와 기존에 이미지 경로를 같이 보낸다
            if(!petimagepath.equals("")){
                Log.d("Sub1Update:postURL", "1");
                // 기존에 있던 DB 경로
                builder.addTextBody("pDbImgPath", petimagepath, ContentType.create("Multipart/related", "UTF-8"));
                // DB에 저장할 경로
                builder.addTextBody("dbImgPath", petimagepath, ContentType.create("Multipart/related", "UTF-8"));
                // 실제 이미지 파일
                builder.addPart("image", new FileBody(new File(imageRealPathA)));

                postURL = CommonMethod.ipConfig + "/app/cPetUpdate";

            }else if(imageRealPathA.equals("")){  // 이미지를 바꾸지 않았다면
                Log.d("Sub1Update:postURL", "3");
                postURL = CommonMethod.ipConfig + "/app/cPetUpdate";
            }else{
                Log.d("Sub1Update:postURL", "5 : error");
            }
            Log.d("Sub1Update:postURL", postURL);

            HttpClient httpClient = AndroidHttpClient.newInstance("Android");
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


    }

}
