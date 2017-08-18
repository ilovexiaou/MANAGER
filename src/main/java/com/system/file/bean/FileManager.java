package com.system.file.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FileManager implements Serializable{
    private Integer id;
    private String file_name;
    private String file_realname;
    private String file_realpath;
    private String kind;
    private String comment;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getFile_name() {
        return file_name;
    }
    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
    public String getFile_realname() {
        return file_realname;
    }
    public void setFile_realname(String file_realname) {
        this.file_realname = file_realname;
    }
    public String getFile_realpath() {
        return file_realpath;
    }
    public void setFile_realpath(String file_realpath) {
        this.file_realpath = file_realpath;
    }
    public String getKind() {
        return kind;
    }
    public void setKind(String kind) {
        this.kind = kind;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
}
