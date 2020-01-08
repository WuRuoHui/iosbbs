package com.wu.manager.pojo;

public class FriendlyLink {
    private Integer linkId;

    private String logo;

    private String websiteName;

    private String websiteUrl;

    private String masterEmail;

    private Long addTime;

    private Long modifyTime;

    private String showAddress;

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName == null ? null : websiteName.trim();
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl == null ? null : websiteUrl.trim();
    }

    public String getMasterEmail() {
        return masterEmail;
    }

    public void setMasterEmail(String masterEmail) {
        this.masterEmail = masterEmail == null ? null : masterEmail.trim();
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getShowAddress() {
        return showAddress;
    }

    public void setShowAddress(String showAddress) {
        this.showAddress = showAddress == null ? null : showAddress.trim();
    }
}