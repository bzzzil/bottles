package io.bzzzil.bottles;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        StringBuilder about = new StringBuilder();

        about.append("RSA: ");
        about.append(getString(R.string.build_git_rsa).substring(0, 6));

        if (!getString(R.string.build_git_local_changes).isEmpty()) {
            about.append(" + LOCAL CHANGES");
        }

        about.append("\r\nBuild date: ");
        about.append(getString(R.string.build_timestamp));

        ((TextView)findViewById(R.id.about_text)).setText(about);
    }
}
