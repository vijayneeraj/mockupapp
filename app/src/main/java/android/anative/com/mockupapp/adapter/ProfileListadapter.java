package android.anative.com.mockupapp.adapter;

import android.anative.com.mockupapp.R;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Neeraj VijayVargiya on 10/11/17.
 */

public class ProfileListadapter extends RecyclerView.Adapter<ProfileListadapter.MyViewHolder> {
    private Context context;

    public ProfileListadapter(Context context) {
        this.context = context;
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
        return 5;
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

        public void setData(int position) {
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
                return;
            }

        }
    }

    private boolean isEven(int position) {
        if (position % 2 == 0) {
            return true;
        }
        return false;
    }
}
