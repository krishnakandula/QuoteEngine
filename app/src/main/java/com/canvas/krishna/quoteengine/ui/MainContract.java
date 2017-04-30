package com.canvas.krishna.quoteengine.ui;

import com.canvas.krishna.quoteengine.models.Quote;

import java.util.List;

/**
 * Created by Krishna Chaitanya Kandula on 4/30/17.
 */

public interface MainContract {
    interface View {
        void showLoading();
        void hideLoading();
        void showQuotes(List<Quote> quotes);
        void showErrorMessage(String err);
    }

    interface Presenter {
        void setView(View view);
        void start();
        void loadQuotes();
        void onRefresh();
    }
}
