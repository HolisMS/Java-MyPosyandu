package satella.app.posyanduku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import satella.app.posyanduku.about.AboutActivity;
import satella.app.posyanduku.adapter.SliderPagerAdapter;
import satella.app.posyanduku.data_pages.DataBalitaActivity;
import satella.app.posyanduku.jadwal_anak.JadwalAnakActivity;
import satella.app.posyanduku.login.LoginActivity;
import satella.app.posyanduku.models.Slider;
import satella.app.posyanduku.note.NoteActivity;
import satella.app.posyanduku.profile.ProfilAnakActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager sliderPager;
    private TabLayout tbLayout;
    private List<Slider> lstSlide;
    private FloatingActionButton fab;

    private ImageView imgData, imgAbout, imgProfil, imgCalender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        sliderPager = findViewById(R.id.slider_pager);
        tbLayout = findViewById(R.id.indicator);
        fab = findViewById(R.id.fab);
        imgAbout = findViewById(R.id.img_about);
        imgCalender = findViewById(R.id.img_calender);
        imgData = findViewById(R.id.img_data);
        imgProfil = findViewById(R.id.img_profil);

        fab.setOnClickListener(this);
        imgProfil.setOnClickListener(this);
        imgData.setOnClickListener(this);
        imgCalender.setOnClickListener(this);
        imgAbout.setOnClickListener(this);

        lstSlide = new ArrayList<>();
        lstSlide.add(new Slider(R.drawable.head_fam1,""));
        lstSlide.add(new Slider(R.drawable.head_fam2,""));
        lstSlide.add(new Slider(R.drawable.head_fam3,""));

        SliderPagerAdapter adapter = new SliderPagerAdapter(this, lstSlide);
        sliderPager.setAdapter(adapter);
        tbLayout.setupWithViewPager(sliderPager,false);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MainActivity.SliderTimer(),4000,5000);
    }

    @Override
    public void onClick(View v) {
        if (v == fab){
            Intent intent = new Intent(MainActivity.this, NoteActivity.class);
            startActivity(intent);
        }
        else if (v == imgData){
            Intent intent = new Intent(MainActivity.this, DataBalitaActivity.class);
            startActivity(intent);
        }
        else if (v == imgAbout){
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }
        else if (v == imgProfil){
            Intent intent = new Intent(MainActivity.this, ProfilAnakActivity.class);
            startActivity(intent);
        }
        else if (v == imgCalender){
            Intent intent = new Intent(MainActivity.this, JadwalAnakActivity.class);
            startActivity(intent);
        }
    }

    class SliderTimer extends TimerTask{
        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderPager.getCurrentItem() < lstSlide.size()-1){
                        sliderPager.setCurrentItem(sliderPager.getCurrentItem()+1);
                    }
                    else
                        sliderPager.setCurrentItem(0);
                }
            });
        }
    }

    public boolean doubleTapParam = false;
    @Override
    public void onBackPressed() {
        if (doubleTapParam){
            super.onBackPressed();
            return;
        }
        this.doubleTapParam = true;
        Toast.makeText(getApplicationContext(),"Ketuk sekali lagi untuk keluar",Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleTapParam = false;
            }
        },2000);
    }
}
