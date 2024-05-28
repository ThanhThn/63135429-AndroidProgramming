package ntu.lhqthanh_63135429.Song;

import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ntu.lhqthanh_63135429.thi_cuoi_ki.R;

public class SongFragment extends Fragment {
    RecyclerView listView;
    TextView title;
    ArrayList<Song> listSong;
    String nameTitle;
    RecyclerView.LayoutManager layout;
    public SongFragment (ArrayList<Song> listSong, String nameTitle, RecyclerView.LayoutManager layout){
        this.listSong = listSong;
        this.nameTitle = nameTitle;
        this.layout = layout;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_song, container, false);
        title = view.findViewById(R.id.titleSong);
        title.setText(nameTitle);
        listView = view.findViewById(R.id.listSong);
        SongAdapter adapter = new SongAdapter(listSong, getActivity());
        listView.setLayoutManager(layout);
        listView.setAdapter(adapter);
        if(layout instanceof GridLayoutManager){
            listView.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                    int position = parent.getChildAdapterPosition(view);
                    int column = position % 2;
                    int spacing = 40;
                    outRect.left = column * spacing / 2;
                    outRect.right = spacing - (column + 1) * spacing / 2;
                }
            });
        }
        return view;
    }

}