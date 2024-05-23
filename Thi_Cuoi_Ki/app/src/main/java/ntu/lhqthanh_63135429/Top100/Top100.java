package ntu.lhqthanh_63135429.Top100;

public class Top100 {
    String thumbnail, nameTop, nameArtist, idTop;

    public Top100(String thumbnail, String nameTop, String nameArtist, String idTop) {
        this.thumbnail = thumbnail;
        this.nameTop = nameTop;
        this.nameArtist = nameArtist;
        this.idTop = idTop;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getNameTop() {
        return nameTop;
    }

    public void setNameTop(String nameTop) {
        this.nameTop = nameTop;
    }

    public String getNameArtist() {
        return nameArtist;
    }

    public void setNameArtist(String nameArtist) {
        this.nameArtist = nameArtist;
    }

    public String getIdTop() {
        return idTop;
    }

    public void setIdTop(String idTop) {
        this.idTop = idTop;
    }
}
