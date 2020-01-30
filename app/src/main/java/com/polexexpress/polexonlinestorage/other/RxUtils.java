package com.polexexpress.polexonlinestorage.other;

import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Helper class for Java RX
 */
public class RxUtils {

    private RxUtils() {
        //no instance
    }

    public static <T> SingleTransformer<T, T> ioToMainTransformerSingle() {
        return inSingle -> inSingle
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
