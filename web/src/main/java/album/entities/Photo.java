package album.entities;

/**
 * @author sgdr
 */
public class Photo {

    private PhotoInfo info;

    private byte[] image;

    public Photo(PhotoInfo info, byte[] image) {
        this.info = info;
        this.image = image;
    }

    public PhotoInfo getInfo() {
        return info;
    }

    public void setInfo(PhotoInfo info) {
        this.info = info;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
