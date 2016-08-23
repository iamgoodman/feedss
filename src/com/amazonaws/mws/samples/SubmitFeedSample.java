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
    	final String accessKeyId = "";
        final String secretAccessKey = "";


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
        final String merchantId = "";
        final String sellerDevAuthToken = "";
        // marketplaces to which this feed will be submitted; look at the
        // API reference document on the MWS website to see which marketplaces are
        // included if you do not specify the list yourself
        final IdList marketplaces = new IdList(Arrays.asList(
        		 ""));

        SubmitFeedRequest request = new SubmitFeedRequest();
        
        request.setMerchant(merchantId);
        
       request.setMWSAuthToken(sellerDevAuthToken);
       
        request.setMarketplaceIdList(marketplaces);

        //need to generate XML to submit  _POST_PRODUCT_DATA_ for change title descrpition and etc	
        //update listing for price and inventory only _POST_FLAT_FILE_LISTINGS_DATA_
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

    		// append messagetype to root
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
    		Title.appendChild(doc.createTextNode("Tests 02-06 Acura RSX Coupe Factory Style Rear Spoiler 03 04 05 S Coupe"));
    		DescriptionData .appendChild(Title);
    		
    		
    		
    		//append DescriptionData element to DescriptionData
    		
    		
    		org.w3c.dom.Element Brand = doc.createElement("Brand");
    		Brand.appendChild(doc.createTextNode("Larry"));
    		DescriptionData .appendChild(Brand);
    		
    		
    		

    		//append DescriptionData element to DescriptionData
    		
    		
    		org.w3c.dom.Element Description = doc.createElement("Description");
    		Description.appendChild(doc.createTextNode(""));
    		DescriptionData .appendChild(Description);
    		
    		

    		//append DescriptionData element to DescriptionData
    		
    		
    		org.w3c.dom.Element BulletPoint = doc.createElement("BulletPoint");
    		BulletPoint.appendChild(doc.createTextNode("100% Brand-new in sealed box,custom-designed for your car."));
    		DescriptionData .appendChild(BulletPoint);
    		
    		
    		//append DescriptionData element to DescriptionData
    		
    		
    		org.w3c.dom.Element BulletPoint1 = doc.createElement("BulletPoint");
    		BulletPoint1.appendChild(doc.createTextNode("Made of high quality Automotive Grade ABS,awesome."));
    		DescriptionData .appendChild(BulletPoint1);
    		
    		
    			//append DescriptionData element to DescriptionData
    		
    		
	    		org.w3c.dom.Element BulletPoint2 = doc.createElement("BulletPoint");
	    		BulletPoint2.appendChild(doc.createTextNode("Extremely Popular through out the country "));
	    		DescriptionData .appendChild(BulletPoint2);
    		

			//append DescriptionData element to DescriptionData
		
		
			org.w3c.dom.Element BulletPoint3 = doc.createElement("BulletPoint");
			BulletPoint3.appendChild(doc.createTextNode("Recommanded By Many Professionals "));
			DescriptionData .appendChild(BulletPoint3);
			
		/*	//append DescriptionData element to DescriptionData
			
			//create msrp
			org.w3c.dom.Element MSRP = doc.createElement("MSRP");
			MSRP.appendChild(doc.createTextNode("73.99"));
			
			
			//set Attributes of msrp element
    		Attr attr2 = doc.createAttribute("currency");
    		attr1.setValue("USD");
    		MSRP.setAttributeNode(attr2);
    		
    		//append msrp to descrpition data
    		DescriptionData .appendChild(MSRP);
			*/
			
    	//append DescriptionData element to DescriptionData
			
			
			org.w3c.dom.Element Manufacturer = doc.createElement("Manufacturer");
			Manufacturer.appendChild(doc.createTextNode("Alex"));
			DescriptionData .appendChild(Manufacturer);
			
			
    		
    		//many more descrpition data element can be appended, ended now for testing purposes, refer to mws submitfeed api
    		
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
    	
        
        
        

        
        
        //this is for changing price and quantinty, just need to follow format update price  in xls and upload it. 
   /*     //md5 set for direct change of price and quantity through xls file
       //set md5 to request file
        String md5 = SubmitFeedSample.computeContentMD5HeaderValue(new FileInputStream("Y:\\Staffs\\Joey\\Developer\\JoeyAdvisor\\testupload.xls"));
        
        
         try {
			request.setFeedContent( new FileInputStream("Y:\\Staffs\\Joey\\Developer\\JoeyAdvisor\\testupload.xls"));
			request.setContentMD5(md5);
	 invokeSubmitFeed(service, request);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
     
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
