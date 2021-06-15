package com.progettopdm.lyricbuddy.model.mxmResponse;

public class MxmBody {
    public MxmLyrics getLyrics() {
        return lyrics;
    }

    public MxmTrack getTrack() {
        return track;
    }

    private MxmLyrics lyrics;
    private MxmTrack track;
}
