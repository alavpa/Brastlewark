package es.alavpa.brastlewark.presenter;

import rx.Subscription;

/**
 * Created by alavpa on 14/9/16.
 */
public interface BaseView {

    void showError(String errorMessage);
    void startLoading();
    void stopLoading();
    void finish();
    void addSubscription(Subscription subscription);

}
