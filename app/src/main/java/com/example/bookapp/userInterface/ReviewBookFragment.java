package com.example.bookapp.userInterface;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.bookapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReviewBookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReviewBookFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReviewBookFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReviewBookFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReviewBookFragment newInstance(String param1, String param2) {
        ReviewBookFragment fragment = new ReviewBookFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review_book, container, false);

        ImageButton exit = view.findViewById(R.id.logoutDanhGiaBtn);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToViewBook();
            }
        });

        //Chọn sao
        ImageView star1 = view.findViewById(R.id.star1);
        ImageView star2 = view.findViewById(R.id.star2);
        ImageView star3 = view.findViewById(R.id.star3);
        ImageView star4 = view.findViewById(R.id.star4);
        ImageView star5 = view.findViewById(R.id.star5);

        ImageView[] stars = {star1, star2, star3, star4, star5};

        // Thêm sự kiện click cho từng ngôi sao
        for (int i = 0; i < stars.length; i++) {
            final int rating = i + 1;
            stars[i].setOnClickListener(v -> setRating(rating,stars));
        }

        return view;
    }

    private void moveToViewBook(){
        ViewBookFragment viewBookFragment = new ViewBookFragment();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.view_pager_trangchu, viewBookFragment)
                .addToBackStack(null)
                .commit();
    }

    //Trạng thái của các ngôi sao
    private void setRating(int rating, ImageView[] stars) {
        for (int i = 0; i < stars.length; i++) {
            if (i < rating) {
                stars[i].setImageResource(R.drawable.ic_star_filled); // Sao vàng
            } else {
                stars[i].setImageResource(R.drawable.ic_star_empty); // Sao trống
            }
        }
    }
}