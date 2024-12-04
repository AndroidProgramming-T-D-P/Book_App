package com.example.bookapp.userInterface;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bookapp.R;
import com.example.bookapp.models.Photo;
import com.example.bookapp.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewBookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewBookFragment extends Fragment {

    private static final String ARG_SACH = "sach";
    ImageView bookCover;
    TextView bookTitle;
    TextView author;
    int bookId = 0;
    Button btn_docSach;

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
    public static ViewBookFragment newInstance(Photo photo) {
        ViewBookFragment fragment = new ViewBookFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_SACH, photo);
        fragment.setArguments(args);
        return fragment;
    }

//    public static ViewBookFragment newInstance(int idbook) {
//        ViewBookFragment fragment = new ViewBookFragment();
//        Bundle args = new Bundle();
//        args.putSerializable(ARG_SACH, idbook);
//        fragment.setArguments(args);
//        return fragment;
//    }

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

        //ÁNH xạ
        bookCover = view.findViewById(R.id.bookCover);
        bookTitle = view.findViewById(R.id.bookTitle);
        author = view .findViewById(R.id.author);

        //Lấy thông tin sách từ bundle
        Photo sach = (Photo) getArguments().getSerializable(ARG_SACH);

        Utils utils = new Utils();
        bookTitle.setText(sach.getTitle());
        author.setText("Tác giả: " + sach.getAuthor());
        bookId = sach.getBook_id();
        Glide.with(getContext()).load(utils.BASE_URL + sach.getCover_image()).into(bookCover);

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
                intent.putExtra("book_id", bookId);
                startActivity(intent);
            }
        });
        return view;
    }

    private void moveToTrangChu(){
        getActivity().getSupportFragmentManager().popBackStack();
    }

    private void moveToTrangDanhGia() {
        ReviewBookFragment reviewBookFragment = new ReviewBookFragment();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.view_pager_trangchu, reviewBookFragment)
                .addToBackStack(null)
                .commit();
    }
}