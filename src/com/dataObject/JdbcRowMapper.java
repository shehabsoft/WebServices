//package ae.rta.util.dao.jdbc;
package com.dataObject;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Used by {@link ae.rta.util.dao.jdbc.JdbcDataAccessObject} for mapping rows of a 
 * {@link java.sql.ResultSet} on a per-row basis.
 * 
 * Implementations of this interface perform the actual work of mapping each row to a result object.
 */
public interface JdbcRowMapper  {

    /**
     * Implementations must implement this method to map each row of data in the {@link java.sql.ResultSet}. 
     * This method should not call {@link java.sql.ResultSet#close} or {@link java.sql.ResultSet#next} on the ResultSet; it is only supposed to map values of the current row.
     * @throws java.sql.SQLException
     * @return The result object for the current row
     * @param rs
     */
    public Object mapRow(ResultSet rs) throws SQLException;
}