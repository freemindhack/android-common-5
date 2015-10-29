package com.pheth.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Project: android-common
 * Description: com.pheth.android-PrintLogActivity
 * Author: danhantao
 * Update: danhantao(2015-10-29 19:05)
 * Email: danhantao@yeah.net
 * QQ: 1152892859
 */
public class PrintLogActivity extends Activity {
    private TextView textview_log;
    private ScrollView scrollView_log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.printlog_layout);
        textview_log = (TextView) findViewById(R.id.textview_log);
        scrollView_log = (ScrollView) findViewById(R.id.scrollView_log);
        scrollView_log.setScrollbarFadingEnabled(false);
        scrollView_log.setScrollContainer(true);
        textview_log.setText("Log:");
    }


    public void outputLog(String str) {
        textview_log.append("\n" + str);
        scrollView_log.post(new Runnable() {
            @Override
            public void run() {
                scrollView_log.smoothScrollTo(0, scrollView_log.getHeight());
            }
        });
    }
}
