package com.polexexpress.polexonlinestorage.provider;

import com.polexexpress.polexonlinestorage.model.ContentDetail;
import com.polexexpress.polexonlinestorage.model.ContentSearchInvoice;
import com.polexexpress.polexonlinestorage.model.ContentStoreInfo;
import com.polexexpress.polexonlinestorage.model.DetailGroupInvoice;
import com.polexexpress.polexonlinestorage.model.Group;
import com.polexexpress.polexonlinestorage.model.GroupResult;
import com.polexexpress.polexonlinestorage.model.Login;
import com.polexexpress.polexonlinestorage.model.StatusCheckInvoice;
import com.polexexpress.polexonlinestorage.network.Backend;
import com.polexexpress.polexonlinestorage.network.NetworkModule;

import io.reactivex.Single;

public class ProviderImpl {
    private Backend mBackendService;

    ProviderImpl() {
        initNetworkModule();
    }

    private void initNetworkModule() {
        mBackendService = NetworkModule.getBackEndService();
    }

    public Single<Login> login(String grant_type, String username, String pass) {
        return mBackendService.login(grant_type, username, pass);
    }

    public Single<Login> refreshToken(String grant_type, String refresh_token) {
        return mBackendService.refreshToken(grant_type, refresh_token);
    }

    public Single<ContentStoreInfo> getStoreInfo(String token) {
        return mBackendService.getStoreInfo(token);
    }

    public Single<ContentSearchInvoice> checkInvoice(String token, String trackNo) {
        return mBackendService.checkInvoice(token, trackNo);
    }

    public Single<StatusCheckInvoice> checkInvoiceDetail(String token, String groupId, String trackNo) {
        return mBackendService.checkInvoiceDetail(token, groupId, trackNo);
    }

    public Single<StatusCheckInvoice> checkShipment(String token, String groupId, String trackNo) {
        return mBackendService.checkShipment(token, groupId, trackNo);
    }

    public Single<GroupResult> getGroup(String token, Group group) {
        return mBackendService.getGroup(token, group);
    }

    public Single<ContentDetail> getGroupDetail(String token, String id) {
        return mBackendService.getGroupDetail(token, id);
    }

    public Single<DetailGroupInvoice> getGroupDetailInvoice(String token, Group group) {
        return mBackendService.getGroupDetailInvoice(token, group);
    }

    public Single<StatusCheckInvoice> finishShipment(String token, String groupId) {
        return mBackendService.finishShipment(token, groupId);
    }

}
