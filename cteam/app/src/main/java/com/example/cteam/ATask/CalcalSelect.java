package com.example.cteam.ATask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.cteam.Calendar;
import com.example.cteam.CalendarAdd;
import com.example.cteam.Dto.CalendarDTO;

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

public class CalcalSelect extends AsyncTask<Void, Void, ArrayList<CalendarDTO>> {

    ArrayList<CalendarDTO> icons;
    String petname;

    public CalcalSelect(String petname) {
        this.petname = petname;
    }

    HttpClient httpClient;
    HttpPost httpPost;
    HttpResponse httpResponse;
    HttpEntity httpEntity;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        icons = new ArrayList<>();
    }

    @Override
    protected ArrayList<CalendarDTO> doInBackground(Void... voids) {
        String postURL = ipConfig + "/app/calcalSelect";

        // 기본셋팅
        try {
            // MultipartEntityBuild 생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            // 기본셋팅 여기까지

            builder.addTextBody("petname", petname, ContentType.create("Multipart/related", "UTF-8"));

            // 전송, 그대로 갖다쓰기
            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("cteam");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();

            // 받은거, 배열 형태로 여러개 받을 때 사용
            readJsonStream(inputStream);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
            return icons;
    }

    @Override
    protected void onPostExecute(ArrayList<CalendarDTO> icons) {

    }

    public void readJsonStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        try {
            reader.beginArray();
            while (reader.hasNext()) {
                icons.add(readMessage(reader));
            }
            reader.endArray();
            Log.d("main:Calcalselect", "readJsonStream: " + icons.size());
        } finally {
            reader.close();
        }

    }

    public CalendarDTO readMessage(JsonReader reader) throws IOException {

        String calendar_date = "", calendar_icon = "", calendar_memo = "", calendar_hour = "", calendar_minute = "",calendar_id="";

        reader.beginObject();
        while (reader.hasNext()) {
            String readStr = reader.nextName();
            if (readStr.equals("calendar_date")) {
                calendar_date = reader.nextString();
            } else if (readStr.equals("calendar_icon")) {
                calendar_icon = reader.nextString();
            } else if (readStr.equals("calendar_memo")) {
                calendar_memo = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();

        return new CalendarDTO(calendar_date, calendar_icon, calendar_memo, calendar_hour, calendar_minute,calendar_id);

    }

}
