package kr.hs.emirim.s2018s37.ru_ok;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    CalendarFragment calenderFragment;
    ChattingFragment chattingFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = findViewById(R.id.bottomNavigationView);

        calenderFragment = new CalendarFragment();
        chattingFragment = new ChattingFragment();

//        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, calenderFragment).commitAllowingStateLoss();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


//        Log.i("tag", "information message : main");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Log.i("tag", "information message");
            switch (item.getItemId()) {
                case R.id.action_calendar:
                    Log.i("tag", "information message : calendar");
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_main, calenderFragment).commit();/*프래그먼트 매니저가 프래그먼트를 담당한다!*/
                    break;
                case R.id.action_search:
                    Log.i("tag", "information message : search");
                    break;
                case R.id.action_chatting:
                    Log.i("tag", "information message : chatting");
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_main, chattingFragment).commit();
                    break;
                case R.id.action_people:
                    Log.i("tag", "information message : people");
                    return true;
            }
            return false;
        }
    };
}
