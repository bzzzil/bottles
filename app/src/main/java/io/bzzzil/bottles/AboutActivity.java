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

        about.append("RSA: " + getString(R.string.git_rsa).substring(0, 6));

        if (!getString(R.string.git_local_changes).isEmpty()) {
            about.append(" + LOCAL CHANGES");
        }

        ((TextView)findViewById(R.id.about_text)).setText(about);
    }
}
