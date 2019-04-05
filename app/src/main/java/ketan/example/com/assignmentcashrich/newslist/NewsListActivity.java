package ketan.example.com.assignmentcashrich.newslist;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ketan.example.com.assignmentcashrich.MyApplication;
import ketan.example.com.assignmentcashrich.R;
import ketan.example.com.assignmentcashrich.model.dto.NewsListResponse;
import ketan.example.com.assignmentcashrich.utils.ApiResponse;
import ketan.example.com.assignmentcashrich.utils.Constant;
import ketan.example.com.assignmentcashrich.utils.ViewModelFactory;


public class NewsListActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;

    @BindView(R.id.listNews)
    RecyclerView listNews;

    NewsListViewModel viewModel;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        progressDialog = Constant.getProgressDialog(this, "Please wait...");

        ButterKnife.bind(this);
        ((MyApplication) getApplication()).getAppComponent().doInjection(this);


        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsListViewModel.class);
        viewModel.loginResponse().observe(this, this::consumeResponse);

        viewModel.getNewsFeed("us", "business");

    }


    /*
     * method to handle response
     * */
    private void consumeResponse(ApiResponse apiResponse) {

        switch (apiResponse.status) {

            case LOADING:
                progressDialog.show();
                break;

            case SUCCESS:
                progressDialog.dismiss();
                renderSuccessResponse(apiResponse.data);
                break;

            case ERROR:
                progressDialog.dismiss();
                Log.v("Response Activity", "" + apiResponse.toString());
                Toast.makeText(NewsListActivity.this, getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }

    /*
     * method to handle success response
     * */
    private void renderSuccessResponse(NewsListResponse response) {
        if (response.getStatus().equalsIgnoreCase("ok")) {
            Log.d("response=", response.toString());

            listNews.setLayoutManager(new LinearLayoutManager(this));
            listNews.setItemAnimator(new DefaultItemAnimator());
            listNews.setAdapter(new ListNewsAdapter(this, response.getArticles()) {

                @Override
                public void handleClick(int position) {
                    Intent i = new Intent(NewsListActivity.this, NewsDescriptionActivity.class);
                    i.putExtra(Constant.EXTRA_URL, response.getArticles().get(position).getUrl());
                    startActivity(i);
                }
            });
        } else {
            Toast.makeText(NewsListActivity.this, getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
        }
    }
}
