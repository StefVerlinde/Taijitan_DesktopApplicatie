package repository;

import domain.Image;

public class ImageDaoJpa extends GenericDaoJpa<Image> implements ImageDao {
    public ImageDaoJpa() {
        super(Image.class);
    }
}
