package satella.app.posyanduku.about;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import satella.app.posyanduku.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        if (getSupportActionBar() !=null){
            getSupportActionBar().setTitle("About");
        }
    }
}
