package UserInterface;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabLayoutAdapter extends FragmentStateAdapter {


    public TabLayoutAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new BookUserFragment();
            case 1:
                return new BookUserFragment();
            case 2:
                return new BookUserFragment();
            default:
                return new BookUserFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
