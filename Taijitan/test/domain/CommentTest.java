package domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.*;

public class CommentTest {
    private Comment comment;

    @Before
    public void before(){
        this.comment = new Comment();
    }

    @Test
    public void setValidId(){
        Integer  id = 2;
        this.comment.setCommentId(id);
        Assert.assertEquals(id, this.comment.getCommentId());
    }

    @Test
    public void setValidContent(){
        String name = "testCommentaar";
        this.comment.setContent(name);
        Assert.assertEquals(name, comment.getContent());
    }

    @Test (expected= IllegalArgumentException.class)
    public void setNullContent_givesError(){
        this.comment.setContent(null);
    }

    @Test (expected= IllegalArgumentException.class)
    public void setEmptyContent_givesError(){
        this.comment.setContent("");
    }

    @Test
    public void setValidDateCreated(){
        Date dat = new Date(2019,6,18);
        this.comment.setDateCreated(dat);
        Assert.assertEquals(dat, this.comment.getDateCreated());
    }

    @Test (expected= IllegalArgumentException.class)
    public void setNullDateCreated_givesError(){
        this.comment.setDateCreated(null);
    }







}