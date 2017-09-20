package album.entities;

/**
 * Information about photo
 * @author sgdr
 */
public class Photo {

    private String imageName;

    private String description;

    public String getImageName() {
        return imageName;
    }

    public Photo setImageName(String imageName) {
        this.imageName = imageName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Photo setDescription(String description) {
        this.description = description;
        return this;
    }
}
