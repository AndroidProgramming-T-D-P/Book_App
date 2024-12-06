package com.example.bookapp.userInterface;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bookapp.R;
import com.example.bookapp.Service.ApiService;
import com.example.bookapp.Service.RetrofitClient;
import com.example.bookapp.utils.Utils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReviewBookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReviewBookFragment extends Fragment {

    ApiService apiService;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ImageView imageView;
    TextView title, author;
    AppCompatButton btnDanhGia;
    private int dem = 0;
    EditText comment;

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

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review_book, container, false);

        SharedPreferences preferences = requireActivity().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
        int user_id = preferences.getInt("user_id", -1);

        imageView = view.findViewById(R.id.ivBookCover);
        title = view.findViewById(R.id.tvBookName);
        author = view.findViewById(R.id.tvAuthor);
        btnDanhGia = view.findViewById(R.id.btnSubmit);
        comment = view.findViewById(R.id.etComment);

        apiService = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiService.class);
        Bundle args = getArguments();
        int  book_id = args.getInt("BOOK_ID");
        if(args != null){
            compositeDisposable.add(apiService.getSachById(book_id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            photoModel -> {
                                if(photoModel.isSuccess()) {
                                    title.setText(photoModel.getResult().get(0).getTitle());
                                    author.setText("Tác giả: "+photoModel.getResult().get(0).getAuthor());
                                    Glide.with(getContext()).load(Utils.BASE_URL + photoModel.getResult().get(0).getCover_image()).into(imageView);
                                }
                            }
                    ));
        }



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
            stars[i].setOnClickListener(v -> {
                setRating(rating,stars);
                dem = rating;
            });
        }

        btnDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cmt = comment.getText().toString().trim();
                if(TextUtils.isEmpty(cmt)){
                    Toast.makeText(getContext(), "Bạn chưa nhập bình luận", Toast.LENGTH_SHORT).show();
                } else {
                    compositeDisposable.add(apiService.ThemDanhGia(book_id, user_id, dem, comment.getText().toString().trim())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    danhGiaModel -> {
                                        if(danhGiaModel.isSuccess()){
                                            Toast.makeText(getContext(), danhGiaModel.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            Toast.makeText(getContext(), danhGiaModel.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }, throwable -> {
                                        //Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                            ));
                }
            }
        });


        return view;
    }

    private void moveToViewBook(){
        getActivity().getSupportFragmentManager().popBackStack();
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