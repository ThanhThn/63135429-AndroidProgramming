package ntu.lhqthanh_63135429.artist;

public class Artist {
    String urlThumbnail, idArtist, nameArtist;
    int follower;

    public Artist(String urlThumbnail, String idArtist, String nameArtist, int follower) {
        this.urlThumbnail = urlThumbnail;
        this.idArtist = idArtist;
        this.nameArtist = nameArtist;
        this.follower = follower;
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

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }
}
