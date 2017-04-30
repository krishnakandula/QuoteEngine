package com.canvas.krishna.quoteengine.ui;

/**
 * Created by Krishna Chaitanya Kandula on 4/30/17.
 */

public interface MainContract {
    interface View {

    }

    interface Presenter {
        void setView(View view);
        void start();
    }
}
