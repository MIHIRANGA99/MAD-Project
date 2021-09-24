package com.example.woofnew;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class GroupAdapter extends FirebaseRecyclerAdapter <GroupsModel, GroupAdapter.groupsHolder>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public GroupAdapter(@NonNull FirebaseRecyclerOptions<GroupsModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull groupsHolder holder, int position, @NonNull GroupsModel model) {

    }

    @NonNull
    @Override
    public groupsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    class groupsHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView groupName;

        public groupsHolder(@NonNull View itemView) {
            super(itemView);

            image = (CircleImageView)itemView.findViewById(R.id.groupImage);
            groupName = (TextView)itemView.findViewById(R.id.groupName);
        }
    }
}
