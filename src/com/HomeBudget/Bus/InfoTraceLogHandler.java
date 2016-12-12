package com.HomeBudget.Bus;

import org.apache.log4j.Logger;

import com.HomeBudget.DAO.InfoTraceLogDAO;
import com.HomeBudget.DAO.InfoTraceLogDAOImpl;
import com.HomeBudget.dataObject.InfoTraceLogVO;
import com.dataObject.BusinessException;
import com.dataObject.BusinessObject;
import com.dataObject.DataAccessException;

public class InfoTraceLogHandler extends BusinessObject {

	Logger logger = Logger.getLogger(InfoTraceLogHandler.class);
	
	public void add(InfoTraceLogVO infoTraceLogVO) throws Exception
	{
		InfoTraceLogDAO infoTraceLogDAO=null;
		try
		{
			logger.info("Add Trace Log ....");
			infoTraceLogDAO=(InfoTraceLogDAO)getDAO(InfoTraceLogDAO.class);
			infoTraceLogDAO.addTraceLog(infoTraceLogVO);
			infoTraceLogDAO.commit();
			
		}catch (DataAccessException ex) {
		 logger.error(ex);
		  throw ex;
		  } catch (BusinessException ex) {
		  logger.error(ex);
		    throw ex;
		   } catch (Exception ex) {
		    logger.error(ex);
		    throw new BusinessException(ex);
		      } finally {
		        close(infoTraceLogDAO);
		      }
	}
}
