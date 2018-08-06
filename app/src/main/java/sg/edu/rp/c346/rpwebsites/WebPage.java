package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebPage extends AppCompatActivity {

    WebView wvPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_page);

        wvPage = findViewById(R.id.webViewUrl);

        //WebSettings webSetting = wvPage.getSettings();


        Intent intentReceived = getIntent();
        String url = intentReceived.getStringExtra("url");
        wvPage.loadUrl(url);

        /*
        webSetting.setJavaScriptEnabled(true);
        webSetting.setAllowFileAccess(false);
        webSetting.setDisplayZoomControls(true);
        */
    }
}
