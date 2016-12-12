package com.example.thene.bsm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.thene.bsm.PassHandler.setPassword;

public class ChangePassActivity extends AppCompatActivity {

    private String newPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        Button button = (Button) findViewById(R.id.buttonChangePassword);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editNewPass);
                newPassword = editText.getText().toString();
                if (!newPassword.matches("")) {
                    setPassword(newPassword);
                    Intent intent = new Intent(ChangePassActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), R.string.error_incorrect_data, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
