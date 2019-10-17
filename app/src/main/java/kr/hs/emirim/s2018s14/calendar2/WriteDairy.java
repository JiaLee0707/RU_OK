package kr.hs.emirim.s2018s14.calendar2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WriteDairy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_dairy);

        View.OnClickListener save_listener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //DB에 저장
                Toast.makeText(WriteDairy.this, "저장되었습니다.", Toast.LENGTH_LONG).show();
                Intent cancel_intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(cancel_intent);
            }
        };

        View.OnClickListener cancel_listener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent cancel_intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(cancel_intent);
            }
        };

        Button save_btn = (Button) findViewById(R.id.save);
        save_btn.setOnClickListener(save_listener);

        Button cancel_btn = (Button) findViewById(R.id.cancel);
        cancel_btn.setOnClickListener(cancel_listener);
    }
}
