package domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class CourseMaterialTest {
    private CourseMaterial courseMaterial;

    @Before
    public void before(){
        courseMaterial = new CourseMaterial();
    }

    @Test
    public void setMaterialId() {
        Integer id = 1;
        courseMaterial.setMaterialId(id);
        Assert.assertEquals(id, courseMaterial.getMaterialId());
    }

    @Test
    public void setYoutubeURL() {
        courseMaterial.setYoutubeURL("youtubeLink");
        Assert.assertEquals("youtubeLink", courseMaterial.getYoutubeURL());
    }

    @Test
    public void setFullDescription() {
        courseMaterial.setFullDescription("Beschrijving");
        Assert.assertEquals("Beschrijving", courseMaterial.getFullDescription());
    }

    @Test
    public void setRank() {
        courseMaterial.setRank(2);
        Assert.assertEquals(2, courseMaterial.getRank());
    }

    @Test
    public void setTitle() {
        courseMaterial.setTitle("Titel");
        Assert.assertEquals("Titel", courseMaterial.getTitle());
    }

    @Test
    public void setCommentCollection() {
        Collection<Comment> commentCollection = new ArrayList<>();
        commentCollection.add(new Comment());
        commentCollection.add(new Comment());
        courseMaterial.setCommentCollection(commentCollection);
        Assert.assertEquals(commentCollection.size(), courseMaterial.getCommentCollection().size());
    }

    @Test
    public void setImageCollection() {
        Collection<Image> imageCollection = new ArrayList<>();
        imageCollection.add(new Image());
        imageCollection.add(new Image());
        courseMaterial.setImageCollection(imageCollection);
        Assert.assertEquals(imageCollection.size(), courseMaterial.getImageCollection().size());
    }
}