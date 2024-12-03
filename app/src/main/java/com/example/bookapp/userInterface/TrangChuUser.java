package com.example.bookapp.userInterface;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bookapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TrangChuUser extends AppCompatActivity{

    private FrameLayout viewPager2;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trang_chu_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewPager2 = findViewById(R.id.view_pager_trangchu);
        bottomNavigationView = findViewById(R.id.bottomNavUser);

        loadFrament(new HomeUserFragment());

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.home) {
                selectedFragment = new HomeUserFragment();
            } else if (item.getItemId() == R.id.library) {
                selectedFragment = new LibraryUserFragment();
            } else if (item.getItemId() == R.id.profile) {
                selectedFragment = new ProfileUserFragment();
            }
            if(selectedFragment != null) {
                loadFrament(selectedFragment);
                return true;
            }
            return false;
        });

    }

    private void loadFrament(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.view_pager_trangchu, fragment);
        transaction.commit();

    }

//    public void showViewBookFragment(int bookId){
//        ViewBookFragment viewBookFragment = ViewBookFragment.newInstance(bookId);
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.view_pager_trangchu, viewBookFragment)
//                .addToBackStack(null)
//                .commit();
//    }

//    @Override
//    public void onCategorySelected(String category) {
//        // Khi một chủ đề được chọn, thay thế fragment với BookListFragment
//        ItemBookTheoChuDeFragment ItemBookFragment = ItemBookTheoChuDeFragment.newInstance(category);
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.view_pager_trangchu, ItemBookFragment)
//                .addToBackStack(null)
//                .commit();
//    }
}