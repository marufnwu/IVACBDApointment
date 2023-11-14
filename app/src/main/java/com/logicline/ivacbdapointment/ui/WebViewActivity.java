package com.logicline.ivacbdapointment.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.logicline.ivacbdapointment.R;
import com.logicline.ivacbdapointment.databinding.ActivityWebViewBinding;
import com.logicline.ivacbdapointment.utils.Constants;

public class WebViewActivity extends AppCompatActivity {
    private ActivityWebViewBinding binding;

    public static Intent getWebActivityIntent(Context context, String url){
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(Constants.KEY_INTENT_WEB_VIEW_ACTIVITY, url);

        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityWebViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Privacy Policy");
        }

        if (getIntent().hasExtra(Constants.KEY_INTENT_WEB_VIEW_ACTIVITY)){
            String url = getIntent().getStringExtra(Constants.KEY_INTENT_WEB_VIEW_ACTIVITY);

            binding.wvWebView.loadUrl(url);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}