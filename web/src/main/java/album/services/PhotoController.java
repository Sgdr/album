package album.services;

import album.db.PhotoDao;
import album.entities.Photo;
import album.entities.PhotoInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Rest service provides photos
 * @author sgdr
 */
@RestController
public class PhotoController {

    private static final Logger log = LoggerFactory.getLogger("album");

    private final PhotoDao photoDao;

    public PhotoController(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }

    /**
     * @return list of photos descriptions
     */
    @RequestMapping(value = "/photos", method = RequestMethod.GET)
    public List<PhotoInfo> getPhotos() {
        return photoDao.selectPhotos();
    }

    /**
     * Save photos with descriptions to db
     * @param photoInfos photos information
     * @param files      bytes representations of photos
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void savePhotos(@RequestPart("descriptions") PhotoInfo[] photoInfos,
                           @RequestParam("photosUpload") MultipartFile[] files) {
        List<Photo> photoList = Stream.of(files).map(file -> {
            String name = file.getOriginalFilename();
            PhotoInfo photoInfo = Stream.of(photoInfos).filter(info ->
                    name.equals(info.getImageName()))
                    .findFirst().orElseThrow(IllegalArgumentException::new);
            try {
                return new Photo(photoInfo, file.getBytes());
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }).collect(Collectors.toList());
        photoDao.savePhotos(photoList);
    }

    /**
     * return photo
     * @param name photo name
     * @return bytes representation of photo
     */
    @RequestMapping(value = "/photo/{name:.+}", method = RequestMethod.GET)
    public byte[] getPhotoByName(@PathVariable("name") String name) {
        return photoDao.getImage(name);
    }

    /**
     * Handle exceptions that not catched
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    String handleBadRequest(HttpServletRequest req, Exception ex) {
        log.error(req.getPathInfo(), ex);
        return ex.getLocalizedMessage();
    }
}
