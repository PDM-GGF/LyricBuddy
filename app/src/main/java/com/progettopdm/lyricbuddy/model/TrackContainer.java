package com.progettopdm.lyricbuddy.model;

import java.util.List;

public abstract class TrackContainer {
    public abstract String getId();
    public abstract String getName();
    public abstract List<GenericImage> getImgList();
    public abstract List<Track> getTrackList();
    public abstract void setTrackList(List<Track> trackList);
    public abstract String getDescription();
}
