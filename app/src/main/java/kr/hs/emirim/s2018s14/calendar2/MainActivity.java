package kr.hs.emirim.s2018s14.calendar2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalendarView calendar = (CalendarView) findViewById(R.id.calendar);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year,
                                            int month, int dayOfMonth) {
                Intent calendar_intent = new Intent(getApplicationContext(), WriteDairy.class);
                startActivity(calendar_intent);
                Toast.makeText(MainActivity.this, "" + year + "/" +
                        (month + 1) + "/" + dayOfMonth, 0).show();
            }
        });
    }


}
