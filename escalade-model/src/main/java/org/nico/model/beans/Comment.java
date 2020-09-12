package org.nico.model.beans;

import java.lang.reflect.Member;
import java.sql.Timestamp;

public class Comment {

    //====  ATTRIBUTES  ====//

    private Integer id;
    private String commentText;
    private Timestamp creationDate;
    private Timestamp updateDate;

    private User user;
    private ClimbingSite climbingSite;


    //====  CONSTRUCTOR  ====//

    public Comment(){}

    public Comment(Integer id, String commentText, Timestamp creationDate, Timestamp updateDate, User user, ClimbingSite climbingSite){
        this.id = id;
        this.commentText = commentText;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.user = user;
        this.climbingSite = climbingSite;
    }

    //====  GETTERS AND SETTERS  ====//


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ClimbingSite getClimbingSite() {
        return climbingSite;
    }

    public void setClimbingSite(ClimbingSite climbingSite) {
        this.climbingSite = climbingSite;
    }
}
