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
import satella.app.posyanduku.models.Login;

public class DatabaseHelperLogin {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private List<Login> logins = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoad(List<Login> logins, List<String> keys);
        void DataIsInsert();
        void DataIsUpdate();
        void DataIsDelete();
    }

    public DatabaseHelperLogin() {
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("posyandu");
    }

    public void readPosyandu(final DataStatus dataStatus){
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                logins.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Login login = keyNode.getValue(Login.class);
                    logins.add(login);
                }
                dataStatus.DataIsLoad(logins, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void deletePosyandu(String key, final DataStatus dataStatus){
        mReference.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDelete();
                    }
                });
    }

}


