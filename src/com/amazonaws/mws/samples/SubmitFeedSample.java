/******************************************************************************* 
 *  Copyright 2009 Amazon Services.
 *  Licensed under the Apache License, Version 2.0 (the "License"); 
 *  
 *  You may not use this file except in compliance with the License. 
 *  You may obtain a copy of the License at: http://aws.amazon.com/apache2.0
 *  This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR 
 *  CONDITIONS OF ANY KIND, either express or implied. See the License for the 
 *  specific language governing permissions and limitations under the License.
 * ***************************************************************************** 
 *
 *  Marketplace Web Service Java Library
 *  API Version: 2009-01-01
 *  Generated: Wed Feb 18 13:28:48 PST 2009 
 * 
 */

package com.amazonaws.mws.samples;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;

import javax.xml.bind.Element;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;

import com.amazonaws.mws.*;
import com.amazonaws.mws.model.*;
import com.sun.xml.txw2.Document;
import com.amazonaws.mws.mock.MarketplaceWebServiceMock;

/**
 * 
 * Submit Feed Samples
 * 
 * 
 */
public class SubmitFeedSample {

    /**
     * Just add a few required parameters, and try the service Submit Feed
     * functionality
     * 
     * @param args
     *            unused
     */
    /**
     * @param args
     */
	
	//helper to calculate md5 content needed for submit feed
	
