package album.services;

import album.entities.Photo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest service provides photos
 * @author sgdr
 */
@RestController
public class PhotoController {

    @RequestMapping(value = "/photos", method = RequestMethod.GET)
    public Photo getPhotos() {
        return new Photo().setImageName("img123").setDescription("first photo");
    }

}
