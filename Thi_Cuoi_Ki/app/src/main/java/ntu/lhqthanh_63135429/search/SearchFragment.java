package ntu.lhqthanh_63135429.search;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import ntu.lhqthanh_63135429.thi_cuoi_ki.R;

public class SearchFragment extends Fragment {
    TextView searchText;
    ImageButton buttonSearch;
    String textSearch = "";

    public SearchFragment() {
        // Required empty public constructor
    }
    public SearchFragment(String textSearch){
        this.textSearch = textSearch;
    }

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        searchText = view.findViewById(R.id.txtSearch);
        buttonSearch = view.findViewById(R.id.btnSearch);
        searchText.setText(textSearch);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("search", searchText.getText().toString());
                getActivity().startActivity(intent);
            }
        });
        return view;
    }


}