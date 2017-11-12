package android.anative.com.mockupapp.ui;

import android.anative.com.mockupapp.R;
import android.anative.com.mockupapp.datbase.ProfileTable;
import android.anative.com.mockupapp.ui.fragments.ProfileDetail;
import android.anative.com.mockupapp.ui.fragments.ProfileInfoFragment;
import android.anative.com.mockupapp.ui.fragments.ProfileListFragment;
import android.content.SharedPreferences;
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
import java.util.logging.Handler;

/**
 * Created by Neeraj VijayVargiya on 10/11/17.
 */

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    ViewPager viewPager;
    TabLayout tabLayout;
    ViewPagerAdapter viewPagerAdapter;
    List<ProfileTable> profileList;
    ProfileTable profileTable;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (profileList.size() > 0) {
                profileTable = profileList.get(0);
                if (viewPagerAdapter.getItem(viewPager.getCurrentItem()) instanceof ProfileDetail) {
                    ProfileDetail profileDetail = (ProfileDetail) viewPagerAdapter.getItem(viewPager.getCurrentItem());
                    if (profileDetail != null) {
                        profileDetail.setProfileTable(profileTable);
                    }
                }
            }
        }
    };
    Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            if (profileList.size() > 0) {
                if (viewPagerAdapter.getItem(viewPager.getCurrentItem()) instanceof ProfileDetail) {
                    ProfileDetail profileDetail = (ProfileDetail) viewPagerAdapter.getItem(viewPager.getCurrentItem());
                    if (profileDetail != null) {
                        profileDetail.setProfileTable(profileTable);
                    }
                }
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        findViews();
    }

    private void findViews() {
        if (getPrefs() == 0) {
            // do manual entry;
            ProfileTable profileTable = new ProfileTable();
            profileTable.set_id(System.currentTimeMillis());
            profileTable.setUser_name("Name");
            profileTable.setDepartment("designation");
            profileTable.setGender("gender");
            profileTable.setQualification("Qualification");
            profileTable.setColor(0);
            profileTable.setPets_count("asdf");
            profileTable.setAbout("sdca");
            profileTable.setDate("Asds");
            profileTable.setAge("sad");
            profileTable.save();
            saeToPrefs(1);
        }
        profileList = new ArrayList<>();
        profileList = ProfileTable.getPostComments();
        android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(runnable, 400);
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
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    public void setViewPagerAtDetail(ProfileTable profileDetail) {
        viewPager.setCurrentItem(2);
        this.profileTable = profileDetail;
        android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(runnable2, 400);
    }

    @Override
    public void onPageSelected(int position) {
        if (viewPagerAdapter.getItem(position) instanceof ProfileListFragment) {
            ProfileListFragment profileListFragment = (ProfileListFragment) viewPagerAdapter.getItem(position);
            if (profileListFragment != null) {
                profileListFragment.setProfilesList(profileList);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

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
    public void newEntry(){
        profileList.clear();
        profileList=ProfileTable.getPostComments();
    }
    public void saeToPrefs(int value) {
        SharedPreferences.Editor sharedPreferences = getSharedPreferences("prefs_db", MODE_PRIVATE).edit();
        sharedPreferences.putInt("key", value);
        sharedPreferences.commit();
    }

    public int getPrefs() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs_db", MODE_PRIVATE);
        return sharedPreferences.getInt("key", 0);
    }

}
