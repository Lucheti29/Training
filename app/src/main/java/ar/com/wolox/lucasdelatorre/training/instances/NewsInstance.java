package ar.com.wolox.lucasdelatorre.training.instances;

import ar.com.wolox.lucasdelatorre.training.Utils;

public class NewsInstance {

    private String objectId;
    private String id;
    private String[] likes;
    private boolean like;
    private String picture;
    private String text;
    private String title;
    private String createdAt;
    private String updatedAt;
    private String diffTime;

    public void initialize(String reader) {
        this.like = false;

        for (String like : likes) {
            if (like.equalsIgnoreCase(reader)) {
                this.like = true;
                break;
            }
        }

        diffTime = Utils.getDiffTimeInString(createdAt);
    }

    public boolean isLike() {
        return like;
    }

    public String getDescription() {
        return text;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getLikes() {
        return likes;
    }

    public void setLikes(String[] likes) {
        this.likes = likes;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDiffTime() {
        return diffTime;
    }
}
