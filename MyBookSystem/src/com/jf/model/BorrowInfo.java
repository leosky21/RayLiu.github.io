package com.jf.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 借阅信息类
 * @author weidong
 */
public class BorrowInfo implements Serializable {

    private Integer id;    //借阅编号
    private Book book;    //借阅书籍
    private Reader reader;    //借阅读者
    private Date borrowDate;    //借阅日期
    private Admin admin;    //操作员
    private Date endDate;    //截止日期
    private Double penalty;    //每日罚金
    private Integer overday;    //逾期天数
    private Integer state; //状态 (未归还=0,逾期未归还=1,归还=2,续借未归还=3,续借逾期未归还=4,续借归还=5)


    //2018年5月15日10:42:05
    private Integer fk_reader;    //借阅读者id
    private Integer fk_admin;    //操作的管理员id
    private Integer fk_book;    //借阅书籍id


    public BorrowInfo() {
    }

    public BorrowInfo(Integer id) {
        this.id = id;
    }

    public Double getPenalty() {
        return penalty;
    }

    public void setPenalty(Double penalty) {
        this.penalty = penalty;
    }

    public Integer getOverday() {
        return overday;
    }

    public void setOverday(Integer overday) {
        this.overday = overday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getFk_reader() {
        return fk_reader;
    }

    public void setFk_reader(Integer fk_reader) {
        this.fk_reader = fk_reader;
    }

    public Integer getFk_admin() {
        return fk_admin;
    }

    public void setFk_admin(Integer fk_admin) {
        this.fk_admin = fk_admin;
    }

    public Integer getFk_book() {
        return fk_book;
    }

    public void setFk_book(Integer fk_book) {
        this.fk_book = fk_book;
    }
}
