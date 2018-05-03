package id.sch.smktelkom_mlg.afinal.xirpl1052029.farmgo;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMember extends Fragment {

    IListener mListener;


    public FragmentMember() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_member, container, false);

        Button proses_member = view.findViewById(R.id.proses_member);

        proses_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        if (context instanceof FragmentMember.IListener) {
            mListener = (FragmentMember.IListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement FragmentMember.IListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void addMember() {

    }

    interface IListener {
        void toOwnerPage();
    }

}
