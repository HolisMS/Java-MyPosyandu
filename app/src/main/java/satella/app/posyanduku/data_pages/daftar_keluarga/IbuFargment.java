package satella.app.posyanduku.data_pages.daftar_keluarga;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import satella.app.posyanduku.R;
import satella.app.posyanduku.adapter.RecyclerviewIbuAdapter;
import satella.app.posyanduku.firebase_helper.DatabaseHelperIbu;
import satella.app.posyanduku.models.Ibu;

public class IbuFargment extends Fragment {

    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fargment_ibu,container,false);

        mRecyclerView = view.findViewById(R.id.rv_ibu);
        new DatabaseHelperIbu().readIbu(new DatabaseHelperIbu.DataStatusIbu() {
            @Override
            public void DataIsLoadIbu(List<Ibu> ibus, List<String> keys) {
                new RecyclerviewIbuAdapter().setConfig(mRecyclerView, getActivity(),ibus,keys);
            }

            @Override
            public void DataIsInsertIbu() {

            }

            @Override
            public void DataIsUpdateIbu() {

            }

            @Override
            public void DataIsDeleteIbu() {

            }
        });


        return view;
    }
}
