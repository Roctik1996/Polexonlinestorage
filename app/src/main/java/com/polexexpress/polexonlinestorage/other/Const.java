package com.polexexpress.polexonlinestorage.other;

import com.polexexpress.polexonlinestorage.R;

import java.util.HashMap;

public class Const {
    public static String BASE_URL = "http://track.polex-express.com/";
    public static String token = "";
    public static String token_type="";
    public static String refresh_token="";
    public static final String grant_type="password";
    public static final String grant_type_refresh="refresh_token";
    private static final HashMap<String, Integer> icon = new HashMap<>();
    static {
        icon.put("NEW", R.drawable.ic_new);
        icon.put("IN_SHIPMENT", R.drawable.ic_shipped_shipped_partially_in_shipment);
        icon.put("SHIPPED_PARTIALLY", R.drawable.ic_shipped_shipped_partially_in_shipment);
        icon.put("SHIPPED", R.drawable.ic_shipped_shipped_partially_in_shipment);
        icon.put("READY_TO_SEND", R.drawable.ic_waiting_to_send_expected);
        icon.put("WAITING_TO_SEND", R.drawable.ic_waiting_to_send_expected);
        icon.put("DELIVERED", R.drawable.ic_delivered);
        icon.put("IN_RECEIPT", R.drawable.ic_in_receipt_rec_accepted);
        icon.put("DEP_ACCEPTED", R.drawable.ic_dep_accepted);
        icon.put("DRAFT", R.drawable.ic_draft);
        icon.put("IN_PROCESSING", R.drawable.ic_in_processing_customs_process);
        icon.put("FINISH", R.drawable.ic_finish);
    }
    public static Integer getTypeIcon(String type){
        return Const.icon.get(type);
    }
}
