package ntu.lhqthanh_63135429.artist;

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

import ntu.lhqthanh_63135429.thi_cuoi_ki.R;

public class ArtistsFragment extends Fragment {
    List<Artist> list;
    RecyclerView viewArtist;
    String title;
    public ArtistsFragment(List<Artist> list, String title) {
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
        View view = inflater.inflate(R.layout.fragment_artists, container, false);
        TextView headerTitle = view.findViewById(R.id.titleArtist);
        headerTitle.setText(title);
        viewArtist = view.findViewById(R.id.listArtist);
        RecyclerView.LayoutManager linearHorizal = new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL, false);
        viewArtist.setLayoutManager(linearHorizal);
        ArtistAdapter adapter = new ArtistAdapter(list, getActivity());
        viewArtist.setAdapter(adapter);
        viewArtist.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                int spacing = 40;
                outRect.right = spacing;
            }
        });
        return view;
    }
}