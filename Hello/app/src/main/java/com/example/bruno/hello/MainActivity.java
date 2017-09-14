package com.example.bruno.hello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private Button button;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text_view);
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.edit_text);

        button.setOnClickListener(this) ;
    }

    @Override
    public void onClick(View view) {
        String text = "Olá " + editText.getText();
        textView.setText(text);
    }
}
