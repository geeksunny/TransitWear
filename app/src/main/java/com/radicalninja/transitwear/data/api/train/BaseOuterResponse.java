package com.radicalninja.transitwear.data.api.train;

import org.simpleframework.xml.Element;

public class BaseOuterResponse {

    /**
     * Textual error description / messages
     */
    @Element(name="errNm", required=false)
    String errorName;

    /**
     * Numeric error code
     */
    @Element(name="errCd", required=false)
    String errorCode;

    /**
     * Shows time when response was generated in format:
     *  yyyyMMdd HH:mm:ss (24-hour format, time local to Chicago)
     */
    @Element(name="tmst", required=false)
    String timestamp;

    public String getErrorName() {return this.errorName;}
    public void setErrorName(String value) {this.errorName = value;}

    public String getErrorCode() {return this.errorCode;}
    public void setErrorCode(String value) {this.errorCode = value;}

    public String getTimestamp() {return this.timestamp;}
    public void setTimestamp(String value) {this.timestamp = value;}

}
