package ntu.lhqthanh_63135429;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ntu.lhqthanh_63135429.new_released.NewReleasedFragment;
import ntu.lhqthanh_63135429.slider.SilderFragment;
import ntu.lhqthanh_63135429.thi_cuoi_ki.AdapterRecycleView;
import ntu.lhqthanh_63135429.thi_cuoi_ki.R;

public class HomeFragment extends Fragment {
    List<Fragment> fragmentListHome;
    AdapterRecycleView adapterHome;
    RecyclerView recyclerView;
    FragmentManager fragmentManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentListHome = new ArrayList<>();
        fragmentListHome.add(new SilderFragment());
        fragmentListHome.add(new NewReleasedFragment());
        fragmentListHome.add(new Fragment());

        recyclerView = view.findViewById(R.id.HomeRecyclerView);
        RecyclerView.LayoutManager linear = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linear);

        adapterHome = new AdapterRecycleView(fragmentListHome, getActivity(), fragmentManager);
        recyclerView.setAdapter(adapterHome);

        return view;
    }
}