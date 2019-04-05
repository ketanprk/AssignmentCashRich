package ketan.example.com.assignmentcashrich.newslist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import ketan.example.com.assignmentcashrich.R;
import ketan.example.com.assignmentcashrich.model.dto.Articles;


abstract class ListNewsAdapter extends RecyclerView.Adapter<ListNewsAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Articles> listArticles;

    public ListNewsAdapter(Context context, ArrayList<Articles> listArticles) {
        this.context = context;
        this.listArticles = listArticles;
    }

    @Override
    public ListNewsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_list_item, parent, false);

        return new ListNewsAdapter.MyViewHolder(itemView);
    }

    public int getCount() {
        return listArticles.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(ListNewsAdapter.MyViewHolder holder, final int position) {

        Articles mArticle = listArticles.get(position);


        holder.tv_author.setText(mArticle.getAuthor());
        holder.tv_title.setText(mArticle.getTitle());
        holder.tv_time.setText(mArticle.getPublishedAt());
        holder.tv_details.setText(mArticle.getDescription());
        holder.tv_source.setText(mArticle.getSource().getName());
        Glide.with(context).load(mArticle.getUrlToImage()).apply(new RequestOptions().placeholder(R.drawable.ic_gallery_default)).into(holder.iv_image);
        holder.ll_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleClick(position);
            }
        });


    }

    public abstract void handleClick(int position);

    @Override
    public int getItemCount() {
        return listArticles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title, tv_details, tv_author, tv_time, tv_source;
        ImageView iv_image;
        LinearLayout ll_parent;

        public MyViewHolder(View view) {
            super(view);
            ll_parent = view.findViewById(R.id.ll_parent);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_details = (TextView) view.findViewById(R.id.tv_details);
            tv_author = (TextView) view.findViewById(R.id.tv_author);
            iv_image = (ImageView) view.findViewById(R.id.iv_image);
            tv_time = (TextView) view.findViewById(R.id.tv_time);
            tv_source = (TextView) view.findViewById(R.id.tv_source);
        }
    }
}