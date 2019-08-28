package satella.app.posyanduku.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import satella.app.posyanduku.R;
import satella.app.posyanduku.data_pages.data_orang_tua.UpdateBapakActivity;
import satella.app.posyanduku.data_pages.data_orang_tua.UpdateIbuActivity;
import satella.app.posyanduku.models.Ibu;

public class RecyclerviewIbuAdapter {
    private Context mContext;
    private IbuAdapter mIbuAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Ibu> ibus, List<String> keys){
        mContext = context;
        mIbuAdapter = new IbuAdapter(ibus, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mIbuAdapter);
    }


    class IbuItemView extends RecyclerView.ViewHolder{
        private TextView  mNmIbu, mPekerjaan;
        private TextView mpendidikan, mAlamat;
        private TextView mAgama, mTmptLahir, mTglLahir;
        private String key;

        public IbuItemView(@NonNull ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.item_ibu,parent,false));

            mNmIbu = itemView.findViewById(R.id.tv_nm_ibu);
            mPekerjaan = itemView.findViewById(R.id.tv_kerja_ibu);
            mpendidikan = itemView.findViewById(R.id.tv_pend_ibu);
            mAlamat = itemView.findViewById(R.id.tv_almt_ibu);
            mAgama = itemView.findViewById(R.id.tv_agama_ibu);
            mTmptLahir = itemView.findViewById(R.id.tv_tmp_lahir_ibu);
            mTglLahir = itemView.findViewById(R.id.tv_tgl_ibu);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, UpdateIbuActivity.class);
                    intent.putExtra("key", key);
                    intent.putExtra("nma", mNmIbu.getText().toString());
                    intent.putExtra("pend",mpendidikan.getText().toString());
                    intent.putExtra("kerja", mPekerjaan.getText().toString());
                    intent.putExtra("alamat", mAlamat.getText().toString());
                    intent.putExtra("agama", mAgama.getText().toString());
                    intent.putExtra("tmpLahir", mTmptLahir.getText().toString());
                    intent.putExtra("tglLahir", mTglLahir.getText().toString());

                    mContext.startActivity(intent);
                }
            });

        }

        public void bind(Ibu ibu, String key){

            mNmIbu.setText(ibu.getNama_ibu());
            mPekerjaan.setText(ibu.getPekerjaan());
            mpendidikan.setText(ibu.getPendidikan());
            mAlamat.setText(ibu.getAlamat());
            mAgama.setText(ibu.getAgama());
            mTmptLahir.setText(ibu.getTempat_lahir());
            mTglLahir.setText(ibu.getTanggal_lahir());
            this.key = key;
        }

    }

    class IbuAdapter extends RecyclerView.Adapter<IbuItemView>{
        private List<Ibu> mIbuList;
        private List<String> mKeys;

        public IbuAdapter(List<Ibu> mIbuList, List<String> mKeys) {
            this.mIbuList = mIbuList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public IbuItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new IbuItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull IbuItemView holder, int position) {
            holder.bind(mIbuList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mIbuList.size();
        }
    }

}
