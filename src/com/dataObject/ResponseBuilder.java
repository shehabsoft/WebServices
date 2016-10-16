package com.dataObject;

public abstract class ResponseBuilder {

    /** XML file header. */
    private static final String XML_HEADER = 
            "<?xml version='1.0' encoding='utf-8'?>\n";


    /**
     * Build create transaction response.
     * 
     * @param ex generated exception.
     * @return Response XML message.
     */
    public static String getCreateTransactionResponse(TransactionVO trsVO) {
        StringBuffer xml = new StringBuffer();
        
        xml.append(XML_HEADER)
           .append("<transactionResponse>")
           .append("<transaction-info>")
           .append("<status>").append(TransactionTypes.TRS_STATUS_CERTIFIED).append("</status>\n")
       
           .append("<transactionId>")
          // .append(trsVO.getId())
           .append("</transactionId>")
           .append("</transaction-info>")
           .append("</transactionResponse>");
           

        return xml.toString();
    }
    /**
     * Validate Violations
     * 
     * @param violation Violation
     * 
     * @return violation message
     */
    private static String validateViolations(String violation) {
        if(violation != null && !violation.equalsIgnoreCase("")) {
            int index = violation.indexOf("wsTransaction.do") ;
            if(index >= 0 ) {
                violation = violation.substring(0,index);
            }
        }
        
        return violation;
    }
    /**
     * Build create transaction response.
     * 
     * @param ex generated exception.
     * @return Response XML message.
     */
    public static String getCreateTransactionResponse(Exception ex) {
        StringBuffer xml = new StringBuffer();
        xml.append(XML_HEADER)
           .append("<transactionResponse>\n")           
           .append("  <transaction-info>\n")
           .append("  <status>").append(TransactionTypes.TRS_STATUS_FAILED).append("</status>\n")
           .append("    <violations>\n");
        
        String[] violationsAr = null;
        String[] violationsEn = null;
        if (ex instanceof TransactionException) {
            TransactionException trsEx = (TransactionException) ex;
            violationsAr = trsEx.getViolationsAr();
            violationsEn = trsEx.getViolationsEn();
            
        } else if (ex instanceof RuleException) {
            RuleException ruleEx = (RuleException) ex;
//            BusinessRuleVO businessRuleVO = preferencesHandler.getBusinessRule(ruleEx);
//            violationsAr = new String[]{businessRuleVO.getDescriptionAr()};
//            violationsEn = new String[]{ruleEx.getErrorCode() + STRING_SEPARATOR + businessRuleVO.getDescriptionEn()};
            
        } else if (ex instanceof BrException) {
//            Map violationMap = getBrMessages(ex);
//            violationsAr = (String[])violationMap.get("AR");
//            violationsEn = (String[])violationMap.get("EN");
            
        } else {
            violationsAr = new String[]{ex.getMessage()};
            violationsEn = new String[]{ex.getMessage()};
        }

        for (int i = 0; violationsAr != null && i < violationsAr.length; i++)  {
            xml.append("      <violation>\n")
               .append("        <description-ar>")
               .append(          "<![CDATA[")
               .append(             validateViolations(violationsAr[i]))
               .append(             "]]>")
               .append(        "</description-ar>\n")
               .append("        <description-en>")
               .append(          "<![CDATA[")
               .append(             validateViolations(violationsEn[i]))
               .append(             "]]>")
               .append(        "</description-en>\n")
               .append("      </violation>\n");
        }
        
        xml.append("    </violations>\n")
           .append("  </transaction-info>\n")
           .append("</transactionResponse>");
        
        return xml.toString();
    }
}
