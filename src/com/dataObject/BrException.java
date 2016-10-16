package com.dataObject;



import java.util.List;


import java.util.ArrayList;
import java.util.StringTokenizer;

import com.HomeBudget.common.GlobalUtilities;

public class BrException extends RuntimeException {                   
    /*
     * Instance variables
     */
    private ArrayList violationList= null;
    private boolean   hasErrors= true;
    
    
    /*
     * Constructors
     */

    public BrException() {
        // Empty body
    }

    public BrException(ArrayList list, boolean hasErrors) {
        violationList = list;         
        this.hasErrors = hasErrors;
    }
    
    /*
     * Methods
     */

    public void setViolations(ArrayList list) {
      violationList = list;
    }

    public ArrayList getViolationList() {
      return violationList;
    }

    public String getMessage() {
        StringBuffer msg= new StringBuffer("");

        if (violationList != null) {
            BrViolation brv= null;
            
            for (int i = 0; i < violationList.size(); i++) {
                brv= (BrViolation) violationList.get(i);

                if (brv.getViolationType() == brv.BR_ERROR) {
                    msg.append( "Error   :: ");
                } else {
                    msg.append( "Warning :: ");
                }
                
                String brDesc = appendParamsToMsg (
                                new StringBuffer(
                                    GlobalUtilities.isBlankOrNull(brv.getViolationMsg()) 
                                        ? getViolationMsg(brv.getBrName())
                                        : brv.getViolationMsg()
                                                ),brv.getBrParameters() );                
                msg.append(brv.getBrName()).append(" :: ").append(brDesc).append("\n");
            }
        }

        return msg.toString();
    }

    

    private String getViolationMsg(String brName) {
        return BrValidator.getBrMessage(brName);
    }

    private String appendParamsToMsg(StringBuffer brDesc, StringBuffer brParams) {
        if ( brParams == null )
            return brDesc.toString();
            
        StringTokenizer st = new StringTokenizer(brParams.toString(),",");
        int startIndex     = 0;
        int endIndex       = 0;
        
        while ( st.hasMoreTokens() )
        {   
            startIndex = brDesc.toString().toUpperCase().indexOf("<P>");            
            endIndex   = brDesc.toString().indexOf(">",startIndex);

            if(startIndex == -1) {
                break;
            }
            
            if ( st.countTokens() == 1 && startIndex == -1 && endIndex == -1 )
                return brDesc.toString();
                
            if ( startIndex != -1 && endIndex != -1 && endIndex > startIndex )              
                brDesc.replace(startIndex,endIndex+1,st.nextToken());                
        }
        
        return brDesc.toString();
    }

  
  
   

    //overloaded versions for backward compatibility as canBeSkipped may be
    //called from several places
    public static boolean canBeSkipped(String brLabel, ArrayList userGroups) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT 1 FROM TF_STP_SKIPPED_BRS")
           .append(" WHERE BRS_BR_LABEL='").append(brLabel).append("'")
           .append(  " AND GRP_GRP_ID IN ")
           .append(userGroups.toString().replace('[','(').replace(']',')'));
                  
        return (JdbcAdapter.selectIntValue(sql.toString(), -1) == 1);
    }
    
    /**
     * Is Dubai E-Gov Exception
     * return true if br name is BR_TRS079_MER or BR_TRS080_MER
     * 
     * @return boolean ( true / false) 
     */
    public boolean isDegException() {  
            
        if (getViolationList() != null && getViolationList().size() > 0) {
            for (int i = 0; i < getViolationList().size(); i++)  {
                List violations = getViolationList();
                BrViolation brViolation = (BrViolation)violations.get(i);                    
                String violationStr = brViolation.getBrName();
                if(violationStr != null && 
                    ( violationStr.equalsIgnoreCase("BR_TRS079_MER") ||
                      violationStr.equalsIgnoreCase("BR_TRS080_MER")) ) {
                    return true;
                }   
                return false;  
            }
        }     
        return true;
    }
    
    /**
     * Is Need Person Picture Exception
     * return true if contains br name (BR_ISP040_MER)
     * 
     * @return boolean ( true / false) 
     */
    public boolean isNeedPersonPictureException() {
        
        List violations = getViolationList();
        
        if (violations != null && violations.size() > 0) {
        
            for (int i = 0; i < violations.size(); i++)  {
            
                BrViolation brViolation = (BrViolation)violations.get(i);
                String violationStr = brViolation.getBrName();
                
                if(violationStr != null && 
                    violationStr.equalsIgnoreCase("BR_ISP040_MER")) {
                    return true;
                }
            }
        }
        
        return false;
    }
 
}