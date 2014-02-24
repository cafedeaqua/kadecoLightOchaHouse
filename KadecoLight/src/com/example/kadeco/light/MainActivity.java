
package com.example.kadeco.light;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private static final String TAG = "tag";
    private ArrayList<URL> mUrlList;
    private Button mBtnAllOnOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUrlList = new ArrayList<URL>();

        try {
            mUrlList.add(new URL(
                    "http://192.168.1.142:31413/call.json?method=set&params=[GeneralLighting1,[0x80,0x30]]"));
            mUrlList.add(new URL(
                    "http://192.168.1.142:31413/call.json?method=set&params=[GeneralLighting2,[0x80,0x30]]"));
            mUrlList.add(new URL(
                    "http://192.168.1.142:31413/call.json?method=set&params=[GeneralLighting1,[0x80,0x31]]"));
            mUrlList.add(new URL(
                    "http://192.168.1.142:31413/call.json?method=set&params=[GeneralLighting3,[0x80,0x30]]"));
            mUrlList.add(new URL(
                    "http://192.168.1.142:31413/call.json?method=set&params=[GeneralLighting2,[0x80,0x31]]"));
            mUrlList.add(new URL(
                    "http://192.168.1.142:31413/call.json?method=set&params=[GeneralLighting4,[0x80,0x30]]"));
            mUrlList.add(new URL(
                    "http://192.168.1.142:31413/call.json?method=set&params=[GeneralLighting3,[0x80,0x31]]"));
            mUrlList.add(new URL(
                    "http://192.168.1.142:31413/call.json?method=set&params=[GeneralLighting4,[0x80,0x31]]"));
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }

        mBtnAllOnOff = (Button)findViewById(R.id.btnAllOnOff);

        Intent intent = getIntent();
        String action = intent.getAction();
        if (action.equals("android.nfc.action.NDEF_DISCOVERED")) {
            Uri uri = intent.getData();
            List<String> pathList = uri.getPathSegments();
            String path = pathList.get(0);
            switch (Integer.valueOf(path)) {
                case 1:
                    onClickAllOnOff(null);
                    break;
                case 2:
                    Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
                    onClick2(null);
                    break;
                case 3:
                    Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
                    onClick3(null);
                    break;
                case 4:
                    Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();
                    onClick4(null);
                    break;
                case 5:
                    Toast.makeText(this, "5", Toast.LENGTH_SHORT).show();
                    onClick5(null);
                    break;
                case 6:
                    Toast.makeText(this, "6", Toast.LENGTH_SHORT).show();
                    onClick6(null);
                    break;
                default:
                    break;
            }
        }
    }

    public void onClickAllOnOff(View v) {
        mBtnAllOnOff.setEnabled(false);
        URL[] urlArray = mUrlList.toArray(new URL[0]);
        DownloadFilesTask task = new DownloadFilesTask();
        task.execute(urlArray);
    }

    public void onClick2(View v) {
    }
    public void onClick3(View v) {
    }
    public void onClick4(View v) {
    }
    public void onClick5(View v) {
    }
    public void onClick6(View v) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private class DownloadFilesTask extends AsyncTask<URL, Integer, Long> {
        @Override
        protected Long doInBackground(URL... urls) {
            int count = urls.length;
            long totalSize = 0;
            for (int i = 0; i < count; i++) {
                connect(urls[i].toString());
                android.os.SystemClock.sleep(300);
            }
            return totalSize;
        }

        @Override
        protected void onPostExecute(Long result) {
            mBtnAllOnOff.setEnabled(true);
        }

        public void connect(String url) {
            HttpClient httpclient = new DefaultHttpClient();

            HttpGet httpget = new HttpGet(url);

            HttpResponse response;
            try {
                response = httpclient.execute(httpget);

                Log.i(TAG, response.getStatusLine().toString());

                HttpEntity entity = response.getEntity();

                if (entity != null) {
                    InputStream instream = entity.getContent();
                    String result = convertStreamToString(instream);
                    instream.close();
                }

            } catch (Exception e) {
            }
        }

        private String convertStreamToString(InputStream is) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        }

    }


}
