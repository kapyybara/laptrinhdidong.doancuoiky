package ltdd.doan.mangxahoi.data.model;


import java.time.Instant;
import java.util.Date;

import ltdd.doan.mangxahoi.data.Utils;

public class Comment {
    private Integer id;
    private Integer post_id;
    private Integer owner;
    private String  text;
    private String created_at;

    public Comment(Integer id, Integer post_id, Integer owner, String text, String created_at) {
        this.id = id;
        this.post_id = post_id;
        this.owner = owner;
        this.text = text;
        this.created_at = created_at;
    }

    public Comment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Comment getEx(int post_id){
        return new Comment(Utils.random(),post_id,Utils.random(),Utils.randomS(40), Date.from(Instant.now()).toString());
    }
}