	public static String computeContentMD5HeaderValue( FileInputStream fis )
			throws IOException, NoSuchAlgorithmException {

		
		
		DigestInputStream dis = new DigestInputStream( fis,
				MessageDigest.getInstance( "MD5" ));
				byte[] buffer = new byte[8192];
				while( dis.read( buffer ) > 0 );
				String md5Content = new String(
				org.apache.commons.codec.binary.Base64.encodeBase64(dis.getMessageDigest().digest())
				 );
				// Effectively resets the stream to be beginning of the file via a
				  fis.getChannel().position(0);
		
				return md5Content;
		

		
	}
    public static void main(String... args) throws NoSuchAlgorithmException, FileNotFoundException, IOException {

        /************************************************************************
         * Access Key ID and Secret Access Key ID, obtained from:
         * http://aws.amazon.com
         ***********************************************************************/
    	final String accessKeyId = "0";
        final String secretAccessKey = "0";

        final String appName = "Myawesomeapp";
        final String appVersion = "1.1.0";

        MarketplaceWebServiceConfig config = new MarketplaceWebServiceConfig();

        /************************************************************************
         * Uncomment to set the appropriate MWS endpoint.
         ************************************************************************/
        // US
         config.setServiceURL("https://mws.amazonservices.com");
        // UK
        // config.setServiceURL("https://mws.amazonservices.co.uk");
        // Germany
        // config.setServiceURL("https://mws.amazonservices.de");
        // France
        // config.setServiceURL("https://mws.amazonservices.fr");
        // Italy
        // config.setServiceURL("https://mws.amazonservices.it");
        // Japan
        // config.setServiceURL("https://mws.amazonservices.jp");
        // China
        // config.setServiceURL("https://mws.amazonservices.com.cn");
        // Canada
        // config.setServiceURL("https://mws.amazonservices.ca");
        // India
        // config.setServiceURL("https://mws.amazonservices.in");

        /************************************************************************
         * You can also try advanced configuration options. Available options are:
         *
         *  - Signature Version
         *  - Proxy Host and Proxy Port
         *  - User Agent String to be sent to Marketplace Web Service
         *
         ***********************************************************************/

        /************************************************************************
         * Instantiate Http Client Implementation of Marketplace Web Service        
         ***********************************************************************/

        MarketplaceWebService service = new MarketplaceWebServiceClient(
                accessKeyId, secretAccessKey, appName, appVersion, config);


        /************************************************************************
         * Setup request parameters and uncomment invoke to try out sample for
         * Submit Feed
         ***********************************************************************/

        /************************************************************************
         * Marketplace and Merchant IDs are required parameters for all
         * Marketplace Web Service calls.
         ***********************************************************************/
        final String merchantId = "0";
        final String sellerDevAuthToken = "0";
        // marketplaces to which this feed will be submitted; look at the
        // API reference document on the MWS website to see which marketplaces are
        // included if you do not specify the list yourself
        final IdList marketplaces = new IdList(Arrays.asList(
        		 "0"));

        SubmitFeedRequest request = new SubmitFeedRequest();
        
        request.setMerchant(merchantId);
        
       request.setMWSAuthToken(sellerDevAuthToken);
       
        request.setMarketplaceIdList(marketplaces);

        //need to generate XML to submit  _POST_PRODUCT_DATA_ for change title descrpition and etc	
        //update listing for price and inventory only _POST_FLAT_FILE_LISTINGS_DATA_ using xls
        //change item decsription _POST_PRODUCT_DATA_ using xml
        //change item price  _POST_PRODUCT_PRICING_DATA_ using xml
        //update inventory _POST_INVENTORY_AVAILABILITY_DATA_ using xml
        //Update order fulfillment tracking number _POST_ORDER_FULFILLMENT_DATA_  xml
        //Create new Product _POST_PRODUCT_DATA_ XML
        
        request.setFeedType("_POST_PRODUCT_DATA_");

        // MWS exclusively offers a streaming interface for uploading your
        // feeds. This is because
        // feed sizes can grow to the 1GB+ range - and as your business grows
        // you could otherwise
        // silently reach the feed size where your in-memory solution will no
        // longer work, leaving you
        // puzzled as to why a solution that worked for a long time suddenly
        // stopped working though
        // you made no changes. For the same reason, we strongly encourage you
        // to generate your feeds to
        // local disk then upload them directly from disk to MWS via Java -
        // without buffering them in Java
        // memory in their entirety.
        // Note: MarketplaceWebServiceClient will not retry a submit feed request
        // because there is no way to reset the InputStream from our client. 
        // To enable retry, recreate the InputStream and resubmit the feed
        // with the new InputStream. 
        //
        
    
        //make xml to change product description and son on 
        
        
        
        
        try {

    		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

    		// root elements
    		org.w3c.dom.Document doc = docBuilder.newDocument();
    		org.w3c.dom.Element rootElement = doc.createElement("AmazonEnvelope");
    		doc.appendChild(rootElement);
    		
    		//set Attributes of root element
    		Attr attr = doc.createAttribute("xmlns:xsi");
    		attr.setValue("http://www.w3.org/2001/XMLSchema-instance");
    		rootElement.setAttributeNode(attr);
    		
    		//set Attributes of root element
    		Attr attr1 = doc.createAttribute("xsi:noNamespaceSchemaLocation");
    		attr1.setValue("amzn-envelope.xsd");
    		rootElement.setAttributeNode(attr1);

    		// Append Header elements to root 
    		org.w3c.dom.Element Header = doc.createElement("Header");
    		rootElement.appendChild(Header);

    	
    		// append header elements to header
    		org.w3c.dom.Element DocumentVersion = doc.createElement("DocumentVersion");
    		DocumentVersion.appendChild(doc.createTextNode("1.01"));
    		Header.appendChild(DocumentVersion);

    		//  append header elements to header
    		org.w3c.dom.Element MerchantIdentifier = doc.createElement("MerchantIdentifier");
    		MerchantIdentifier.appendChild(doc.createTextNode("A3CQ5833KTZ4RX"));
    		Header.appendChild(MerchantIdentifier);

    		
  ///////The above header is the same for description, price and quantity xml, need to keep ////////////////////////////
    		
    		////this is for update description
    	/*	// append messagetype to root
    		org.w3c.dom.Element MessageType = doc.createElement("MessageType");
    		MessageType.appendChild(doc.createTextNode("Product"));
    		rootElement.appendChild(MessageType);

    		// append message to root 
    		org.w3c.dom.Element Message = doc.createElement("Message");
    		
    		rootElement.appendChild(Message);

    		
    		// append message element to message 
    		org.w3c.dom.Element MessageID = doc.createElement("MessageID");
    		MessageID.appendChild(doc.createTextNode("1"));
    		Message.appendChild(MessageID);
    		
    		
    		// append message element to message 
    		org.w3c.dom.Element OperationType = doc.createElement("OperationType");
    		OperationType.appendChild(doc.createTextNode("Update"));
    		Message.appendChild(OperationType);
    		
    		
    		
    		// append message element to message 
    		org.w3c.dom.Element Product = doc.createElement("Product");
    		
    		Message.appendChild(Product);
    		
    		
    		
    		//append product element to product 
    		
    		org.w3c.dom.Element SKU = doc.createElement("SKU");
    		SKU.appendChild(doc.createTextNode("SP-10070"));
    		Product.appendChild(SKU);
    		
    		
    		
    		//append product element to product 
    		
    		org.w3c.dom.Element StandardProductID = doc.createElement("StandardProductID");
    
    		Product.appendChild(StandardProductID);
    		
    		
    		//append standardproduct id element to standardproduct id 
    		
    		
    		org.w3c.dom.Element Type = doc.createElement("Type");
    		Type.appendChild(doc.createTextNode("ASIN"));
    		StandardProductID .appendChild(Type);
    		
    		
    		
    		//append standardproduct id element to standardproduct id 
    		
    		
    		org.w3c.dom.Element Value = doc.createElement("Value");
    		Value.appendChild(doc.createTextNode("B015RYLIHM"));
    		StandardProductID .appendChild(Value);
    		
    		
    		//append product element to product 
    		
    		org.w3c.dom.Element DescriptionData = doc.createElement("DescriptionData");
    	    
    		Product.appendChild(DescriptionData);
    		
    		
    		
    		//append DescriptionData element to DescriptionData
    		
    		
    		org.w3c.dom.Element Title = doc.createElement("Title");
    		Title.appendChild(doc.createTextNode("Sample 02-06 Acura RSX Coupe Factory Style Rear Spoiler 03 04 05 S Coupe"));
    		DescriptionData .appendChild(Title);
    		
    		
    		
    		//append DescriptionData element to DescriptionData
    		
    		
    		org.w3c.dom.Element Brand = doc.createElement("Brand");
    		Brand.appendChild(doc.createTextNode("LarryAPS"));
    		DescriptionData .appendChild(Brand);
    		
    		
    		

    		//append DescriptionData element to DescriptionData
    		
    		
    		org.w3c.dom.Element Description = doc.createElement("Description");
    		Description.appendChild(doc.createTextNode(""));
    		DescriptionData .appendChild(Description);
    		
    		

    		//append DescriptionData element to DescriptionData
    		
    		
    		org.w3c.dom.Element BulletPoint = doc.createElement("BulletPoint");
    		BulletPoint.appendChild(doc.createTextNode("100% Brand-new in sealed box,custom-designed."));
    		DescriptionData .appendChild(BulletPoint);
    		
    		
    		//append DescriptionData element to DescriptionData
    		
    		
    		org.w3c.dom.Element BulletPoint1 = doc.createElement("BulletPoint");
    		BulletPoint1.appendChild(doc.createTextNode("Made of high quality Automotive Grade ABS."));
    		DescriptionData .appendChild(BulletPoint1);
    		
    		
    			//append DescriptionData element to DescriptionData
    		
    		
	    		org.w3c.dom.Element BulletPoint2 = doc.createElement("BulletPoint");
	    		BulletPoint2.appendChild(doc.createTextNode("Extremely Popular through out the country "));
	    		DescriptionData .appendChild(BulletPoint2);
    		

			//append DescriptionData element to DescriptionData
		
		
			org.w3c.dom.Element BulletPoint3 = doc.createElement("BulletPoint");
			BulletPoint3.appendChild(doc.createTextNode("Recommanded By Many Professionals "));
			DescriptionData .appendChild(BulletPoint3);
			
			//append DescriptionData element to DescriptionData
			
			//create msrp
			org.w3c.dom.Element MSRP = doc.createElement("MSRP");
			MSRP.appendChild(doc.createTextNode("73.99"));
			
			
			//set Attributes of msrp element
    		Attr attr2 = doc.createAttribute("currency");
    		attr1.setValue("USD");
    		MSRP.setAttributeNode(attr2);
    		
    		//append msrp to descrpition data
    		DescriptionData .appendChild(MSRP);
			
			
    	//append DescriptionData element to DescriptionData
			
			
			org.w3c.dom.Element Manufacturer = doc.createElement("Manufacturer");
			Manufacturer.appendChild(doc.createTextNode("Alex"));
			DescriptionData .appendChild(Manufacturer);*/
			
	//Add multiple skus acheived by enclosing first message, and add 
			//another <Message></message>, with message <id >+1 here can be added to message type " product" to change mutiple listings' description
			
	///////////////////////////////////////		////////////////////////////////////////////////////////
			//if does not work, may need to send another request wihtou rregarding to price, in here tyring to combine two request to one
			// append another messagetype1 to root
    		//this is for update price
		    //	need to create a seperate xml to update price	
			//with message type price 
			
			
    	/*	org.w3c.dom.Element MessageType1 = doc.createElement("MessageType");
    		MessageType1.appendChild(doc.createTextNode("Price"));
    		rootElement.appendChild(MessageType1);
			
			//append another message1 to root 
		
    		org.w3c.dom.Element Message1 = doc.createElement("Message");
    		
    		rootElement.appendChild(Message1);
    		
    		

    		// append message1 element to message 1
    		org.w3c.dom.Element MessageID1 = doc.createElement("MessageID");
    		//value inside message tag
    		MessageID1.appendChild(doc.createTextNode("1"));
    		Message1.appendChild(MessageID1);
    		

    		
    		//append price to message 1
    		
    		org.w3c.dom.Element Price = doc.createElement("Price");
    		
    		Message1.appendChild(Price);
    		
    		
    		// append sku attribute  to price
    		org.w3c.dom.Element sku1 = doc.createElement("SKU");
    		sku1.appendChild(doc.createTextNode("SP-10070"));
    		Price.appendChild(sku1);
    		
    		
    		//append standard price to price
    		
    		org.w3c.dom.Element StandardPrice  = doc.createElement("StandardPrice");
    		StandardPrice.appendChild(doc.createTextNode("204.99"));
    		
    		
    		
    		//set attrubtes of standardprice 
    		Attr attr2 = doc.createAttribute("currency");
    		attr2.setValue("USD");
    		StandardPrice.setAttributeNode(attr2);
    		
    		//append to price
    		Price.appendChild(StandardPrice);
    		
    		
    		//append sale to price
    		
    		org.w3c.dom.Element Sale = doc.createElement("Sale");
    		
    		Price.appendChild(Sale);
    		
    		
    		
    		
    		// append start date  to sale
    		org.w3c.dom.Element StartDate = doc.createElement("StartDate");
    		StartDate.appendChild(doc.createTextNode("2016-08-24T00:00:00Z"));
    		Sale.appendChild(StartDate);
    		
    		
     		// append end date  to sale
    		org.w3c.dom.Element EndDate = doc.createElement("EndDate");
    		EndDate.appendChild(doc.createTextNode("2016-08-30T00:00:00Z"));
    		Sale.appendChild(EndDate);
    		
    		
    		
    		//append sale price to sale
    		
    		org.w3c.dom.Element SalePrice   = doc.createElement("SalePrice");
    		SalePrice.appendChild(doc.createTextNode("80.99"));
    		
    		
    		
    		//set attrubtes of standardprice 
    		
    		Attr attr3 = doc.createAttribute("currency");
    		attr3.setValue("USD");
    		
    		SalePrice .setAttributeNode(attr3);
    		
    		//append to price
    		Sale.appendChild(SalePrice );*/
    		
    		//another <Message>, with message <id >+1 here can be added to message type "price" to change mutiple listings' price	
    		
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
    		///this is for update for inventory 
    		//////////testing for inventory
    		//need antoher xml, set messagetype to inventory, append to root 
    		
    		//
    		/*org.w3c.dom.Element MessageType2 = doc.createElement("MessageType");
    		MessageType2.appendChild(doc.createTextNode("Inventory"));
    		rootElement.appendChild(MessageType2);
			
    		//append another message2 to root 
    		
    		org.w3c.dom.Element Message2 = doc.createElement("Message");
    		
    		rootElement.appendChild(Message2);
    		
    		
    		// append messageid2 element to message 2
    		org.w3c.dom.Element MessageID2 = doc.createElement("MessageID");
    		//value inside message tag
    		MessageID2.appendChild(doc.createTextNode("1"));
    		Message2.appendChild(MessageID2);
    		
    		
    		
    		//appned operation type1 to meesage2
    		
    		
    		org.w3c.dom.Element OperationType1 = doc.createElement("OperationType");
    		//value inside message tag
    		OperationType1.appendChild(doc.createTextNode("Update"));
    		Message2.appendChild(OperationType1);
    		
    		
    		
    		//append inventory element to meesage2
    		
    		
	org.w3c.dom.Element Inventory1 = doc.createElement("Inventory");
    		
    		Message2.appendChild(Inventory1 );
    		
    		
    		//append sku2 to inventory
    		
    		
    		org.w3c.dom.Element SKU2 = doc.createElement("SKU");
    		//value inside message tag
    		SKU2.appendChild(doc.createTextNode("SP-10070"));
    		Inventory1.appendChild(SKU2);
    		
    		
    		
    		
	//append FulfillmentCenterID1 to inventory
    		
    		
    		org.w3c.dom.Element FulfillmentCenterID1 = doc.createElement("FulfillmentCenterID");
    		//value inside message tag
    		FulfillmentCenterID1 .appendChild(doc.createTextNode("DEFAULT"));
    		Inventory1.appendChild(FulfillmentCenterID1);
    		
    		
    	//qty1 to inventory
    		
    		
    		org.w3c.dom.Element Quantity1 = doc.createElement("Quantity");
    		//value inside message tag
    		Quantity1 .appendChild(doc.createTextNode("100"));
    		Inventory1.appendChild(Quantity1);
    		
    		
    		
    	//append SwitchFulfillmentTo to inventory 	
    		
    		
    		org.w3c.dom.Element SwitchFulfillmentTo1 = doc.createElement("SwitchFulfillmentTo");
    		//value inside message tag
    		SwitchFulfillmentTo1 .appendChild(doc.createTextNode("MFN"));
    		Inventory1.appendChild(SwitchFulfillmentTo1);*/
    		
    		
    		
    	//////////mutliple sku inventory can be updated, but this has to be in a seperate message//////	
    		
			
//////////////////////seperate xml to test tracking # upload, needs to retreive order id date method, from db///////////////////////////////////////
			
			
			
			
			
	/*		
			//append a new message type to root 
    		org.w3c.dom.Element MessageType3 = doc.createElement("MessageType");
    		MessageType3.appendChild(doc.createTextNode("OrderFulfillment"));
    		rootElement.appendChild(MessageType3);
			
			
			
			
			
    		//append another message3 to root 
    		
    		org.w3c.dom.Element Message3 = doc.createElement("Message");
    		
    		rootElement.appendChild(Message3);
			
			
			
	
    		// append messageid3 element to message 3
    		org.w3c.dom.Element MessageID3 = doc.createElement("MessageID");
    		//value inside message tag
    		MessageID3.appendChild(doc.createTextNode("1"));
    		Message3.appendChild(MessageID3);
    		
			
			//append orderfulfillment3 to message 3
			
			
    		org.w3c.dom.Element OrderFulfillment3 = doc.createElement("OrderFulfillment");
    		Message3.appendChild(OrderFulfillment3 );
			
			
			
			//append amazon order id to orderfulfillment3
			
			
    		org.w3c.dom.Element AmazonOrderID = doc.createElement("AmazonOrderID");
    		//value inside message tag
    		AmazonOrderID.appendChild(doc.createTextNode("116-9200379-4366634"));
    		OrderFulfillment3.appendChild(AmazonOrderID);
    		
			
			
			//append fulfillment date to orderfulfillment 3
    		
    		
    		
    		org.w3c.dom.Element FulfillmentDate = doc.createElement("FulfillmentDate");
    		//value inside message tag
    		FulfillmentDate.appendChild(doc.createTextNode("2016-08-19T16:40:30-06:00"));
    		OrderFulfillment3.appendChild(FulfillmentDate);
    		
			
			//append fulfillment data to orderfulfillment 3
    		
    		org.w3c.dom.Element FulfillmentData= doc.createElement("FulfillmentData");
    		
    		OrderFulfillment3.appendChild(FulfillmentData);
    		
    		
    		//Append carrier code to fulfillment data
    		
    		
    		org.w3c.dom.Element CarrierCode = doc.createElement("CarrierCode");
    		//value inside message tag
    		CarrierCode.appendChild(doc.createTextNode("UPS"));
    		FulfillmentData.appendChild(CarrierCode);
			
			
    		
    		//Append ShippingMethod to fulfillment data
    	
    		

    		org.w3c.dom.Element ShippingMethod = doc.createElement("ShippingMethod");
    		//value inside message tag
    		ShippingMethod.appendChild(doc.createTextNode("UPS Ground"));
    		FulfillmentData.appendChild(ShippingMethod);
    		
			
    		
    		
    		//append ShipperTrackingNumber to fulfillment data
    		
    		
    		
    		org.w3c.dom.Element ShipperTrackingNumber = doc.createElement("ShipperTrackingNumber");
    		//value inside message tag
    		ShipperTrackingNumber.appendChild(doc.createTextNode("1ZY59R760343610835"));
    		FulfillmentData.appendChild(ShipperTrackingNumber);
    		
    		
    		
    		*/
    		
    		

    		
    		
    		
    		
    		
    		
    		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			//add new product 
    		
    		
			//append a new message type to root 
    		org.w3c.dom.Element MessageType4 = doc.createElement("MessageType");
    		MessageType4.appendChild(doc.createTextNode("Product"));
    		rootElement.appendChild(MessageType4);
			
			
		/*	//append a new element to root  //will remove all open lisiting and replaec with this one. use with caution do not use
    		
    		org.w3c.dom.Element PurgeAndReplace = doc.createElement("PurgeAndReplace");
    		PurgeAndReplace.appendChild(doc.createTextNode("false"));
    		rootElement.appendChild(PurgeAndReplace);
    		*/
    		
			
			
    		//append another message4 to root 
    		
    		org.w3c.dom.Element Message4 = doc.createElement("Message");
    		
    		rootElement.appendChild(Message4);
			
			
			
	
    		// append messageid4 element to message 4
    		org.w3c.dom.Element MessageID4 = doc.createElement("MessageID");
    		//value inside message tag
    		MessageID4.appendChild(doc.createTextNode("1"));
    		Message4.appendChild(MessageID4);
    		
    		
    		
    		
    		
			
			//append Operationtype to message 4
			
			
    		org.w3c.dom.Element OperationType = doc.createElement("OperationType");
    		
    		OperationType.appendChild(doc.createTextNode("Update"));
    		
    		Message4.appendChild(OperationType );
			
			
    		
			
			//append amazon product to message4
			
			
    		org.w3c.dom.Element Product = doc.createElement("Product");
    		
    		Message4.appendChild(Product);
    		
			
			
			//append SKU to Product 
    		
    		
    		
    		org.w3c.dom.Element SKU = doc.createElement("SKU");
    		//value inside message tag
    		SKU.appendChild(doc.createTextNode("IB-TEST"));
    		Product.appendChild(SKU);
    		
			
			//append ProductTaxCode  to Product 
    		
    		
    		org.w3c.dom.Element ProductTaxCode = doc.createElement("ProductTaxCode");
    		//value inside message tag
    		ProductTaxCode.appendChild(doc.createTextNode("A_GEN_TAX"));
    		Product.appendChild(ProductTaxCode);
    		
    		
    		

			//append LaunchDate to Product 
    		
    		
    		org.w3c.dom.Element LaunchDate = doc.createElement("LaunchDate");
    		//value inside message tag
    		LaunchDate.appendChild(doc.createTextNode("2016-08-26T12:00:01"));
    		Product.appendChild(LaunchDate);
    		
    		
    		
    		//append Description data to Product 
    		
    		
    		org.w3c.dom.Element DescriptionData = doc.createElement("DescriptionData");
    
    		Product.appendChild(DescriptionData);
    		
    		
    		//append title to description data
    		
    		org.w3c.dom.Element Title = doc.createElement("Title");
    		//value inside message tag
    		Title.appendChild(doc.createTextNode("Beautiful testing title"));
    		DescriptionData.appendChild(Title);
    		
    		
    		
    		
    		//append brand to description data
    		
    		
    		org.w3c.dom.Element Brand = doc.createElement("Brand");
    		//value inside message tag
    		Brand.appendChild(doc.createTextNode("LarryAPS"));
    		DescriptionData.appendChild(Brand);
    		
    	
    		
    		
    		
    		
    		//append Description to des data
    		
    		
    		org.w3c.dom.Element Description = doc.createElement("Description");
    		//value inside message tag
    		Description.appendChild(doc.createTextNode("This beautiful product is for testing purposes only, not sure where this message"
    				+ "will go, but once being posted, the truth will reveal"
    				+ "if you really like this beautiful product please contact corresponding seller for sample purchase"));
    		DescriptionData.appendChild(Description);
    		
    		
    		//append bullet point to des data 
    		
    		
    		
    		org.w3c.dom.Element BulletPoint = doc.createElement("BulletPoint");
    		BulletPoint.appendChild(doc.createTextNode("100% Brand-new in sealed box,custom-designed."));
    		DescriptionData .appendChild(BulletPoint);
    		
    		
    		//append bull1 to DescriptionData
    		
    		
    		org.w3c.dom.Element BulletPoint1 = doc.createElement("BulletPoint");
    		BulletPoint1.appendChild(doc.createTextNode("Made of high quality Automotive Grade ABS."));
    		DescriptionData .appendChild(BulletPoint1);
    		
    		   
    			//append bull2 element to DescriptionData
    		
    		
	    		org.w3c.dom.Element BulletPoint2 = doc.createElement("BulletPoint");
	    		BulletPoint2.appendChild(doc.createTextNode("Extremely Popular through out the country "));
	    		DescriptionData .appendChild(BulletPoint2);
    		

			//append bull 3 element to DescriptionData
		
		
			org.w3c.dom.Element BulletPoint3 = doc.createElement("BulletPoint");
			BulletPoint3.appendChild(doc.createTextNode("Recommanded By Many Professionals "));
			DescriptionData .appendChild(BulletPoint3);
    		
			
			
    		//append manufacter to DescriptionData
    		
    		
    		org.w3c.dom.Element Manufacturer = doc.createElement("Manufacturer");
    		Manufacturer.appendChild(doc.createTextNode("GoodAlex"));
    		DescriptionData .appendChild(Manufacturer);
    		
    		
    		
	//append SearchTerms to DescriptionData
    		
    		
    		org.w3c.dom.Element SearchTerms = doc.createElement("SearchTerms");
    		SearchTerms.appendChild(doc.createTextNode("Alex"));
    		DescriptionData .appendChild(SearchTerms);
    		
    		
    		
    		
      		
    		//append ItemType to DescriptionData
    	    		
    	    		
    	    		org.w3c.dom.Element ItemType = doc.createElement("ItemType");
    	    		ItemType.appendChild(doc.createTextNode("flat-sheets"));
    	    		DescriptionData .appendChild(ItemType);
    	    		
    		
    	    		
    	    		//append giftwrap  to DescriptionData
    	    	    		
    	    	    		
    	    	    		org.w3c.dom.Element IsGiftWrapAvailable = doc.createElement("IsGiftWrapAvailable");
    	    	    		IsGiftWrapAvailable.appendChild(doc.createTextNode("false"));
    	    	    		DescriptionData .appendChild(IsGiftWrapAvailable);
    	    	    		
    		
    		  //append IsGiftMessageAvailable to descrpition data 
    	    	    		org.w3c.dom.Element IsGiftMessageAvailable = doc.createElement("IsGiftMessageAvailable");
    	    	    		IsGiftMessageAvailable.appendChild(doc.createTextNode("false"));
    	    	    		DescriptionData .appendChild(IsGiftMessageAvailable);
    	    	    		
    		
    	    	    		
    	    	    		
    	    	    		
    	    	//append product data to product 
    	    	    		

    	    	    		org.w3c.dom.Element ProductData = doc.createElement("ProductData");
    	    	    		
    	    	    		Product.appendChild(ProductData);
    	    	    			
    	    	    		
    	   //append Home   to product data 	 
    	    	    		
    	    	    		
    	    	    		org.w3c.dom.Element Home = doc.createElement("Home");
    	    	    
    	    	    		ProductData .appendChild(Home);	
    	  
    	    	    		
    	    	    		
    	    	    		
    	   //append varaition data to Home
    	    	    		
    	    	    		org.w3c.dom.Element VariationData = doc.createElement("VariationData");
    	    	    	
    	    	    		Home .appendChild(VariationData);
    	    	    			
    	    
    	    	    		//append varation theme to var data
    	    	    		
    	    	    		
    	    	    		org.w3c.dom.Element VariationTheme = doc.createElement("VariationTheme");
    	    	    		VariationTheme.appendChild(doc.createTextNode("Size-Color"));
    	    	    		VariationData .appendChild(VariationTheme);
    	   
    	    	    		
    	    	    		
    	    	    		//append material to home
    	    	    		

    	    	    		org.w3c.dom.Element Material = doc.createElement("Material");
    	    	    		Material.appendChild(doc.createTextNode("Aluminum"));
    	    	    		Home .appendChild(Material );
    	    	    		
    	    	    		//append ThreadCount to Home
    	    	    		
    	    	    		org.w3c.dom.Element ThreadCount = doc.createElement("ThreadCount");
    	    	    		ThreadCount.appendChild(doc.createTextNode("500"));
    	    	    		Home .appendChild(ThreadCount );
    	    	    		
    	    	    		
    	    	    		
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  		
    	
    	    	    		
    	    	    		
    	    	    		
    	  
    	    	    		
    	    	    		
    		//many more descrpition data element can be appended, ended now for testing purposes, refer to mws submitfeed api
    		
    	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    	    		
    	    	    		
    	    	    		
    	    	    		
    		// write the content into xml file
    		TransformerFactory transformerFactory = TransformerFactory.newInstance();
    		Transformer transformer = transformerFactory.newTransformer();
    		DOMSource source = new DOMSource(doc);
    		StreamResult result = new StreamResult(new File("Y:\\Staffs\\Joey\\Developer\\JoeyAdvisor\\feedSubmissionready.xml"));

    		// Output to console for testing
    		// StreamResult result = new StreamResult(System.out);

    		transformer.transform(source, result);

    		System.out.println("XMLFile saved!");
    		
    		
    		//send request to server to request update
    		//set MSD5
    		 String md5 = SubmitFeedSample.computeContentMD5HeaderValue(new FileInputStream("Y:\\Staffs\\Joey\\Developer\\JoeyAdvisor\\feedSubmissionready.xml"));
    		  
             try {
            	 //seet request feed contet
    			request.setFeedContent( new FileInputStream("Y:\\Staffs\\Joey\\Developer\\JoeyAdvisor\\feedSubmissionready.xml"));
    			//set md5
    			request.setContentMD5(md5);
    			//invoke request to serve 
    	 invokeSubmitFeed(service, request);

    		} catch (FileNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		   
    		   

    	  } catch (ParserConfigurationException pce) {
    		pce.printStackTrace();
    	  } catch (TransformerException tfe) {
    		tfe.printStackTrace();
    	  }
    	
        
        
        

        

     
    }

    /**
     * Submit Feed request sample Uploads a file for processing together with
     * the necessary metadata to process the file, such as which type of feed it
     * is. PurgeAndReplace if true means that your existing e.g. inventory is
     * wiped out and replace with the contents of this feed - use with caution
     * (the default is false).
     * 
     * @param service
     *            instance of MarketplaceWebService service
     * @param request
     *            Action to invoke
     */
    public static void invokeSubmitFeed(MarketplaceWebService service,
            SubmitFeedRequest request) {
        try {

            SubmitFeedResponse response = service.submitFeed(request);

            System.out.println("SubmitFeed Action Response");
            System.out
            .println("=============================================================================");
            System.out.println();

            System.out.print("    SubmitFeedResponse");
            System.out.println();
            if (response.isSetSubmitFeedResult()) {
                System.out.print("        SubmitFeedResult");
                System.out.println();
                SubmitFeedResult submitFeedResult = response
                .getSubmitFeedResult();
                if (submitFeedResult.isSetFeedSubmissionInfo()) {
                    System.out.print("            FeedSubmissionInfo");
                    System.out.println();
                    FeedSubmissionInfo feedSubmissionInfo = submitFeedResult
                    .getFeedSubmissionInfo();
                    if (feedSubmissionInfo.isSetFeedSubmissionId()) {
                        System.out.print("                FeedSubmissionId");
                        System.out.println();
                        System.out.print("                    "
                                + feedSubmissionInfo.getFeedSubmissionId());
                        System.out.println();
                    }
                    if (feedSubmissionInfo.isSetFeedType()) {
                        System.out.print("                FeedType");
                        System.out.println();
                        System.out.print("                    "
                                + feedSubmissionInfo.getFeedType());
                        System.out.println();
                    }
                    if (feedSubmissionInfo.isSetSubmittedDate()) {
                        System.out.print("                SubmittedDate");
                        System.out.println();
                        System.out.print("                    "
                                + feedSubmissionInfo.getSubmittedDate());
                        System.out.println();
                    }
                    if (feedSubmissionInfo.isSetFeedProcessingStatus()) {
                        System.out
                        .print("                FeedProcessingStatus");
                        System.out.println();
                        System.out.print("                    "
                                + feedSubmissionInfo.getFeedProcessingStatus());
                        System.out.println();
                    }
                    if (feedSubmissionInfo.isSetStartedProcessingDate()) {
                        System.out
                        .print("                StartedProcessingDate");
                        System.out.println();
                        System.out
                        .print("                    "
                                + feedSubmissionInfo
                                .getStartedProcessingDate());
                        System.out.println();
                    }
                    if (feedSubmissionInfo.isSetCompletedProcessingDate()) {
                        System.out
                        .print("                CompletedProcessingDate");
                        System.out.println();
                        System.out.print("                    "
                                + feedSubmissionInfo
                                .getCompletedProcessingDate());
                        System.out.println();
                    }
                }
            }
            if (response.isSetResponseMetadata()) {
                System.out.print("        ResponseMetadata");
                System.out.println();
                ResponseMetadata responseMetadata = response
                .getResponseMetadata();
                if (responseMetadata.isSetRequestId()) {
                    System.out.print("            RequestId");
                    System.out.println();
                    System.out.print("                "
                            + responseMetadata.getRequestId());
                    System.out.println();
                }
            }
            System.out.println(response.getResponseHeaderMetadata());
            System.out.println();
            System.out.println();

        } catch (MarketplaceWebServiceException ex) {

            System.out.println("Caught Exception: " + ex.getMessage());
            System.out.println("Response Status Code: " + ex.getStatusCode());
            System.out.println("Error Code: " + ex.getErrorCode());
            System.out.println("Error Type: " + ex.getErrorType());
            System.out.println("Request ID: " + ex.getRequestId());
            System.out.print("XML: " + ex.getXML());
            System.out.println("ResponseHeaderMetadata: " + ex.getResponseHeaderMetadata());
        }
    }

}
