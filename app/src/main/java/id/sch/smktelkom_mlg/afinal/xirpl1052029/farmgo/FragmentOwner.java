package id.sch.smktelkom_mlg.afinal.xirpl1052029.farmgo;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOwner extends Fragment {

    IListener oListener;

    DatabaseReference databaseOwner;
    private EditText nama, no_telp, user, passwordo, nama_p, alamato;
    private Button proses;

    public FragmentOwner() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment_owner, container, false);

        nama = view.findViewById(R.id.nama_owner);
        no_telp = view.findViewById(R.id.no_telp_owner);
        user = view.findViewById(R.id.user_owner);
        passwordo = view.findViewById(R.id.pass_owner);
        nama_p = view.findViewById(R.id.namaperusahaan);
        alamato = view.findViewById(R.id.alamat_perusahaan);
        proses = view.findViewById(R.id.proses_owner);
        databaseOwner = FirebaseDatabase.getInstance().getReference("Owner");

        proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOwner();
            }
        });
        return view;

    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        if (context instanceof FragmentOwner.IListener) {
            oListener = (FragmentOwner.IListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement FragmentOwnerIListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        oListener = null;
    }

    private void addOwner() {
        String name = nama.getText().toString().trim();
        String telp = no_telp.getText().toString().trim();
        String username = user.getText().toString().trim();
        String password = passwordo.getText().toString().trim();
        String namaperusahaan = nama_p.getText().toString().trim();
        String alamat = alamato.getText().toString().trim();


        String uid = databaseOwner.push().getKey();

        Owner owner = new Owner(uid, name, telp, username, password, namaperusahaan, alamat);

        databaseOwner.child(uid).setValue(owner);


    }

    interface IListener {
        void toMemberPage();
    }

}
