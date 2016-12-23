package com.mg.api.model;

import java.util.Date;

public class Resource {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column resource.id
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column resource.uid
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    private Long uid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column resource.type
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column resource.use_cnt
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    private Integer useCnt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column resource.size
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    private Integer size;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column resource.create_date
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    private Date createDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column resource.id
     *
     * @return the value of resource.id
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column resource.id
     *
     * @param id the value for resource.id
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column resource.uid
     *
     * @return the value of resource.uid
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public Long getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column resource.uid
     *
     * @param uid the value for resource.uid
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column resource.type
     *
     * @return the value of resource.type
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column resource.type
     *
     * @param type the value for resource.type
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column resource.use_cnt
     *
     * @return the value of resource.use_cnt
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public Integer getUseCnt() {
        return useCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column resource.use_cnt
     *
     * @param useCnt the value for resource.use_cnt
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public void setUseCnt(Integer useCnt) {
        this.useCnt = useCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column resource.size
     *
     * @return the value of resource.size
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public Integer getSize() {
        return size;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column resource.size
     *
     * @param size the value for resource.size
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column resource.create_date
     *
     * @return the value of resource.create_date
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column resource.create_date
     *
     * @param createDate the value for resource.create_date
     *
     * @mbggenerated Fri Dec 23 11:53:28 CST 2016
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}