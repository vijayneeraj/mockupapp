package android.anative.com.mockupapp.ui.fragments;

import android.anative.com.mockupapp.R;
import android.anative.com.mockupapp.datbase.ProfileTable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Neeraj VijayVargiya on 10/11/17.
 */

public class ProfileDetail extends BaseFragment {
    View view;
    TextView txt_name, txt_designation, txt_gender, txt_pets, txt_profile_info, txt_dob;
    LinearLayout lin_qualification, lin_color;
    private ProfileTable profileTable;

    public void setProfileTable(ProfileTable profileTable) {
        this.profileTable = profileTable;
       // Toast.makeText(getActivity(), "got new profile detail", Toast.LENGTH_SHORT).show();
        setData();
    }

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
        lin_qualification=(LinearLayout)view.findViewById(R.id.lin_qualification);
        lin_color=(LinearLayout)view.findViewById(R.id.lin_color);
        txt_name = (TextView) view.findViewById(R.id.txt_name);
        txt_designation = (TextView) view.findViewById(R.id.txt_designation);
        txt_gender = (TextView) view.findViewById(R.id.txt_gender);
        txt_pets = (TextView) view.findViewById(R.id.txt_pets);
        txt_profile_info = (TextView) view.findViewById(R.id.txt_profile_info);
        txt_dob = (TextView) view.findViewById(R.id.txt_dob);
        setData();
    }
    private void setData(){
        if (profileTable!=null){
            txt_name.setText(profileTable.getUser_name());
            txt_designation.setText(profileTable.getDepartment());
            txt_gender.setText(profileTable.getGender());
            txt_pets.setText(profileTable.getPets_count());
            txt_profile_info.setText(profileTable.getAbout());
            txt_dob.setText(profileTable.getDate());
            lin_color.setBackgroundColor(profileTable.getColor());
            List<String> qualif=new Gson().fromJson(profileTable.getQualification(),new TypeToken<List<String>>(){}.getType());
            if (qualif!=null && qualif.size()>0){
                for (int i=0; i<qualif.size();i++){
                    TextView textView=new TextView(getActivity());
                    textView.setTextColor(getActivity().getResources().getColor(R.color.colorBlack));
                    textView.setText(qualif.get(i));
                    lin_qualification.addView(textView);
                }
            }
        }

    }
}
