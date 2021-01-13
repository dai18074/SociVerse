package com.example.socialmanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.socialmanager.R;
import com.example.socialmanager.utils.Post;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchAdapter extends ArrayAdapter<Post> {

    private List<Post> dataset;
    private final LayoutInflater inflater;
    private final int layoutResource;

    public SearchAdapter(@NonNull Context context, int resource, @NonNull List<Post> objects) {
        super(context, resource, objects);
        dataset = objects;
        inflater = LayoutInflater.from(context);
        layoutResource = resource;
    }

    public Post getPost(int position){
        if(position < dataset.size() ){
            return dataset.get(position);
        }
        return new Post();
    }

    public void setPosts(@NonNull List<Post> posts) {
        this.dataset = posts;
        notifyDataSetChanged();
    }

    public void addPosts(@NonNull List<Post> posts) {
        this.dataset.addAll(posts);
        notifyDataSetChanged();
    }

    static class PostsViewHolder {

        public ImageView imgType;
        public TextView txtUser;
        public ImageView imgPost;
        public TextView txtDate;
        public TextView txtPostText;

        public PostsViewHolder(View itemView) {
            imgType = itemView.findViewById(R.id.imgType);
            txtUser = itemView.findViewById(R.id.txtUser);
            imgPost = itemView.findViewById(R.id.imgPost);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtPostText = itemView.findViewById(R.id.txtPostText);
        }
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PostsViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(layoutResource, parent, false);
            holder = new PostsViewHolder(convertView);
            convertView.setTag(holder);
        }
        else{
            holder = (PostsViewHolder)convertView.getTag();
        }

        Post post = dataset.get(position);
        if (post.getType() == "twitter") holder.imgType.setImageResource(R.drawable.twitter_logo);
        else holder.imgType.setImageResource(R.drawable.ig_logo);
        holder.txtUser.setText(post.getUser());
        holder.txtDate.setText(post.getDate());
        holder.txtPostText.setText(post.getText());
        if (post.getUrlToImage() != "") {
            holder.imgPost.setVisibility(View.VISIBLE);
            Picasso.get().load(post.getUrlToImage()).into(holder.imgPost);
        } else {
            holder.imgPost.setVisibility(View.GONE);
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return dataset.size();
    }

}
