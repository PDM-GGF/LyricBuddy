package com.progettopdm.lyricbuddy.model;

import com.progettopdm.lyricbuddy.model.mxmResponse.MxmMessage;

public class MxmTrack {
    private MxmMessage message;
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public MxmMessage getMessage() {
        return message;
    }

    public int getId() {
        return id;
    }

    public String getLyrics() {
        return message.getBody().getLyrics().getLyrics_body();
    }
}

