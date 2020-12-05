package satella.app.posyanduku.jadwal_anak;

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

import satella.app.posyanduku.POSYANDU_PAGES.adapter.RecylerviewJadwalAdapter;
import satella.app.posyanduku.POSYANDU_PAGES.models.Jadwal;
import satella.app.posyanduku.R;

public class JadwalAnakActivity extends AppCompatActivity {
    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Jadwal> mListJadwal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_anak);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Jadwal posyandu");
        }

        rvView = findViewById(R.id.rv_listJadwal);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);
//
//        rvView = findViewById(R.id.rv_listJadwal);
//        rvView.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(this);
//        rvView.setLayoutManager(layoutManager);

        database = FirebaseDatabase.getInstance().getReference();
        database.child("jadwal").addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mListJadwal = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()){
                    Jadwal jadwal = noteDataSnapshot.getValue(Jadwal.class);
                    jadwal.getBulan(noteDataSnapshot.getKey());
                    mListJadwal.add(jadwal);
                }
                adapter = new RecylerviewJadwalAdapter(mListJadwal, getApplicationContext());
                rvView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails() + " " + databaseError.getMessage());
            }
        }));

    }
}
