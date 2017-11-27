package album.db;

import album.entities.Photo;
import album.entities.PhotoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provide access to db to save, update, select photos information
 * @author sgdr
 */
@Repository
public class PhotoDao {

    private DataSource dataSource;

    @Autowired
    public PhotoDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /** query to insert photo with information */
    private static final String INSERT_QUERY =
            "INSERT INTO photos (name, image, description) VALUES (:name, :image, :description);";

    /**
     * Insert photos
     * @param photoList list of photos with information
     */
    public void savePhotos(List<Photo> photoList) {
        Map<String, Object>[] paramList = photoList.stream().map(photo -> {
            Map<String, Object> params = new HashMap<>();
            params.put("name", photo.getInfo().getImageName());
            params.put("image", photo.getImage());
            params.put("description", photo.getInfo().getDescription());
            return params;
        }).toArray(size -> (Map<String, Object>[]) new HashMap[size]);
        SqlParameterSource[] sqlParameterSources = SqlParameterSourceUtils.createBatch(paramList);
        try {
            getTemplate().batchUpdate(INSERT_QUERY, sqlParameterSources);
        } catch (DataAccessException ex) {
            ex.printStackTrace();
        }
    }

    /** query to select photos information list */
    private static final String SELECT_QUERY = "SELECT name, description FROM photos;";

    /**
     * select photos information list
     * @return photos information list
     */
    public List<PhotoInfo> selectPhotos() {
        return getTemplate().query(SELECT_QUERY, (ResultSet rs, int rowNum) -> {
            PhotoInfo info = new PhotoInfo();
            info.setImageName(rs.getString("name"));
            info.setDescription(rs.getString("description"));
            return info;
        });
    }

    /** query to select photo */
    private static final String SELECT_IMAGE = "SELECT image FROM photos WHERE name=:name;";

    /**
     * Get byte representation of photo
     * @param name name of photo
     * @return byte representation of photo
     */
    public byte[] getImage(String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return getTemplate().queryForObject(SELECT_IMAGE, params, (ResultSet rs, int num) -> rs.getBytes("image"));
    }

    /**
     * @return named template
     */
    public NamedParameterJdbcTemplate getTemplate() {
        return new NamedParameterJdbcTemplate(this.dataSource);
    }
}
