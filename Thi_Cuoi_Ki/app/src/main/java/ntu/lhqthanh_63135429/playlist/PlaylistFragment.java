package ntu.lhqthanh_63135429.playlist;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ntu.lhqthanh_63135429.artist.Artist;
import ntu.lhqthanh_63135429.thi_cuoi_ki.R;

public class PlaylistFragment extends Fragment {

    List<Playlist> list;
    RecyclerView recyclerList;
    String title;

    public PlaylistFragment(List<Playlist> list, String title) {
        this.list = list;
        this.title = title;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        TextView titlePlaylist = view.findViewById(R.id.titlePlaylist);
        titlePlaylist.setText(title);
        recyclerList = view.findViewById(R.id.listPlaylist);
        PlaylistAdapter adapter = new PlaylistAdapter(list, getActivity());
        RecyclerView.LayoutManager linearHorizal = new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL, false);
        recyclerList.setLayoutManager(linearHorizal);
        recyclerList.setAdapter(adapter);
        recyclerList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                int spacing = 40;
                outRect.right = spacing;
            }
        });
        return view;
    }
}