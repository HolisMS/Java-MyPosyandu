package satella.app.posyanduku.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import satella.app.posyanduku.R;
import satella.app.posyanduku.models.Sehat;

public class SehatRecyclerAdapter extends RecyclerView.Adapter<SehatRecyclerAdapter.SehatViewHolder> {

    private ArrayList<Sehat> listSehat;

    public SehatRecyclerAdapter(ArrayList<Sehat> listSehat) {
        this.listSehat = listSehat;
    }

    @NonNull
    @Override
    public SehatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sehat,parent,false);
        return new SehatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SehatViewHolder holder, int position) {
        Sehat sehat = listSehat.get(position);

        Glide.with(holder.itemView.getContext())
                .load(sehat.getPhoto())
                .apply(new RequestOptions().centerInside())
                .into(holder.imgPhoto);

        holder.tvName.setText(sehat.getNama());
        holder.tvDec.setText(sehat.getDesc());
    }

    @Override
    public int getItemCount() {
        return listSehat.size();
    }

    public class SehatViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgPhoto;
        private TextView tvName, tvDec;

        public SehatViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_photo);
            tvName = itemView.findViewById(R.id.tv_item_nama);
            tvDec = itemView.findViewById(R.id.tv_item_desc);
        }
    }
}
