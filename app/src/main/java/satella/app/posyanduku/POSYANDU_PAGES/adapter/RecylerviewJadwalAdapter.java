package satella.app.posyanduku.POSYANDU_PAGES.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import satella.app.posyanduku.POSYANDU_PAGES.models.Jadwal;
import satella.app.posyanduku.R;

public class RecylerviewJadwalAdapter extends RecyclerView.Adapter<RecylerviewJadwalAdapter.ViewHolder> {
    private ArrayList<Jadwal> mListJadwal;
    private Context mContext;

    public RecylerviewJadwalAdapter(ArrayList<Jadwal> mListJadwal, Context mContext) {
        this.mListJadwal = mListJadwal;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwal,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String bulan = mListJadwal.get(position).getBulan();
        final String tanggal = mListJadwal.get(position).getTanggal();

        holder.tvBulan.setText(bulan);
        holder.tvTanggal.setText(tanggal);
    }

    @Override
    public int getItemCount() {
        return mListJadwal.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvBulan, tvTanggal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBulan = itemView.findViewById(R.id.tv_bulan);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal);
        }
    }
}
