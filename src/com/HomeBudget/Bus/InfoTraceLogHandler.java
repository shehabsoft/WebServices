package com.HomeBudget.Bus;

import org.apache.log4j.Logger;

import com.HomeBudget.DAO.InfoTraceLogDAO;
import com.HomeBudget.DAO.InfoTraceLogDAOImpl;
import com.HomeBudget.dataObject.InfoTraceLogVO;

public class InfoTraceLogHandler {

	Logger logger = Logger.getLogger(InfoTraceLogHandler.class);
	
	public void add(InfoTraceLogVO infoTraceLogVO) throws Exception
	{
		try
		{
			logger.info("Add Trace Log ....");
			InfoTraceLogDAO infoTraceLogDAO=new InfoTraceLogDAOImpl();
			infoTraceLogDAO.addTraceLog(infoTraceLogVO);
			
		}catch(Exception e)
		{
			logger.error(e);
			throw new Exception(e);
		}
	}
}
