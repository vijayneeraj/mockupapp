package android.anative.com.mockupapp.ui.fragments;

import android.anative.com.mockupapp.R;
import android.anative.com.mockupapp.adapter.ProfileListadapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Neeraj VijayVargiya on 10/11/17.
 */

public class ProfileListFragment extends BaseFragment {
    View view;
    RecyclerView tableRv;
    ProfileListadapter profileListadapter;

    public static ProfileListFragment newInstance() {

        Bundle args = new Bundle();

        ProfileListFragment fragment = new ProfileListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.profile_list, container, false);
        }
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        tableRv = (RecyclerView) view.findViewById(R.id.tableRv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        tableRv.setLayoutManager(layoutManager);
        profileListadapter = new ProfileListadapter(getActivity());
        tableRv.setAdapter(profileListadapter);
    }
}
