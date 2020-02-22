package com.wu.manager.pojo;

public class Passageway {
    private Integer id;

    private String passagewayLogo;

    private String name;

    private String url;

    private Long gmtCreate;

    private Long gmtModify;

    private String showAddress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassagewayLogo() {
        return passagewayLogo;
    }

    public void setPassagewayLogo(String passagewayLogo) {
        this.passagewayLogo = passagewayLogo == null ? null : passagewayLogo.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Long gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getShowAddress() {
        return showAddress;
    }

    public void setShowAddress(String showAddress) {
        this.showAddress = showAddress == null ? null : showAddress.trim();
    }
}