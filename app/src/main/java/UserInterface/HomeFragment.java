package UserInterface;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.service.quicksettings.Tile;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookapp.R;
import com.example.bookapp.models.ModelCategory;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    //    public ArrayList<ModelCategory> categoryArrayList;
//    public ViewPagerAdapter viewPagerAdapter;
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private View mview;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mview = inflater.inflate(R.layout.fragment_home, container, false);

        tabLayout = mview.findViewById(R.id.tablayout);
        viewPager2 = mview.findViewById(R.id.viewPager);

        TabLayoutAdapter adapter = new TabLayoutAdapter(getActivity());
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager2,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(TabLayout.Tab tab, int position) {
                        // Đặt tiêu đề cho từng tab
                        switch (position) {
                            case 0:
                                tab.setText("All");
                                break;
                            case 1:
                                tab.setText("Most viewed");
                                break;
                            case 2:
                                tab.setText("Most downloaded");
                                break;
                        }
                    }
                }).attach();
        return mview;
    }

//    private void setupViewPagerAdapter(ViewPager viewPager){
//
//    }

//    public class ViewPagerAdapter extends FragmentPagerAdapter{
//
//        private ArrayList<BookUserFragment> fragmentList = new ArrayList<>();
//        private ArrayList<String> fragmentTitleList = new ArrayList<>();
//        private Context context;
//
//        public ViewPagerAdapter(FragmentManager fm, int behavior, Context context) {
//            super(fm, behavior);
//
//            this.context = context;
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return fragmentList.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return fragmentList.size();
//        }
//
//        private void addFragment(BookUserFragment fragment, String title){
//            fragmentList.add(fragment);
//            fragmentTitleList.add(title);
//        }
//    }


}