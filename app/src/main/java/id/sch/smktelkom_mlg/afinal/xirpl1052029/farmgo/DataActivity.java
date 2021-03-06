package id.sch.smktelkom_mlg.afinal.xirpl1052029.farmgo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TableLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity {
    protected Context context;
    Data data;
    List<Data> dataList = new ArrayList<>();
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DataAdapter adapter;
    private TableLayout tableLayout;
    private DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        recyclerView = findViewById(R.id.rv_data);
        adapter = new DataAdapter();
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        loadData();
    }

    private void loadData() {
        data = new Data();
        mData = FirebaseDatabase.getInstance().getReference("Cabang1");
        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Data data = snapshot.getValue(Data.class);
                    dataList.add(data);
                    Log.d("hasil", data.toString());
                }
                adapter.addAll(dataList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
