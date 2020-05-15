package com.melons.game.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MelonData {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("skillbuild")
    @Expose
    private String skillbuild;
    @SerializedName("skillbought")
    @Expose
    private String skillbought;
    @SerializedName("seedcurrency")
    @Expose
    private String seedcurrency;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSkillbuild() {
        return skillbuild;
    }

    public void setSkillbuild(String skillbuild) {
        this.skillbuild = skillbuild;
    }

    public String getSkillbought() {
        return skillbought;
    }

    public void setSkillbought(String skillbought) {
        this.skillbought = skillbought;
    }

    public String getSeedcurrency() {
        return seedcurrency;
    }

    public void setSeedcurrency(String seedcurrency) {
        this.seedcurrency = seedcurrency;
    }

}

