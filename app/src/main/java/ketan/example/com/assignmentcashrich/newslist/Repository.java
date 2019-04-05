package ketan.example.com.assignmentcashrich.newslist;

import com.google.gson.JsonElement;

import io.reactivex.Observable;
import ketan.example.com.assignmentcashrich.model.dto.NewsListResponse;
import ketan.example.com.assignmentcashrich.utils.ApiCallInterface;
import ketan.example.com.assignmentcashrich.utils.Urls;


public class Repository {

    private ApiCallInterface apiCallInterface;

    public Repository(ApiCallInterface apiCallInterface) {
        this.apiCallInterface = apiCallInterface;
    }

    /*
     * method to call login api
     * */
    public Observable<NewsListResponse> getLatestNews(String mobileNumber, String password) {
        return apiCallInterface.getNews(mobileNumber, password, Urls.API_KEY);
    }

}
