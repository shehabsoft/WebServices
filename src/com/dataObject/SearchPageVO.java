/*
 * Copyright (c) i-Soft 2003.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 * ver    Developer          Date        Comments
 * ----- -----------------  ----------  ----------------------------------------
 * 1.00  Eng. Ayman Atiyeh  14/01/2008  - File created.
 */

package com.dataObject;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * Search result value object. Used to encapsulate search results info.
 *
 * @author Eng. Ayman Atiyeh
 * @version 1.00
 */
public class SearchPageVO implements Serializable {
    @SuppressWarnings("compatibility:-3356097904910351094")
    private static final long serialVersionUID = 4438396215451622095L;
    /*
     * Instance variables.
     */
    
    /** List of search result value objects. */
    private List records = new ArrayList();
    
    /** Page number. */
    private int pageNo;
    
    /** Search page size. */
    private int searchPageSize;

    /** Total records count. */
    private int totalRecords;
    
    /*
     * Constructors.
     */

    /**
     * Construct new SearchPageVO object.
     */
    public SearchPageVO() {
        setSearchPageSize(10);
    }

    /**
     * Construct new SearchPageVO object.
     * 
     * @param pageNo Page number.
     * @param pageSize Search page size.
     */
    public SearchPageVO(int pageNo, int pageSize) {
        setPageNo(pageNo);
        setSearchPageSize(pageSize);
    }

    /**
     * Construct new Page object.
     * 
     * @param pageNo Page number.
     * @param pageSize Search page size.
     * @param total Total records count.
     */
    public SearchPageVO(int pageNo, int pageSize, int total) {
        this(pageNo, pageSize);
        setTotalRecords(total);
    }

    /*
     * Methods
     */

    /**
     * Returns the String representation of this object.
     * @return String representation of this object.
     */
    public String toString() {
        StringBuffer buf = new StringBuffer(getClass().getName());
        buf.append("{")
           .append(  "pageNo=").append(pageNo)
           .append(", searchPageSize=").append(searchPageSize)
           .append(", totalRecords=").append(totalRecords)
           .append(", records[").append(records.size()).append("]")
           .append( "}");

        return buf.toString();
    }

    /**
     * Get page number.
     * 
     * @return page number.
     */
    public int getPageNo() {
        return this. pageNo;
    }

    /**
     * Set page number.
     * 
     * @param no page number.
     */
    public void setPageNo(int no) {
        if (no < 0) {
            throw new IllegalArgumentException("Invalid pageNo: " + no);
        }

        this.pageNo = no;
    }

    /**
     * Get search page size.
     * 
     * @return search page size.
     */
    public int getSearchPageSize() {
        return this.searchPageSize;
    }

    /**
     * Set search page size.
     * 
     * @param size search page size.
     */
    public void setSearchPageSize(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Invalid searchPageSize: " + size);
        }

        this.searchPageSize = size;
    }

    /**
     * Get Total records count.
     * 
     * @return Total records count.
     */
    public int getTotalRecords() {
        return this.totalRecords;
    }

    /**
     * Set Total records count.
     * @param count Total records count.
     */
    public void setTotalRecords(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Invalid totalRecords: " + count);
        }

        this.totalRecords = count;
    }

    /**
     * Get search result value objects.
     * 
     * @return search result value objects.
     */
    public Object[] getRecords() {
        return (Object[]) records.toArray(new Object[records.size()]);
    }

    /**
     * Add new record.
     * 
     * @param rec new record.
     */
    public void addRecord(Serializable rec) {
        if (rec == null) {
            throw new IllegalArgumentException("Null records are not allowed");
        }

        this.records.add(rec);
    }
    
    /**
     * Add List of Serializable Record.
     * 
     * @param rec List of new records.
     */
    public void addAllRecord(List<Serializable> rec) {
        
        if (rec == null) {
            throw new IllegalArgumentException("Null records are not allowed");
        }

        this.records.addAll(rec);
    }

    /**
     * Get current page size.
     * 
     * @return current page size.
     */
    public int getCurrentPageSize() {
        return this.records.size();
    }

    /**
     * Removes all records in this page.
     */
    public void clear() {
        this.records.clear();
    }
    public List readRecords() {
        return records;
    }
}