package com.example.bookapp.models;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.Date;

public class Photo implements Serializable {
    int book_id;
    String title;
    String author;
    int category_id;
    Date published_date;
    String cover_image;
    String file_path;

    // Constructor
    public Photo(int id, String title, String cover_image) {
        this.book_id = id;
        this.title = title;
        this.cover_image = cover_image;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public Date getPublished_date() {
        return published_date;
    }

    public void setPublished_date(Date published_date) {
        this.published_date = published_date;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
}