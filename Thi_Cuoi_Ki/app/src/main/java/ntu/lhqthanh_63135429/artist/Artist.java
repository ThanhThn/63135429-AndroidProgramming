package ntu.lhqthanh_63135429.artist;

public class Artist {
    String urlThumbnail, idArtist, nameArtist, playlistId;

    public Artist(String urlThumbnail, String idArtist, String nameArtist, String playlistId) {
        this.urlThumbnail = urlThumbnail;
        this.idArtist = idArtist;
        this.nameArtist = nameArtist;
        this.playlistId = playlistId;
    }

    public String getUrlThumbnail() {
        return urlThumbnail;
    }

    public void setUrlThumbnail(String urlThumbnail) {
        this.urlThumbnail = urlThumbnail;
    }

    public String getIdArtist() {
        return idArtist;
    }

    public void setIdArtist(String idArtist) {
        this.idArtist = idArtist;
    }

    public String getNameArtist() {
        return nameArtist;
    }

    public void setNameArtist(String nameArtist) {
        this.nameArtist = nameArtist;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }
}
