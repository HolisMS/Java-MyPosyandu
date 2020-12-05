package satella.app.posyanduku.POSYANDU_PAGES.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import satella.app.posyanduku.R;
import satella.app.posyanduku.adapter.RecyclerviewAnakAdapter;
import satella.app.posyanduku.models.Anak;
import satella.app.posyanduku.models.Login;

public class RecyclerviewProfilAdapter {
    private Context mContext;
    private ProfilAdapter mProfilAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Login> logins, List<String> keys){
        mContext = context;
        mProfilAdapter = new ProfilAdapter(logins, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mProfilAdapter);
    }

    class ProfilItemView extends RecyclerView.ViewHolder{
        private TextView mId, mNama, mKetua;
        private TextView mNoTelp, mRw;
        private TextView mKel, mKec, mKota, mProvinsi;
        private String key;

        public ProfilItemView(@NonNull ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.item_posyandu, parent,false));

            mId = itemView.findViewById(R.id.tv_id_posyandu);
            mNama = itemView.findViewById(R.id.tv_nm_posyandu);
            mKetua = itemView.findViewById(R.id.tv_ketua);
            mNoTelp = itemView.findViewById(R.id.tv_noTel_posyandu);
            mRw = itemView.findViewById(R.id.tv_rw);
            mKel = itemView.findViewById(R.id.tv_kel);
            mKec = itemView.findViewById(R.id.tv_kec);
            mKota = itemView.findViewById(R.id.tv_kota);
            mProvinsi = itemView.findViewById(R.id.tv_provinsi);

        }

        public void bind(Login login, String key){
            mId.setText(login.getId_posyandu());
            mNama.setText(login.getNama_posyandu());
            mKetua.setText(login.getKetua());
            mNoTelp.setText(login.getNoTelp());
            mRw.setText(login.getRw());
            mKel.setText(login.getKelurahan());
            mKec.setText(login.getKecamatan());
            mKota.setText(login.getKota());
            mProvinsi.setText(login.getProvinsi());
            this.key = key;
        }
    }

    class ProfilAdapter extends RecyclerView.Adapter<ProfilItemView>{
        private List<Login> mLoginList;
        private List<String> mKey;

        public ProfilAdapter(List<Login> mLoginList, List<String> mKey) {
            this.mLoginList = mLoginList;
            this.mKey = mKey;
        }

        @NonNull
        @Override
        public ProfilItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ProfilItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ProfilItemView holder, int position) {
            holder.bind(mLoginList.get(position),mKey.get(position));
        }

        @Override
        public int getItemCount() {
            return mLoginList.size();
        }
    }
}
