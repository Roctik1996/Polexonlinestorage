package com.polexexpress.polexonlinestorage.network;

import com.polexexpress.polexonlinestorage.model.ContentDetail;
import com.polexexpress.polexonlinestorage.model.ContentSearchInvoice;
import com.polexexpress.polexonlinestorage.model.ContentStoreInfo;
import com.polexexpress.polexonlinestorage.model.DetailGroupInvoice;
import com.polexexpress.polexonlinestorage.model.Group;
import com.polexexpress.polexonlinestorage.model.GroupResult;
import com.polexexpress.polexonlinestorage.model.Login;
import com.polexexpress.polexonlinestorage.model.StatusCheckInvoice;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface Backend {

    @Headers("Authorization:Basic cmVzdHJpY3RlZC1hdXRoLWNsaWVudDoxYzJWeVgyNWhiV1VpT2lKcQ==")
    @POST("auth-service/oauth/token")
    Single<Login> login(@Query("grant_type") String grant_type, @Query("username") String username, @Query("password") String pass);

    @Headers("Authorization:Basic cmVzdHJpY3RlZC1hdXRoLWNsaWVudDoxYzJWeVgyNWhiV1VpT2lKcQ==")
    @POST("auth-service/oauth/token")
    Single<Login> refreshToken(@Query("grant_type") String grant_type, @Query("refresh_token") String refresh_token);

    @GET("invoice-service/1.0/invoices/store-info")
    Single<ContentStoreInfo> getStoreInfo(@Header("Authorization") String token);

    @GET("invoice-service/1.0/invoices/details/{trackNo}")
    Single<ContentSearchInvoice> checkInvoice(@Header("Authorization") String token, @Path("trackNo") String trackNo);

    @GET("invoice-service/1.0/invoices/check/{groupId}/{trackNo}")
    Single<StatusCheckInvoice> checkInvoiceDetail(@Header("Authorization") String token, @Path("groupId") String groupId, @Path("trackNo") String trackNo);

    @PATCH("invoice-service/1.0/invoices/shipment/{groupId}/{trackNo}")
    Single<StatusCheckInvoice> checkShipment(@Header("Authorization") String token, @Path("groupId") String groupId, @Path("trackNo") String trackNo);

    @POST("invoice-service/1.0/groups/search")
    Single<GroupResult> getGroup(@Header("Authorization") String token, @Body Group group);

    @GET("invoice-service/1.0/groups/{id}")
    Single<ContentDetail> getGroupDetail(@Header("Authorization") String token, @Path("id") String id);

    @POST("invoice-service/1.0/invoices/all")
    Single<DetailGroupInvoice> getGroupDetailInvoice(@Header("Authorization") String token, @Body Group group);

    @PATCH("invoice-service/1.0/groups/finish-shipment/{groupId}")
    Single<StatusCheckInvoice> finishShipment(@Header("Authorization") String token, @Path("groupId") String groupId);
}
