package satella.app.posyanduku.POSYANDU_PAGES.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import satella.app.posyanduku.POSYANDU_PAGES.models.Timbang;
import satella.app.posyanduku.R;

public class RecylerviewDataAdapter {
    private Context mContext;
    private PosyanduAdapter mPosyanduAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Timbang> timbangs, List<String> keys){
        mContext = context;
        mPosyanduAdapter = new PosyanduAdapter(timbangs, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mPosyanduAdapter);
    }

    class PosyanduItemView extends RecyclerView.ViewHolder{
        private TextView mNama, mTinggi, mBerat, mLingkar, mTanggal;
        private String key;

        public PosyanduItemView(@NonNull ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.item_timbang, parent,false));

            mNama = itemView.findViewById(R.id.tv_nama);
            mTinggi = itemView.findViewById(R.id.tv_tinggi);
            mBerat = itemView.findViewById(R.id.tv_berat);
            mLingkar = itemView.findViewById(R.id.tv_lingkar);
            mTanggal = itemView.findViewById(R.id.tv_tgl_timbang);

        }

        public void bind(Timbang timbang, String key){
            mNama.setText(timbang.getNama());
            mTinggi.setText(timbang.getTinggi_timbang());
            mBerat.setText(timbang.getBerat_badan());
            mLingkar.setText(timbang.getLinkar_badan());
            mTanggal.setText(timbang.getTgl_timbang());
            this.key = key;
        }

    }

    class PosyanduAdapter extends RecyclerView.Adapter<PosyanduItemView>{

        private List<Timbang> mTimbangList;
        private List<String> mKey;

        public PosyanduAdapter(List<Timbang> mTimbangList, List<String> mKey) {
            this.mTimbangList = mTimbangList;
            this.mKey = mKey;
        }

        @NonNull
        @Override
        public PosyanduItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PosyanduItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull PosyanduItemView holder, int position) {
            holder.bind(mTimbangList.get(position), mKey.get(position));
        }

        @Override
        public int getItemCount() {
            return mTimbangList.size();
        }
    }

}


