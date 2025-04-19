package com.teamluckraft.luckraft;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.ConsoleMessage;
import android.webkit.SslErrorHandler;
import android.net.http.SslError;
import android.util.Log;
import android.view.View;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    // 👉 여기서 직접 개발용 / 운영용 중 하나를 선택
    // 개발용 내부 IP
    private final String TARGET_URL = "https://192.168.0.9/app/";

    // 운영용 도메인 (릴리즈 시 이걸로 교체하면 됨)
    // private final String TARGET_URL = "https://murro.co.kr/app/";

    private final String TAG = "LuckraftWebView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccess(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setUserAgentString("Mozilla/5.0 (Linux; Android 13; Mobile)");

        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        // 콘솔 로그 출력용
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.d(TAG, "콘솔 로그: " + consoleMessage.message() + " (라인 " + consoleMessage.lineNumber() + ")");
                return super.onConsoleMessage(consoleMessage);
            }
        });

        // SSL 우회 및 로딩 로그
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                Log.e(TAG, "SSL 오류: " + error.toString());
                handler.proceed(); // SSL 무시
            }

            @Override
            public void onPageStarted(WebView view, String url, android.graphics.Bitmap favicon) {
                Log.d(TAG, "페이지 로딩 시작: " + url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.d(TAG, "페이지 로딩 완료: " + url);
            }
        });

        Log.d(TAG, "WebView URL 로드: " + TARGET_URL);
        webView.loadUrl(TARGET_URL);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
