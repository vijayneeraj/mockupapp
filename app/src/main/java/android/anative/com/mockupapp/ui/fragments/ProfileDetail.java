package android.anative.com.mockupapp.ui.fragments;

import android.anative.com.mockupapp.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Neeraj VijayVargiya on 10/11/17.
 */

public class ProfileDetail extends BaseFragment {
    View view;
    TextView txt_name, txt_designation, txt_gender, txt_pets, txt_profile_info, txt_dob;
    LinearLayout lin_qualification, lin_color;

    public static ProfileDetail newInstance() {

        Bundle args = new Bundle();

        ProfileDetail fragment = new ProfileDetail();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.profile_detail, container, false);
        }
        initViews(view);
        return view;
    }

    private void initViews(View view
    ) {
        txt_name = (TextView) view.findViewById(R.id.txt_name);
        txt_designation = (TextView) view.findViewById(R.id.txt_designation);
        txt_gender = (TextView) view.findViewById(R.id.txt_gender);
        txt_pets = (TextView) view.findViewById(R.id.txt_pets);
        txt_profile_info = (TextView) view.findViewById(R.id.txt_profile_info);
        txt_dob = (TextView) view.findViewById(R.id.txt_dob);
    }
}
