package android.anative.com.mockupapp.adapter;

import android.anative.com.mockupapp.R;
import android.anative.com.mockupapp.datbase.ProfileTable;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Neeraj VijayVargiya on 10/11/17.
 */

public class ProfileListadapter extends RecyclerView.Adapter<ProfileListadapter.MyViewHolder> {
    private Context context;
    private List<ProfileTable> profileList;
    private ProfileClickListener profileClickListener;

    public void setProfileClickListener(ProfileClickListener profileClickListener) {
        this.profileClickListener = profileClickListener;
    }

    public ProfileListadapter(Context context, List<ProfileTable> profileList) {
        this.context = context;
        this.profileList = profileList;
    }

    @Override
    public ProfileListadapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProfileListadapter.MyViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        if (profileList.size()==0){
            return 1;
        }
        return profileList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, isEmploy, designation, age;
        LinearLayout ln_main;

        public MyViewHolder(View itemView) {
            super(itemView);
            age = (TextView) itemView.findViewById(R.id.age);
            designation = (TextView) itemView.findViewById(R.id.designation);
            isEmploy = (TextView) itemView.findViewById(R.id.isEmploy);
            name = (TextView) itemView.findViewById(R.id.name);
            ln_main = (LinearLayout) itemView.findViewById(R.id.ln_main);
        }

        public void setData(final int position) {
            if (isEven(position)) {
                ln_main.setBackgroundColor(context.getResources().getColor(R.color.colorGray));
            } else {
                ln_main.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
            }
            if (position == 0) {
                age.setTypeface(age.getTypeface(), Typeface.BOLD);
                designation.setTypeface(designation.getTypeface(), Typeface.BOLD);
                isEmploy.setTypeface(isEmploy.getTypeface(), Typeface.BOLD);
                name.setTypeface(name.getTypeface(), Typeface.BOLD);
               // return;
            } else {
                age.setText(profileList.get(position).getAge());
                designation.setText(profileList.get(position).getDepartment());
                isEmploy.setText("Yes");
                name.setText(profileList.get(position).getUser_name());
            }
            ln_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (profileClickListener != null) {
                        profileClickListener.onProfileClick(profileList.get(position));
                    }
                }
            });
        }
    }

    private boolean isEven(int position) {
        if (position % 2 == 0) {
            return true;
        }
        return false;
    }

    public interface ProfileClickListener {
        void onProfileClick(ProfileTable profileTable);
    }
}
