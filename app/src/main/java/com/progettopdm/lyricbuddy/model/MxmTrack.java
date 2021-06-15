package com.progettopdm.lyricbuddy.model;

import com.progettopdm.lyricbuddy.model.mxmResponse.MxmMessage;

public class MxmTrack {
    private MxmMessage message;

    public MxmMessage getMessage() {
        return message;
    }
    public String getLyrics() {
        return message.getBody().getLyrics().getLyrics_body();
    }
}

