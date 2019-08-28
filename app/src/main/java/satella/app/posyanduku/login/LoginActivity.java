package satella.app.posyanduku.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.tabs.TabLayout;

import satella.app.posyanduku.R;
import satella.app.posyanduku.adapter.LoginPagerAdapter;

public class LoginActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager vwPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        tabLayout = findViewById(R.id.indicator_login);
        vwPager = findViewById(R.id.pager_login);



        initView();
    }

    private void initView(){
        ViewPager viewPager = findViewById(R.id.pager_login);
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(vwPager,false);
    }

    private void setUpViewPager(ViewPager viewPager){
        LoginPagerAdapter loginPagerAdapter = new LoginPagerAdapter(getSupportFragmentManager());
        loginPagerAdapter.addFragment(new LoginAnakFragment());
        loginPagerAdapter.addFragment(new LoginPosyanduFragment());
        viewPager.setAdapter(loginPagerAdapter);
    }
}
