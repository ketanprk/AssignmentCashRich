package ketan.example.com.assignmentcashrich.di;


import javax.inject.Singleton;

import dagger.Component;
import ketan.example.com.assignmentcashrich.newslist.NewsListActivity;

/**
 * Created by ${Saquib} on 03-05-2018.
 */

@Component(modules = {AppModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {

    void doInjection(NewsListActivity newsListActivity);

}
