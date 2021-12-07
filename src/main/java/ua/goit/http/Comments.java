package ua.goit.http;

import java.util.Objects;

public class Comments {
    private int postID;
    private int id;
    private String name;
    private String mail;
    private String body;

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comments comments = (Comments) o;
        return postID == comments.postID && id == comments.id && Objects.equals(name, comments.name) && Objects.equals(mail, comments.mail) && Objects.equals(body, comments.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postID, id, name, mail, body);
    }

    @Override
    public String toString() {
        return "Comments{" +
                "postID=" + postID +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
