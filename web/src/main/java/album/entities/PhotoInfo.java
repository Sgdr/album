package album.entities;

/**
 * Information about photo
 * @author sgdr
 */
public class PhotoInfo {

    /** photos name */
    private String imageName;

    /** description of photo (users comment) */
    private String description;

    public PhotoInfo() {}

    public PhotoInfo(String imageName, String description) {
        this.description = description;
        this.imageName = imageName;
    }

    public String getImageName() {
        return imageName;
    }

    public PhotoInfo setImageName(String imageName) {
        this.imageName = imageName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PhotoInfo setDescription(String description) {
        this.description = description;
        return this;
    }
}
