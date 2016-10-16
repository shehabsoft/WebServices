package com.dataObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.ws.WebServiceException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.HomeBudget.Sessions.CategorySession;
import com.HomeBudget.common.GlobalUtilities;
import com.HomeBudget.common.SoapUtils;

public  class TransactionServiceParser {

    /**
     * Parse create transaction request.
     * 
     * @param request Transaction data in XML format.
     * @return Transaction parameters.
     */
    public TransactionVO parseCreateTransaction(String request) {
        if (GlobalUtilities.isBlankOrNull(request)) {
            try {
				throw new Exception("�� ��� ������ �� ������");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        try
        {
        InputStream in = null;
    
            request = request.replaceAll("<!\\[CDATA\\[", "");
            request = request.replaceAll("\\]\\]>", "");
            // Parse config file
            byte[] requestBytes = request.getBytes("UTF-8");
            in = new ByteArrayInputStream(requestBytes);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(in);

            // Get root element
            Node root = getNode(doc, "createTransaction", true);
            TransactionVO transactionVO=getTransaction(root);
            return transactionVO;
            // Get transaction mandatory parameters
            
        }catch(Exception e)
        {
        	System.out.println(e);
        }
            return null;
    }
    /**
     * Get transaction value object.
     *
     * @param request HTTP request object.
     * @return transaction value object.
     */
    private TransactionVO getTransaction(Node root)
            throws ParseException {
    	 // Create transaction VO
        TransactionVO trsVO = null;
     
    	Integer serviceCode = new Integer(getNodeValue(root, "serviceCode", true, true));
        if (serviceCode==1) {
            trsVO = getCategoryInfo(root);
        }else if(serviceCode==2)
        {
        	  trsVO = getPurchaseInfo(root);
        }else if(serviceCode==3)
        {
        	 trsVO = getLocationInfo(root);
        }
        return trsVO;
    }
    public TransactionVO getCategoryInfo(Node root)
    {
    	 // Create transaction VO
        TransactionVO trsVO = null;
        CategoryVO categoryVO=new CategoryVO();
        Integer serviceCode = new Integer(getNodeValue(root, "serviceCode", true, true));
        String arabicDescription = getNodeValue(root, "arabicDescription", true, true);
        String englishDescription = getNodeValue(root, "englishDescription", true, true);
        String limitValue = getNodeValue(root, "limitValue", true, true);
        String planedValue = getNodeValue(root, "planedValue", true, true);
        String categoryStatus = getNodeValue(root, "categoryStatus", true, true);
        String categoryType = getNodeValue(root, "categoryType", true, true);
        String actualValue = getNodeValue(root, "actualValue", true, true);
        trsVO=new TransactionVO();
        trsVO.setServiceCode(serviceCode);
        categoryVO.setArabicDescription(arabicDescription);
        categoryVO.setEnglisDescription(englishDescription);
        categoryVO.setPlanedValue(Double.parseDouble(planedValue));
        categoryVO.setLimit_value(Double.parseDouble(limitValue));
        categoryVO.setCategoryStatus(Integer.parseInt(categoryStatus));
        categoryVO.setActualValue(Double.parseDouble(actualValue));
        categoryVO.setCategoryTypeId(Integer.parseInt(categoryType));
        trsVO.setCategoryVO(categoryVO);
        
        return  trsVO;
        
    }
    public TransactionVO getPurchaseInfo(Node root)
    {
   	 // Create transaction VO
        TransactionVO trsVO = null;
        PurchaseVO purchaseVO=new PurchaseVO();
        Integer serviceCode = new Integer(getNodeValue(root, "serviceCode", true, true));
        String arabicDescription = getNodeValue(root, "arabicDescription", true, true);
        String englishDescription = getNodeValue(root, "englishDescription", true, true);
        String price = getNodeValue(root, "price", true, true);
        String details = getNodeValue(root, "details", true, true);
        String categoryId = getNodeValue(root, "categoryId", true, true);
        String locationId = getNodeValue(root, "locationId", true, true);
        trsVO=new TransactionVO();
        trsVO.setServiceCode(serviceCode);
        purchaseVO.setArabicDescription(arabicDescription);
        purchaseVO.setEnglishDescription(englishDescription);
        purchaseVO.setPrice(Double.parseDouble(price));
        purchaseVO.setDetails(details);
        purchaseVO.setCategoryId(Integer.parseInt(categoryId));
        purchaseVO.setLocationId(Integer.parseInt(locationId));
        trsVO.setPurchaseVO(purchaseVO);
        
        return  trsVO;
    	
    	
    }
    public TransactionVO getLocationInfo(Node root)
    {
   	 // Create transaction VO
        TransactionVO trsVO = null;
        LocationVO locationVO=new LocationVO();
        Integer serviceCode = new Integer(getNodeValue(root, "serviceCode", true, true));
        String arabicDescription = getNodeValue(root, "arabicDescription", true, true);
        String englishDescription = getNodeValue(root, "englishDescription", true, true);
        trsVO=new TransactionVO();
        trsVO.setServiceCode(serviceCode);
        locationVO.setArabicName(arabicDescription);
        locationVO.setEnglishName(englishDescription);
        trsVO.setLocationVO(locationVO);
        
        return  trsVO;
    	
    	
    }
        /**
         * Get document node by name.
         * 
         * @param name node name.
         * @return node object.
         */
        private Node getNode(Document doc, String name, boolean mandatory) {
            NodeList nodesList = doc.getElementsByTagName(name);
            if (nodesList == null || nodesList.getLength() == 0) {
               if (mandatory) {
                   throw new WebServiceException("Node not found: " + name);
               } else {
                   return null;
               }
            }
            
            return nodesList.item(0);
        }  
        /**
         * Get node text value.
         * 
         * @param parent Parent XML node.
         * @param nodeName Node name to be parsed.
         * @return Node text value.
         */
        private String getNodeValue(Node parent, String nodeName, boolean mandatory, boolean trim) {

            if (parent == null)  {
                return null;
            }
            NodeList childNodes = parent.getChildNodes();
            if (childNodes == null || childNodes.getLength() == 0) {
                if (mandatory == true) {
                    throw new WebServiceException("Parent node has childe nodes");
                }
                
                return null;
            }
            
            for (int i = 0; i < childNodes.getLength(); i++)  {
                Node node = childNodes.item(i);
                
                if (! node.getNodeName().equals(nodeName)) {
                    continue;
                }
                
                String textContent = SoapUtils.getTextContent(node);
                if (textContent != null && trim) {
                    textContent = textContent.trim();
                }

                return textContent;
            }

            if (mandatory == true) { 
                throw new WebServiceException("Element not found: " + nodeName);
            }
            
            return null;
        }


}