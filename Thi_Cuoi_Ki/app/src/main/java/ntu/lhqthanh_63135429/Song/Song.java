package ntu.lhqthanh_63135429.Song;

import java.io.Serializable;

public class Song implements Serializable {
    String nameSong, nameArtist, thumbnail, idSong;
    int duration;
    Song nextSong = null, prevSong;

    public Song(String nameSong, String nameArtist, String thumbnail, String idSong, int duration, Song prevSong) {
        this.nameSong = nameSong;
        this.nameArtist = nameArtist;
        this.thumbnail = thumbnail;
        this.idSong = idSong;
        this.duration = duration;
        this.prevSong = prevSong;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getNameArtist() {
        return nameArtist;
    }

    public void setNameArtist(String nameArtist) {
        this.nameArtist = nameArtist;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getIdSong() {
        return idSong;
    }

    public void setIdSong(String idSong) {
        this.idSong = idSong;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Song getNextSong() {
        return nextSong;
    }

    public void setNextSong(Song nextSong) {
        this.nextSong = nextSong;
    }

    public Song getPrevSong() {
        return prevSong;
    }

    public void setPrevSong(Song prevSong) {
        this.prevSong = prevSong;
    }
}
