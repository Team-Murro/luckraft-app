// MainActivity.java
package com.teamluckraft.luckraft;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLotto = findViewById(R.id.btn_lotto);
        Button btn720 = findViewById(R.id.btn_720);
        Button btnFortune = findViewById(R.id.btn_fortune);
        Button btnQuote = findViewById(R.id.btn_quote);
        Button btnSpoiler = findViewById(R.id.btn_spoiler);

        btnLotto.setOnClickListener(v -> openWeb("https://murro.co.kr/lotto"));
        btn720.setOnClickListener(v -> openWeb("https://murro.co.kr/720"));
        btnFortune.setOnClickListener(v -> openWeb("https://murro.co.kr/fortune"));
        btnQuote.setOnClickListener(v -> openWeb("https://murro.co.kr/quote"));
        btnSpoiler.setOnClickListener(v -> openWeb("https://murro.co.kr/spoiler"));
    }

    private void openWeb(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}

// activity_main.xml (res/layout/activity_main.xml)
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:orientation="vertical"
android:background="#FFFFFF"
android:gravity="center"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:padding="24dp">

    <ImageView
android:id="@+id/logo"
android:src="@drawable/luckraft_logo"
android:layout_width="150dp"
android:layout_height="150dp"
android:layout_marginBottom="24dp"/>

    <TextView
android:text="Luckraft"
android:textSize="28sp"
android:textStyle="bold"
android:layout_marginBottom="32dp"
android:textColor="#222222"/>

    <Button
android:id="@+id/btn_lotto"
android:text="🎯 로또 추천번호"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:layout_marginBottom="12dp"/>

    <Button
android:id="@+id/btn_720"
android:text="💸 연금복권 추천"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:layout_marginBottom="12dp"/>

    <Button
android:id="@+id/btn_fortune"
android:text="🔮 오늘의 운세"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:layout_marginBottom="12dp"/>

    <Button
android:id="@+id/btn_quote"
android:text="🧠 오늘의 명언"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:layout_marginBottom="12dp"/>

    <Button
android:id="@+id/btn_spoiler"
android:text="🧩 열린 결말 해석"
android:layout_width="match_parent"
android:layout_height="wrap_content"/>

</LinearLayout>
