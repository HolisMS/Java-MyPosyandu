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

import satella.app.posyanduku.models.Bapak;
import satella.app.posyanduku.models.Ibu;

public class DatabaseHelperIbu {
    private FirebaseDatabase mDatabaseIbu;
    private DatabaseReference mReferenceIbu;
    private List<Ibu> ibus = new ArrayList<>();

    public interface DataStatusIbu{
        void DataIsLoadIbu(List<Ibu> ibus, List<String> keys);
        void DataIsInsertIbu();
        void DataIsUpdateIbu();
        void DataIsDeleteIbu();
    }

    public DatabaseHelperIbu() {
        mDatabaseIbu = FirebaseDatabase.getInstance();
        mReferenceIbu = mDatabaseIbu.getReference("ibu");
    }

    public void readIbu(final DataStatusIbu dataStatusIbu){
        mReferenceIbu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ibus.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Ibu ibu = keyNode.getValue(Ibu.class);
                    ibus.add(ibu);
                }
                dataStatusIbu.DataIsLoadIbu(ibus, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void deleteIbu(String key, final DataStatusIbu dataStatusIbu){
        mReferenceIbu.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatusIbu.DataIsDeleteIbu();
                    }
                });
    }

}
