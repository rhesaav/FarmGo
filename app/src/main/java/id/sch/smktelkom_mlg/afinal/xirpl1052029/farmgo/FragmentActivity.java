package id.sch.smktelkom_mlg.afinal.xirpl1052029.farmgo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class FragmentActivity extends AppCompatActivity implements FragmentOwner.IListener, FragmentMember.IListener {

    private int mNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);


        initSelector();

        Button owner = findViewById(R.id.owner);
        owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toOwnerPage();
            }
        });

        mNo = 1;
        changePage(mNo);
    }


    @Override
    public void toOwnerPage() {
        changeFragment(1);
    }

    @Override
    public void toMemberPage() {
        changeFragment(2);
    }

    private void changePage(int no) {
        changeFragment(no);
    }

    private void initSelector() {
        findViewById(R.id.member).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePage(1);
            }
        });

        findViewById(R.id.member).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePage(2);
            }
        });
    }

    private void changeFragment(int no) {

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = null;

        switch (no) {
            case 1:
                fragment = new FragmentOwner();
                break;
            case 2:
                fragment = new FragmentMember();
                break;
        }
        fm.beginTransaction().replace(R.id.container, fragment).commitNow();
    }

}
