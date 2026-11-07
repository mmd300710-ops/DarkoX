package com.darkox.browser;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.*;
import android.view.inputmethod.EditorInfo;
import android.view.KeyEvent;
import android.widget.*;
import android.graphics.Bitmap;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

public class MainActivity extends Activity {
    private WebView web;
    private EditText address;
    private static final String HOME = "file:///android_asset/index.html";

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_main);
        web = findViewById(R.id.browser);
        address = findViewById(R.id.address);
        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setSupportMultipleWindows(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(false);
        web.setWebViewClient(new WebViewClient() {
            @Override public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri uri = request.getUrl();
                String scheme = uri.getScheme();
                if ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)) return false;
                return true;
            }
            @Override public void onPageStarted(WebView view, String url, Bitmap icon) {
                address.setText(url.startsWith("file:") ? "" : url);
            }
            @Override public void onPageFinished(WebView view, String url) {
                if (!url.startsWith("file:")) address.setText(url);
            }
        });
        web.setWebChromeClient(new WebChromeClient());
        findViewById(R.id.go).setOnClickListener(v -> navigate(address.getText().toString()));
        address.setOnEditorActionListener((v, action, event) -> {
            if (action == EditorInfo.IME_ACTION_GO || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                navigate(address.getText().toString()); return true;
            }
            return false;
        });
        findViewById(R.id.back).setOnClickListener(v -> { if (web.canGoBack()) web.goBack(); });
        findViewById(R.id.home).setOnClickListener(v -> web.loadUrl(HOME));
        findViewById(R.id.rewards).setOnClickListener(v -> web.loadUrl("file:///android_asset/rewards.html"));
        findViewById(R.id.admin).setOnClickListener(v -> web.loadUrl("file:///android_asset/admin.html"));
        web.loadUrl(HOME);
    }

    private void navigate(String raw) {
        String input = raw == null ? "" : raw.trim();
        if (input.isEmpty()) return;
        String url;
        if (input.matches("(?i)^https?://.*")) url = input;
        else if (input.matches("(?i)^[a-z0-9.-]+\\.[a-z]{2,}(/.*)?$")) url = "https://" + input;
        else url = "https://www.google.com/search?q=" + Uri.encode(input);
        web.loadUrl(url);
    }

    @Override public void onBackPressed() {
        if (web != null && web.canGoBack()) web.goBack(); else super.onBackPressed();
    }
}
