package ntu.lhqthanh_63135429.playlist;

public class Playlist {
    String thumbnail, nameList, nameArtist, idList;

    public Playlist(String thumbnail, String nameList, String nameArtist, String idList) {
        this.thumbnail = thumbnail;
        this.nameList = nameList;
        this.nameArtist = nameArtist;
        this.idList = idList;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getNameList() {
        return nameList;
    }

    public void setNameList(String nameList) {
        this.nameList = nameList;
    }

    public String getNameArtist() {
        return nameArtist;
    }

    public void setNameArtist(String nameArtist) {
        this.nameArtist = nameArtist;
    }

    public String getIdList() {
        return idList;
    }

    public void setIdList(String idList) {
        this.idList = idList;
    }
}
