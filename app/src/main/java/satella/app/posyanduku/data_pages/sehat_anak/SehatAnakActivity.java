package satella.app.posyanduku.data_pages.sehat_anak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import satella.app.posyanduku.R;
import satella.app.posyanduku.adapter.SehatAdapter;
import satella.app.posyanduku.adapter.SehatRecyclerAdapter;
import satella.app.posyanduku.models.Sehat;

public class SehatAnakActivity extends AppCompatActivity {

    private RecyclerView rvSehat;
    private ArrayList<Sehat> list = new ArrayList<>();

    private TextView tvName, tvDesc;
    private ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sehat_anak);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Makanan sehat anak");
        }

        rvSehat = findViewById(R.id.rv_sehat);
        rvSehat.setHasFixedSize(true);

        tvName = findViewById(R.id.tv_item_nama);
        tvDesc = findViewById(R.id.tv_item_desc);

        list.addAll(SehatAdapter.getListData());
        showRecycler();

    }

    private void showRecycler(){
        rvSehat.setLayoutManager(new LinearLayoutManager(SehatAnakActivity.this));
        SehatRecyclerAdapter sehatRecyclerAdapter = new SehatRecyclerAdapter(list);
        rvSehat.setAdapter(sehatRecyclerAdapter);
    }
}
