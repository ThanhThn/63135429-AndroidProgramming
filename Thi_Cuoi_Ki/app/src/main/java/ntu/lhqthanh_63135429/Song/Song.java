package ntu.lhqthanh_63135429.Song;

public class Song {
    String nameSong, nameArtist, thumbnail, idSong;

    public Song(String nameSong, String nameArtist, String thumbnail, String idSong) {
        this.nameSong = nameSong;
        this.nameArtist = nameArtist;
        this.thumbnail = thumbnail;
        this.idSong = idSong;
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
}
