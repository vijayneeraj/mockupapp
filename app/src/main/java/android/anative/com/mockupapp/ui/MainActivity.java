package android.anative.com.mockupapp.ui;

import android.anative.com.mockupapp.R;
import android.anative.com.mockupapp.ui.fragments.ProfileDetail;
import android.anative.com.mockupapp.ui.fragments.ProfileInfoFragment;
import android.anative.com.mockupapp.ui.fragments.ProfileListFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neeraj VijayVargiya on 10/11/17.
 */

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        findViews();
    }

    private void findViews() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        setupViewPager();
    }

    private void setupViewPager() {
        viewPagerAdapter.addFragment(ProfileInfoFragment.newInstance(), "Profile Info");
        viewPagerAdapter.addFragment(ProfileListFragment.newInstance(), "Profile List");
        viewPagerAdapter.addFragment(ProfileDetail.newInstance(), "Profile Detail");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        List<String> textList = new ArrayList<>();
        ArrayList<Fragment> fragList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
            fragList.clear();
        }

        public void addFragment(Fragment fragment
                , @NonNull String tabText) {
            if (fragment != null) {
                fragList.add(fragment);
                textList.add(tabText);
            }
        }

        @Override
        public Fragment getItem(int position) {
            return fragList.get(position);
        }

        @Override
        public int getCount() {
            return fragList.size();
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return textList.get(position);
        }
    }


}
