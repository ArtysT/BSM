package com.example.thene.bsm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_message);

        Intent intent = getIntent();

        String mesage = intent.getStringExtra(HomeActivity.MESAGE);

        TextView textView = (TextView) findViewById(R.id.messageView);
        textView.setText(mesage);

        Button button = (Button) findViewById(R.id.buttonReturn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewMessageActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
