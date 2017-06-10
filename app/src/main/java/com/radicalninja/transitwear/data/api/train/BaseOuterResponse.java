package com.radicalninja.transitwear.data.api.train;

import com.google.gson.annotations.SerializedName;

public class BaseOuterResponse {

    /**
     * Textual error description / messages
     */
    @SerializedName("errNm")
    String errorName;

    /**
     * Numeric error code
     */
    @SerializedName("errCd")
    String errorCode;

    /**
     * Shows time when response was generated in format:
     *  yyyyMMdd HH:mm:ss (24-hour format, time local to Chicago)
     */
    @SerializedName("tmst")
    String timestamp;

    @SerializedName("TimeStamp")
    String _timestamp;

    public String getErrorName() {return this.errorName;}
    public void setErrorName(String value) {this.errorName = value;}

    public String getErrorCode() {return this.errorCode;}
    public void setErrorCode(String value) {this.errorCode = value;}

    public String getTimestamp() {return this.timestamp;}
    public void setTimestamp(String value) {this.timestamp = value;}

    public String get_Timestamp() {return this._timestamp;}
    public void set_Timestamp(String value) {this._timestamp = value;}

}
