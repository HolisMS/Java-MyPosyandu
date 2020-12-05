package satella.app.posyanduku.POSYANDU_PAGES;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import satella.app.posyanduku.POSYANDU_PAGES.data_pages.DataPosyanduActivity;
import satella.app.posyanduku.POSYANDU_PAGES.jadwal_pages.JadwalPosyanduActivity;
import satella.app.posyanduku.POSYANDU_PAGES.profile_pages.ProfilePosyanduActivity;
import satella.app.posyanduku.POSYANDU_PAGES.timbangan_pages.TimbanganActivity;
import satella.app.posyanduku.R;
import satella.app.posyanduku.adapter.SliderPagerAdapter;
import satella.app.posyanduku.models.Slider;

public class PosyanduMainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgData, imgProfil, imgTimbang, imgJadwal;
    private ViewPager sliderPager;
    private TabLayout tbLayout;
    private List<Slider> lstSlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_posyandu_main);

        sliderPager = findViewById(R.id.slider_pager_posy);
        tbLayout = findViewById(R.id.indicator_posy);
        imgData = findViewById(R.id.img_data_posyandu);
        imgProfil = findViewById(R.id.img_profil_posyandu);
        imgTimbang = findViewById(R.id.img_timbang);
        imgJadwal = findViewById(R.id.img_jadwal);

        imgData.setOnClickListener(this);
        imgProfil.setOnClickListener(this);
        imgTimbang.setOnClickListener(this);
        imgJadwal.setOnClickListener(this);

        lstSlide = new ArrayList<>();

        lstSlide.add(new Slider(R.drawable.posy_2,""));
        lstSlide.add(new Slider(R.drawable.posy_3,""));
        lstSlide.add(new Slider(R.drawable.posy_4,""));

        SliderPagerAdapter adapter = new SliderPagerAdapter(this, lstSlide);
        sliderPager.setAdapter(adapter);
        tbLayout.setupWithViewPager(sliderPager,false);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new PosyanduMainActivity.SliderTimer(),4000,5000);
    }

    @Override
    public void onClick(View v) {
        if (v == imgData){
            Intent intent = new Intent(getApplicationContext(), DataPosyanduActivity.class);
            startActivity(intent);
        }
        else if (v == imgProfil){
            Intent intent = new Intent(getApplicationContext(), ProfilePosyanduActivity.class);
            startActivity(intent);
        }
        else if (v == imgTimbang){
            Intent intent = new Intent(getApplicationContext(), TimbanganActivity.class);
            startActivity(intent);
        }
        else if (v == imgJadwal){
            Intent intent = new Intent(getApplicationContext(), JadwalPosyanduActivity.class);
            startActivity(intent);
        }
    }

    class SliderTimer extends TimerTask {
        @Override
        public void run() {
            PosyanduMainActivity.this.runOnUiThread(new Runnable() {
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
