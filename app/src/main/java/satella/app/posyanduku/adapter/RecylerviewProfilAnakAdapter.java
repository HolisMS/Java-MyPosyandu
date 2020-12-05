package satella.app.posyanduku.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import satella.app.posyanduku.R;
import satella.app.posyanduku.models.Anak;
import satella.app.posyanduku.profile.DataProfileActivity;

public class RecylerviewProfilAnakAdapter extends RecyclerView.Adapter<RecylerviewProfilAnakAdapter.ViewHolder> {
    private ArrayList<Anak> listAnak;
    private Context mContext;

    public RecylerviewProfilAnakAdapter(ArrayList<Anak> listAnak, Context mContext) {
        this.listAnak = listAnak;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_anak,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final String nikAnak = listAnak.get(position).getNoKK();
        final String namaAnak = listAnak.get(position).getNamaLengkap();
        final String tanggalLahir = listAnak.get(position).getTglLahir();

        holder.tv_nikAnak.setText(nikAnak);
        holder.tv_namaAnak.setText(namaAnak);
        holder.tv_tanggalLahir.setText(tanggalLahir);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(DataProfileActivity.getActIntent((Activity) mContext).putExtra("anak",(Serializable)listAnak.get(position)));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listAnak.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_nikAnak,tv_namaAnak,tv_tanggalLahir;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nikAnak = itemView.findViewById(R.id.tv_nikAnak);
            tv_namaAnak = itemView.findViewById(R.id.tv_namaAnak);
            tv_tanggalLahir = itemView.findViewById(R.id.tv_tanggalLahir);

        }
    }
}
