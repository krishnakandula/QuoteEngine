package com.canvas.krishna.quoteengine.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.canvas.krishna.quoteengine.R;
import com.canvas.krishna.quoteengine.app.QuoteApplication;
import com.canvas.krishna.quoteengine.models.Quote;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements MainContract.View{
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Inject
    MainContract.Presenter presenter;

    @BindView(R.id.activity_main_quote_recycler_view)
    RecyclerView quoteRecyclerView;

    @BindView(R.id.activity_main_swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private Unbinder mUnbinder;
    private RecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((QuoteApplication) getApplication()).getAppComponent().inject(this);
        mUnbinder = ButterKnife.bind(this);

        presenter.setView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupQuoteRecyclerView();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onRefresh();
            }
        });
        presenter.start();
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showQuotes(List<Quote> quotes) {
        mAdapter.setQuotes(quotes);
    }

    @Override
    public void showErrorMessage(String err) {
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
    }

    private void setupQuoteRecyclerView(){
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        quoteRecyclerView.setLayoutManager(linearLayoutManager);
        if (mAdapter == null){
            mAdapter = new RecyclerViewAdapter();
            quoteRecyclerView.setAdapter(mAdapter);
            //Set quotes list to be empty
            mAdapter.setQuotes(new ArrayList<Quote>());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.QuoteViewHolder> {
        List<Quote> quotes = new ArrayList<>();

        @Override
        public QuoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_quote, parent, false);
            return new QuoteViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(QuoteViewHolder holder, int position) {
            holder.bind(quotes.get(position));
        }

        @Override
        public int getItemCount() {
            return quotes.size();
        }

        /**
         * Replace all current quotes
         * @param quotes
         */
        public void setQuotes(List<Quote> quotes){
            this.quotes = quotes;
            notifyDataSetChanged();
        }

        /**
         * Add quotes to end of quotes list
         * @param newQuotes
         */
        public void addQuotes(List<Quote> newQuotes){
            int lastQuoteIndex = quotes.size();
            quotes.addAll(newQuotes);
            notifyItemRangeInserted(lastQuoteIndex, newQuotes.size());
        }

        class QuoteViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.itemview_quote_text_textview)
            TextView quoteTextView;

            public QuoteViewHolder(View itemView){
                super(itemView);
                ButterKnife.bind(this, itemView);
            }

            public void bind(Quote quote){
                quoteTextView.setText(quote.getQuote());
            }
        }
    }
}
