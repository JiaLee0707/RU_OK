package kr.hs.emirim.s2018s37.ru_ok;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;
    private CalendarFragment calenderFragment;
    private ChattingFragment chattingFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        fragment();
    }

    private void fragment() {
        calenderFragment = new CalendarFragment();//(CalendarFragment) getSupportFragmentManager().findFragmentById(R.id.content_main);
        chattingFragment = new ChattingFragment();

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_main, calenderFragment).commitAllowingStateLoss();
//        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, calenderFragment).commitAllowingStateLoss();
//        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, calenderFragment).commit();


//        Log.i("tag", "information message : main");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Log.i("tag", "information message");
            switch (item.getItemId()) {
                case R.id.action_calendar:
                    Log.i("tag", "information message : calendar");
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_main, calenderFragment).commit();
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
