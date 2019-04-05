package ketan.example.com.assignmentcashrich.utils;

import android.util.Log;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import ketan.example.com.assignmentcashrich.model.dto.NewsListResponse;

import static ketan.example.com.assignmentcashrich.utils.Status.ERROR;
import static ketan.example.com.assignmentcashrich.utils.Status.LOADING;
import static ketan.example.com.assignmentcashrich.utils.Status.SUCCESS;


public class ApiResponse {

    public final Status status;

    @Nullable
    public final NewsListResponse data;

    @Nullable
    public final Throwable error;

    private ApiResponse(Status status, @Nullable NewsListResponse data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static ApiResponse loading() {
        return new ApiResponse(LOADING, null, null);
    }

    public static ApiResponse success(@NonNull NewsListResponse data) {
        Log.v("Response", "" + data.toString());
        return new ApiResponse(SUCCESS, data, null);
    }

    public static ApiResponse error(@NonNull Throwable error) {
        Log.v("Response", "" + error.toString());
        return new ApiResponse(ERROR, null, error);
    }

}
