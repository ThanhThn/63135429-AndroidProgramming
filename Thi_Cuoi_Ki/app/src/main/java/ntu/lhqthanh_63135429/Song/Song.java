package ntu.lhqthanh_63135429.Song;

public class Song {
    String nameSong, nameArtist, thumbnail, idSong;
    int duration;

    public Song(String nameSong, String nameArtist, String thumbnail, String idSong, int duration) {
        this.nameSong = nameSong;
        this.nameArtist = nameArtist;
        this.thumbnail = thumbnail;
        this.idSong = idSong;
        this.duration = duration;
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
}
