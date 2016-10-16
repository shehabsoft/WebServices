package com.dataObject;

import java.io.Serializable;

public class BrViolation implements Serializable {
    public static final byte BR_WARNING  = 0;
    public static final byte BR_ERROR    = 1;    

    private byte violationType         = BR_ERROR; //default is error
    private String brName              = null;
    private String violationMsg        = null;
    private StringBuffer brParameters  = null;
    private StringBuffer brParametersEn = null;

    public BrViolation(String brName)
    {
        this.brName= brName;
    }

    public BrViolation(String brName, byte violationType)
    {
        this.brName= brName;
        this.violationType= violationType;
    }

    public BrViolation(String brName, StringBuffer brParameters)
    {
        this.brName = brName;
        this.brParameters = brParameters; 
    }
    
    public BrViolation(String brName, StringBuffer brParameters,StringBuffer brParametersEn) {
        this.brName = brName;
        this.brParameters = brParameters; 
        this.brParametersEn = brParametersEn;
    }

    public byte getViolationType()
    {
      return violationType;
    }
    
    public void setViolationType(byte i)
    {
      violationType= i;
    }   

    public String getBrName()
    {
      return brName;
    }
    
    public void setBrName(String name, boolean bRetrieveMsg )
    {
      brName= name;

      if ( bRetrieveMsg )
      {
        //add code to get error message
      }
    }

    public void setBrParameters(StringBuffer brParams)
    {
        brParameters = brParams;
    }

    public StringBuffer getBrParameters()
    {
        return brParameters;
    }

    public String getViolationMsg()
    {
      return violationMsg;
    }
    
    public void setViolationMsg(String str)
    {
      violationMsg= str;
    }   
    
    /**
     * Setter method for brParametersEn
     * 
     * @param brParametersEn
     */
    public void setBrParametersEn(StringBuffer brParametersEn) {
        this.brParametersEn = brParametersEn;
    }

    /**
     * Getter method for brParametersEn
     * 
     * @return brParametersEn
     */
    public StringBuffer getBrParametersEn() {
        return brParametersEn;
    }
    
    
}