package com.example.bookapp.userInterface;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.bookapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewBookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewBookFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ViewBookFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ViewBookFragment newInstance(int bookId) {
        ViewBookFragment fragment = new ViewBookFragment();
        Bundle args = new Bundle();
        args.putInt("bookId", bookId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String bookId = getArguments().getString("bookId");
            // Sử dụng bookId để lấy và hiển thị thông tin sách
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_book, container, false);

        //Quay lại
        ImageButton backButton = view.findViewById(R.id.backButtonViewBook);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToTrangChu();
            }
        });

        //come danh gia
        LinearLayout DanhGiaButton = view.findViewById(R.id.button_danhgia);
        DanhGiaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToTrangDanhGia();
            }
        });

        //Doc sach
        Button readbutton = view.findViewById(R.id.readButton);
        readbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ReadingBookActivity.class);

                startActivity(intent);
            }
        });
        return view;
    }

    private void moveToTrangChu(){
        HomeUserFragment homeUserFragment = new HomeUserFragment();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.view_pager_trangchu, homeUserFragment)
                .addToBackStack(null)
                .commit();
    }

    private void moveToTrangDanhGia() {
        ReviewBookFragment reviewBookFragment = new ReviewBookFragment();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.view_pager_trangchu, reviewBookFragment)
                .addToBackStack(null)
                .commit();
    }
}