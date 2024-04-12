package ntu.com.usingrecycleview;

public class LandScape {
    String imgLandFileName, landCaption;

    public LandScape(String imgLandFileName, String landCaption) {
        this.imgLandFileName = imgLandFileName;
        this.landCaption = landCaption;
    }

    public String getImgLandFileName() {
        return imgLandFileName;
    }

    public void setImgLandFileName(String imgLandFileName) {
        this.imgLandFileName = imgLandFileName;
    }

    public String getLandCaption() {
        return landCaption;
    }

    public void setLandCaption(String landCaption) {
        this.landCaption = landCaption;
    }
}
