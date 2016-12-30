package com.mg.api.model;

import java.util.Date;

public class Photo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column photo.id
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column photo.uid
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    private Long uid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column photo.aid
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    private Long aid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column photo.introduce
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    private String introduce;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column photo.rid
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    private String rid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column photo.tag
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    private String tag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column photo.view_cnt
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    private Integer viewCnt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column photo.like_cnt
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    private Integer likeCnt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column photo.reply_cnt
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    private Integer replyCnt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column photo.create_date
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column photo.update_date
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    private Date updateDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column photo.id
     *
     * @return the value of photo.id
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column photo.id
     *
     * @param id the value for photo.id
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column photo.uid
     *
     * @return the value of photo.uid
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public Long getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column photo.uid
     *
     * @param uid the value for photo.uid
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column photo.aid
     *
     * @return the value of photo.aid
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public Long getAid() {
        return aid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column photo.aid
     *
     * @param aid the value for photo.aid
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public void setAid(Long aid) {
        this.aid = aid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column photo.introduce
     *
     * @return the value of photo.introduce
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column photo.introduce
     *
     * @param introduce the value for photo.introduce
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column photo.rid
     *
     * @return the value of photo.rid
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public String getRid() {
        return rid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column photo.rid
     *
     * @param rid the value for photo.rid
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public void setRid(String rid) {
        this.rid = rid == null ? null : rid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column photo.tag
     *
     * @return the value of photo.tag
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public String getTag() {
        return tag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column photo.tag
     *
     * @param tag the value for photo.tag
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column photo.view_cnt
     *
     * @return the value of photo.view_cnt
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public Integer getViewCnt() {
        return viewCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column photo.view_cnt
     *
     * @param viewCnt the value for photo.view_cnt
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public void setViewCnt(Integer viewCnt) {
        this.viewCnt = viewCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column photo.like_cnt
     *
     * @return the value of photo.like_cnt
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public Integer getLikeCnt() {
        return likeCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column photo.like_cnt
     *
     * @param likeCnt the value for photo.like_cnt
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public void setLikeCnt(Integer likeCnt) {
        this.likeCnt = likeCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column photo.reply_cnt
     *
     * @return the value of photo.reply_cnt
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public Integer getReplyCnt() {
        return replyCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column photo.reply_cnt
     *
     * @param replyCnt the value for photo.reply_cnt
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public void setReplyCnt(Integer replyCnt) {
        this.replyCnt = replyCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column photo.create_date
     *
     * @return the value of photo.create_date
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column photo.create_date
     *
     * @param createDate the value for photo.create_date
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column photo.update_date
     *
     * @return the value of photo.update_date
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column photo.update_date
     *
     * @param updateDate the value for photo.update_date
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}