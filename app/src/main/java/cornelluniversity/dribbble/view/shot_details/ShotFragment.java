package cornelluniversity.dribbble.view.shot_details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.reflect.TypeToken;

import cornelluniversity.dribbble.R;
import cornelluniversity.dribbble.model.Shot;
import cornelluniversity.dribbble.utils.ModelUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ruihao on 8/16/17.
 */

public class ShotFragment extends Fragment {

    public static final String KEY_SHOT = "shot";

    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    public static ShotFragment newInstance(Bundle args) {
        ShotFragment shotFragment = new ShotFragment();
        shotFragment.setArguments(args);
        return shotFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Shot shot = ModelUtils.toObject(getArguments().getString(KEY_SHOT), new TypeToken<Shot>(){});
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ShotAdapter(shot));
    }
}
