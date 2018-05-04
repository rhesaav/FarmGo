package id.sch.smktelkom_mlg.afinal.xirpl1052029.farmgo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by haniivn on 5/1/2018.
 */

public class TampilData extends AppCompatActivity {
    /*protected Context context;
    Data data;
    List<Data> dataList = new ArrayList<>();
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DataAdapter adapter;
    private DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data);
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
    }*/
    String UID;
    ArrayList<Data> datas = new ArrayList<Data>();
    private TableLayout tableLayout;
    private TextView eTanggal, eUmur, eJayam, eJmati, eJtelur, eBtelur, eBpakan;
    private ArrayList<String> mData = new ArrayList<String>();
    private Firebase mRef;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data);

        this.UID = getIntent().getExtras().getString("uid");
        eTanggal = findViewById(R.id.etanggal);
        eUmur = findViewById(R.id.umur);
        eJayam = findViewById(R.id.jayam);
        eJmati = findViewById(R.id.jmati);
        eJtelur = findViewById(R.id.jtelur);
        eBtelur = findViewById(R.id.btelur);
        eBpakan = findViewById(R.id.bpakan);

        getData();
    }

    private void getData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = firebaseDatabase.getReference("Cabang1/" + this.UID);
        dbRef.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    String key = dataSnapshot1.getKey();
                    Data data = new Data();
                    data.uid = dataSnapshot.child(key).child("uid").getValue().toString();
                    data.etanggal = dataSnapshot.child(key).child("etanggal").getValue().toString();
                    data.umur = dataSnapshot.child(key).child("umur").getValue().toString();
                    data.jtelur = dataSnapshot.child(key).child("jtelur").getValue().toString();
                    data.jmati = dataSnapshot.child(key).child("jmati").getValue().toString();
                    data.jayam = dataSnapshot.child(key).child("jayam").getValue().toString();
                    data.btelur = dataSnapshot.child(key).child("btelur").getValue().toString();
                    data.bmakan = dataSnapshot.child(key).child("bmakan").getValue().toString();


                    datas.add(data);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}

