package ketan.example.com.assignmentcashrich.newslist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.widget.ProgressBar;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ketan.example.com.assignmentcashrich.R;
import ketan.example.com.assignmentcashrich.utils.Constant;

public class NewsDescriptionActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webview;

    @BindView(R.id.loader)
    ProgressBar loader;

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_description);
        ButterKnife.bind(this);
        if (getIntent() != null && getIntent().hasExtra(Constant.EXTRA_URL)) {
            url = getIntent().getStringExtra(Constant.EXTRA_URL);
        } else {
            finish();
        }

        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setDisplayZoomControls(false);
        webview.loadUrl(url);


        webview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    loader.setVisibility(View.GONE);
                } else {
                    loader.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
