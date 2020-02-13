package com.wu.bbs.pojo;

public class UserGrade {

    private Integer id;

    private String gradeIcon;

    private String gradeName;

    private Integer gradePoint;

    private Integer gradeGold;

    private Integer gradeValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGradeIcon() {
        return gradeIcon;
    }

    public void setGradeIcon(String gradeIcon) {
        this.gradeIcon = gradeIcon == null ? null : gradeIcon.trim();
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName == null ? null : gradeName.trim();
    }

    public Integer getGradePoint() {
        return gradePoint;
    }

    public void setGradePoint(Integer gradePoint) {
        this.gradePoint = gradePoint;
    }

    public Integer getGradeGold() {
        return gradeGold;
    }

    public void setGradeGold(Integer gradeGold) {
        this.gradeGold = gradeGold;
    }

    public Integer getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(Integer gradeValue) {
        this.gradeValue = gradeValue;
    }
}