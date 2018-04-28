package id.sch.smktelkom_mlg.afinal.xirpl1052029.farmgo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class IntroductionActivity extends AppCompatActivity {

    private int[] layouts;
    private ViewPager viewPager;
    private Intromanager intromanager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intromanager = new Intromanager(this);
        if (!intromanager.Check()) {
            intromanager.setFirst(false);
            Intent i = new Intent(IntroductionActivity.this, Main2Activity.class);
            startActivity(i);
            finish();
        }
        setContentView(R.layout.activity_introduction);

        layouts = new int[]{R.layout.activity_screen_1, R.layout.activity_screen_2,
                R.layout.activity_screen_3, R.layout.activity_screen_4,};
    }

    public class ViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = layoutInflater.inflate(layouts[position], container, false);
            container.addView(v);
            return v;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View v = (View) object;
            container.removeView(v);
        }
    }
}
