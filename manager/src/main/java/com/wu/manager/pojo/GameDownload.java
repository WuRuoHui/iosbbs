package com.wu.manager.pojo;

import lombok.ToString;

@ToString
public class GameDownload {
    private Integer id;

    private Integer gameId;

    private String url;

    private Integer mix;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getMix() {
        return mix;
    }

    public void setMix(Integer mix) {
        this.mix = mix;
    }
}