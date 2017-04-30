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

        /**
         * Show quotes to user
         * @param quotes List of quotes
         * @param additionalQuotes whether or not to add new quotes to end of list
         */
        void showQuotes(List<Quote> quotes, boolean additionalQuotes);
        void showErrorMessage(String err);
    }

    interface Presenter {
        void setView(View view);
        void start();

        /**
         * Loads quotes from api
         * @param additionalQuotes whether or not to add quotes to end of existing quotes
         */
        void loadQuotes(boolean additionalQuotes);
        void onRefresh();
        void onLoadMoreQuotes();
    }
}
