package com.qifangli.edumanage.dao.entity;

public class Score {
    private String stuId;
    private String teaCrsId;
    private Integer scorePro;
    private Integer scoreEnd;
    private Integer score;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getTeaCrsId() {
        return teaCrsId;
    }

    public void setTeaCrsId(String teaCrsId) {
        this.teaCrsId = teaCrsId;
    }

    public Integer getScorePro() {
        return scorePro;
    }

    public void setScorePro(Integer scorePro) {
        this.scorePro = scorePro;
    }

    public Integer getScoreEnd() {
        return scoreEnd;
    }

    public void setScoreEnd(Integer scoreEnd) {
        this.scoreEnd = scoreEnd;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
