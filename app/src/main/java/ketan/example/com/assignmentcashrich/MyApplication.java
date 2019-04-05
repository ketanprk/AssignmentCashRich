package ketan.example.com.assignmentcashrich;

import android.app.Application;
import android.content.Context;

import ketan.example.com.assignmentcashrich.di.AppComponent;
import ketan.example.com.assignmentcashrich.di.AppModule;
import ketan.example.com.assignmentcashrich.di.DaggerAppComponent;
import ketan.example.com.assignmentcashrich.di.UtilsModule;



public class MyApplication extends Application {
    AppComponent appComponent;
    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).utilsModule(new UtilsModule()).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }
}
