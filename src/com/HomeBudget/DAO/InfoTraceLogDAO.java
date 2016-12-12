/**
 * 
 */
package com.HomeBudget.DAO;

import com.HomeBudget.DAO.JPA.DataAccessObject1;
import com.HomeBudget.dataObject.InfoTraceLogVO;

/**
 * @author Shehab
 *
 */
public interface InfoTraceLogDAO extends DataAccessObject1{
	public void addTraceLog(InfoTraceLogVO infoTraceLogVO) throws Exception;

}
