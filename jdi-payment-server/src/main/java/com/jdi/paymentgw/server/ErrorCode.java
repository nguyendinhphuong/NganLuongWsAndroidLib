package com.jdi.paymentgw.server;

/**
 * Created by phuongnd on 6/8/17.
 */

public enum ErrorCode {
    ERR001("01", "Lỗi không xác định"),
    ERR002("02", "merchant_id không tồn tại"),
    ERR004("04", "checksum không đúng"),
    ERR005("05", "Không ghi nhận được đơn hàng trên cổng thanh toán"),
    ERR006("06", "token_code không tồn tại hoặc không hợp lệ"),
    ERR007("07", "Đơn hàng chưa được thanh toán"),
    ERR009("09", "receiver_email không tồn tại"),
    ERR011("11", "receiver_email đang bị khóa hoặc phong tỏa không thể giao dịch"),
    ERR020("20", "function không đúng"),
    ERR021("21", "version không đúng hoặc không tồn tại"),
    ERR022("22", "Thiếu tham số đầu vào"),
    ERR023("23", "order_code mã đơn hàng không hợp lệ"),
    ERR024("24", "total_amount không hợp lệ"),
    ERR025("25", "currency không hợp lệ"),
    ERR026("26", "language không hợp lệ"),
    ERR027("27", "return_url không hợp lệ"),
    ERR028("28", "cancel_url không hợp lệ"),
    ERR029("29", "notify_url không hợp lệ"),
    ERR030("30", "buyer_fullname không hợp lệ"),
    ERR031("31", "buyer_email không hợp lệ"),
    ERR032("32", "buyer_mobile không hợp lệ"),
    ERR033("33", "buyer_address không hợp lệ"),
    GW001("GW001", "GW lỗi không xác định");;


    private final String code;
    private final String description;

    private ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "{ response_code:\"" + code + "\",response_detail:\""+ description + "\"}";
    }
}