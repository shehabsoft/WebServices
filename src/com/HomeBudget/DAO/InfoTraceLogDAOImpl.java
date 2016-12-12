package com.HomeBudget.DAO;
import org.apache.log4j.Logger;

import com.HomeBudget.DAO.JPA.JPADataAccessObject;
import com.HomeBudget.dataObject.InfoTraceLogVO;
import com.entities.models.InfoTraceLog;

public class InfoTraceLogDAOImpl extends  JPADataAccessObject  implements InfoTraceLogDAO {

	
	Logger logger = Logger.getLogger(InfoTraceLogDAOImpl.class);
	
	public InfoTraceLogDAOImpl()
	{
		super();
	}
	
	@Override
	public void addTraceLog(InfoTraceLogVO infoTraceLogVO) throws Exception {
		// TODO Auto-generated method stub
		try
		{
		logger.info("Add Trace Log");
		InfoTraceLog infoTraceLog=new InfoTraceLog();
		infoTraceLog.setRequestData(infoTraceLogVO.getRequestData());
		infoTraceLog.setRequestDate(infoTraceLogVO.getCreationDate());
		infoTraceLog.setResponseData(infoTraceLogVO.getResponseData());
		infoTraceLog.setResponseDate(infoTraceLogVO.getResponseDate());
		getEntitymanager().persist(infoTraceLog);
		logger.info("Done Adding  Trace Log Info");
		}catch(Exception e)
		{
			logger.error(e);
			throw new Exception(e);
		}
		
	}

}
