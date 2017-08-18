package com.system.dictionary.bean;

public class Dictionary {
    private Integer id;
    private String kind; //什么种类的，同一种类kind相同。
    private Integer sequence;//排序字段
    private String name;//名称
    private String comment;//备注
    
    private String[] sequences;
    private String[] names;
    private String[] comments;
    
    private String oldIds;
    private Integer[] ids;
    
    
    public String getOldIds() {
        return oldIds;
    }
    public void setOldIds(String oldIds) {
        this.oldIds = oldIds;
    }
    public Integer[] getIds() {
        return ids;
    }
    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
    public String[] getSequences() {
        return sequences;
    }
    public void setSequences(String[] sequences) {
        this.sequences = sequences;
    }
    public String[] getNames() {
        return names;
    }
    public void setNames(String[] names) {
        this.names = names;
    }
    public String[] getComments() {
        return comments;
    }
    public void setComments(String[] comments) {
        this.comments = comments;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getKind() {
        return kind;
    }
    public void setKind(String kind) {
        this.kind = kind;
    }
    public Integer getSequence() {
        return sequence;
    }
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    
}
