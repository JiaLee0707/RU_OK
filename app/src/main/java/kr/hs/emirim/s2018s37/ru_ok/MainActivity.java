package kr.hs.emirim.s2018s37.ru_ok;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalendarView calendar = (CalendarView) findViewById(R.id.calendar);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @SuppressLint("WrongConstant")
            public void onSelectedDayChange(CalendarView view, int year,
                                            int month, int dayOfMonth) {
                Intent calendar_intent = new Intent(getApplicationContext(), WriteDairy.class);
                startActivity(calendar_intent);
                Toast.makeText(MainActivity.this, "" + year + "/" +
                        (month + 1) + "/" + dayOfMonth, 0).show();
            }
        });
    }

    public void chatting(View view) {
        ImageButton chatting = (ImageButton) findViewById(R.id.imageButton);

        chatting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chatting = new Intent(MainActivity.this, ChattingActivity.class);
                startActivity(chatting);
            }
        });
    }
}
