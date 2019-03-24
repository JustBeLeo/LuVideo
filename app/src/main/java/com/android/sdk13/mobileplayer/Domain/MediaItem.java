package com.android.sdk13.mobileplayer.Domain;

import java.util.Objects;

public class MediaItem {
    private String name;
    private Long duration;
    private Long size;
    private String path;
    private String artist;

    public MediaItem() {
    }

    public MediaItem(String name, Long duration, Long size, String path, String artist) {
        this.name = name;
        this.duration = duration;
        this.size = size;
        this.path = path;
        this.artist = artist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MediaItem item = (MediaItem) o;
        return Objects.equals( name, item.name ) &&
                Objects.equals( duration, item.duration ) &&
                Objects.equals( size, item.size ) &&
                Objects.equals( path, item.path ) &&
                Objects.equals( artist, item.artist );
    }

    @Override
    public int hashCode() {

        return Objects.hash( name, duration, size, path, artist );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String data) {
        this.path = data;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "MediaItem{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", size=" + size +
                ", path='" + path + '\'' +
                ", artist='" + artist + '\'' +
                '}';
    }
}
