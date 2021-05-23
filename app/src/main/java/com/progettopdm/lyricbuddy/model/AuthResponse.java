package com.progettopdm.lyricbuddy.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AuthResponse implements Parcelable {

    private String status;
    private int totalResults;
    @SerializedName(value = "access_token")
    private String accessToken;

    public AuthResponse(String status, int totalResults, String accessToken) {
        this.accessToken = accessToken;
        this.status = status;
        this.totalResults = totalResults;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    protected AuthResponse(Parcel in) {
        accessToken = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(accessToken);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AuthResponse> CREATOR = new Creator<AuthResponse>() {
        @Override
        public AuthResponse createFromParcel(Parcel in) {
            return new AuthResponse(in);
        }

        @Override
        public AuthResponse[] newArray(int size) {
            return new AuthResponse[size];
        }
    };
}
