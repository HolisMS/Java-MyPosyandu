package satella.app.posyanduku.POSYANDU_PAGES.profile_pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;

import satella.app.posyanduku.POSYANDU_PAGES.adapter.RecyclerviewProfilAdapter;
import satella.app.posyanduku.POSYANDU_PAGES.timbangan_pages.ScanBarcodeActivity;
import satella.app.posyanduku.R;
import satella.app.posyanduku.firebase_helper.DatabaseHelperLogin;
import satella.app.posyanduku.models.Login;

public class ProfilePosyanduActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_posyandu);

        if (getSupportActionBar() !=null){
            getSupportActionBar().setTitle("Profil posyandu");
        }

        mRecyclerView = findViewById(R.id.rv_posyandu);
        new DatabaseHelperLogin().readPosyandu(new DatabaseHelperLogin.DataStatus() {
            @Override
            public void DataIsLoad(List<Login> logins, List<String> keys) {
                new RecyclerviewProfilAdapter().setConfig(mRecyclerView, getApplicationContext(), logins, keys);
            }

            @Override
            public void DataIsInsert() {

            }

            @Override
            public void DataIsUpdate() {

            }

            @Override
            public void DataIsDelete() {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.update_data_posy:
                startActivity(new Intent(this, UpdatePosyanduActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
