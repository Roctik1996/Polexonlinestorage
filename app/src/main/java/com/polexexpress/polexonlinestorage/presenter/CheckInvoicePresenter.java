package com.polexexpress.polexonlinestorage.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.polexexpress.polexonlinestorage.other.RxUtils;
import com.polexexpress.polexonlinestorage.provider.ProviderModule;
import com.polexexpress.polexonlinestorage.view.CheckInvoiceView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class CheckInvoicePresenter extends MvpPresenter<CheckInvoiceView> {

    private CompositeDisposable compositeDisposable;

    public CheckInvoicePresenter() {
        compositeDisposable = new CompositeDisposable();
    }

    public void refreshToken(String grant_type, String refresh_token){
        addBackgroundDisposable(
                ProviderModule.getProvider().refreshToken(grant_type, refresh_token)
                        .compose(RxUtils.ioToMainTransformerSingle())
                        .subscribe(success -> getViewState().refreshToken(success), exception -> getViewState().showError("Произошла ошибка")));
    }

    public void checkInvoice(String token,String groupId,String track_no) {
        addBackgroundDisposable(
                ProviderModule.getProvider().checkInvoiceDetail(token, groupId, track_no)
                        .compose(RxUtils.ioToMainTransformerSingle())
                        .subscribe(success -> getViewState().showSuccess(success), exception -> getViewState().showError("Error")));
    }

    private void addBackgroundDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    protected void finalize() throws Throwable {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
            compositeDisposable = null;
        }
        super.finalize();
    }
}
