package satella.app.posyanduku.firebase_helper;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import satella.app.posyanduku.POSYANDU_PAGES.models.Timbang;

public class DatabaseHelperPosyandu {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private List<Timbang> timbangs = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoad(List<Timbang> timbangs, List<String> keys);
        void DataIsInsert();
        void DataIsUpdate();
        void DataIsDelete();
    }

    public DatabaseHelperPosyandu() {
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("timbang");
    }

    public void readPos(final DatabaseHelperPosyandu.DataStatus dataStatus){
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                timbangs.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Timbang timbang= keyNode.getValue(Timbang.class);
                    timbangs.add(timbang);
                }
                dataStatus.DataIsLoad(timbangs, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void deletePosyandu(String key, final DatabaseHelperLogin.DataStatus dataStatus){
        mReference.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDelete();
                    }
                });
    }
}
