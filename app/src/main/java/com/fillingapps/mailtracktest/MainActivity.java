package com.fillingapps.mailtracktest;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.webView);
        loadHtml();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            loadHtml();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadHtml() {
        String html = String.format("<html> <head></head> <body> <script> function getWifiSSID(){ return '%s'; } document.write(getWifiSSID()); </script> </body> </html>", getWifiSSID());
        String mime = "text/html";
        String encoding = "utf-8";

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadDataWithBaseURL(null, html, mime, encoding, null);
    }

    private String getWifiSSID() {
        String ssid = "No active Wi-Fi detected";
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
        String ssidAux = info.getSSID();
        if (info.getSSID() != "<unknown ssid>") {
            ssid = String.format("Active Wi-Fi: %s", info.getSSID());
        }
        return ssid;
    }
}
