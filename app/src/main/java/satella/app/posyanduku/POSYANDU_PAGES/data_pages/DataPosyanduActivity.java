package satella.app.posyanduku.POSYANDU_PAGES.data_pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import satella.app.posyanduku.POSYANDU_PAGES.adapter.RecyclerviewProfilAdapter;
import satella.app.posyanduku.POSYANDU_PAGES.adapter.RecylerviewDataAdapter;
import satella.app.posyanduku.POSYANDU_PAGES.adapter.RecylerviewJadwalAdapter;
import satella.app.posyanduku.POSYANDU_PAGES.models.Jadwal;
import satella.app.posyanduku.POSYANDU_PAGES.models.Timbang;
import satella.app.posyanduku.R;
import satella.app.posyanduku.firebase_helper.DatabaseHelperPosyandu;

public class DataPosyanduActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_posyandu);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Data posyandu");
        }

        mRecyclerView = findViewById(R.id.rv_timbang);

        new DatabaseHelperPosyandu().readPos(new DatabaseHelperPosyandu.DataStatus() {
            @Override
            public void DataIsLoad(List<Timbang> timbangs, List<String> keys) {
                new RecylerviewDataAdapter().setConfig(mRecyclerView, getApplicationContext(),timbangs, keys);
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
}
