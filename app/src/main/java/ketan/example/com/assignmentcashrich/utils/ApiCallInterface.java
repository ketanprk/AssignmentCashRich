package ketan.example.com.assignmentcashrich.utils;

import io.reactivex.Observable;
import ketan.example.com.assignmentcashrich.model.dto.NewsListResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiCallInterface {

    @GET("top-headlines")
    Observable<NewsListResponse> getNews(@Query("country") String mobileNumber, @Query("category") String password, @Query("apiKey") String apiKey);
}
