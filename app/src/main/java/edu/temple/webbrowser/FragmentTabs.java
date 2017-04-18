package edu.temple.webbrowser;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FragmentTabs extends Fragment {

    private static final String ARG_URL = "URL_PARAM";
    private String URL;

    public FragmentTabs() {
    }

    public static FragmentTabs newInstance(String address) {
        FragmentTabs fragment = new FragmentTabs();
        Bundle args = new Bundle();
        args.putString(ARG_URL, address);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            URL = getArguments().getString(ARG_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabs_fragment, container, false);
        WebView webView = (WebView) view.findViewById(R.id.viewWeb);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(URL);
        return view;
    }

    public String getURL(){
        return URL;
    }
}

