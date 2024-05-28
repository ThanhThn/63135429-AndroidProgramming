package ntu.lhqthanh_63135429.search;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import ntu.lhqthanh_63135429.thi_cuoi_ki.MainActivity;
import ntu.lhqthanh_63135429.thi_cuoi_ki.R;

public class SearchFragment extends Fragment {
    TextView searchText;
    ImageButton buttonSearch, backButton;
    String textSearch = "";

    public SearchFragment() {
        // Required empty public constructor
    }
    public SearchFragment(String textSearch){
        this.textSearch = textSearch;
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
        backButton = view.findViewById(R.id.backSearchButton);
        searchText.setText(textSearch);
        if(getActivity() instanceof SearchActivity){
            backButton.setVisibility(View.VISIBLE);
        }
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("search", searchText.getText().toString());
                getActivity().startActivity(intent);
                if(!(getActivity() instanceof MainActivity)){
                    getActivity().finish();
                }
            }
        });
        return view;
    }


}