import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.io.StringReader;
import java.net.URL;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.*;
public class TestXML {

	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {

		TestXML http = new TestXML();
		String urlServer="http://betamasaheft.aai.uni-hamburg.de/";
		String url = urlServer+"api/works/list";
		System.out.println("Testing 1 - Send Http GET request");
	 	boolean foundError=false;
		String xmlheader = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>";
		String xml=xmlheader+http.sendGet(url);
//System.out.println(xml);
		InputStream stream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
org.jdom2.input.SAXBuilder saxBuilder = new SAXBuilder();
try {
    org.jdom2.Document doc = saxBuilder.build(stream);
    
    String message = doc.getRootElement().getChildText("total");
    System.out.println("**"+message);
	int start=0; int step=200; int limit=Integer.parseInt(message);
	int stop=start+step;
	while(start<limit){
	   //do 
		//for(int j=start;j<stop;j++){

			String url1=url+"?start="+start+"&perpage="+step;
			String req=http.sendGet(url1);
			String xmlTemp=xmlheader+req;
			if(!req.equals("Error")){
			stream = new ByteArrayInputStream(xmlTemp.getBytes("UTF-8"));
			doc = saxBuilder.build(stream);
			for (int i=0;i<doc.getRootElement().getChildren().size()-1;i++){
			     message = doc.getRootElement().getChildren().get(i).getTextNormalize();
			   // String attrOcc=doc.getRootElement().getChildren().get(i).getAttributeValue("occupation");
			   //  System.out.println("PP "+message+ "occupation "+attrOcc);
			    // String idXML=doc.getRootElement().getChildren().get(i).getAttributeValue("uri");
			     //String nameFile=idXML.substring(idXML.lastIndexOf('/')+1);
			     //System.out.println("Name File "+nameFile);
			    /* if(i==0){
			    	String xml1=http.sendGet(urlServer+nameFile).toString();
			    	System.out.println(xml1);
			     }*/
			    }
	}
			
		//}
		start=stop;
		stop=stop+step;
	}
	/*if((stop >= limit)&& (!foundError)){
		//for(int j=start;j<limit;j++){
			String url1=url+"?start="+start;
			String req=http.sendGet(url1);
			String xmlTemp=xmlheader+req;
			if(!req.equals("Error")){
			stream = new ByteArrayInputStream(xmlTemp.getBytes("UTF-8"));
			doc = saxBuilder.build(stream);*/
			//for (int i=0;i<doc.getRootElement().getChildren().size()-1;i++){
			    // message = doc.getRootElement().getChildren().get(i).getTextNormalize();
			   // String attrOcc=doc.getRootElement().getChildren().get(i).getAttributeValue("occupation");
			    // System.out.println("PP "+message+ "occupation "+attrOcc);
			    // String idXML=doc.getRootElement().getChildren().get(i).getAttributeValue("uri");
			     //String nameFile=idXML.substring(idXML.lastIndexOf('/')+1);
			     //System.out.println("Name File "+nameFile);
			    /* if(i==0){
			    	String xml1=http.sendGet(urlServer+nameFile).toString();
			    	System.out.println(xml1);
			     }*/
			   // }
			//}
		//}
	//}
    
    
    /*for (int i=0;i<doc.getRootElement().getChildren().size()-1;i++){
     message = doc.getRootElement().getChildren().get(i).getTextNormalize();
    String attrOcc=doc.getRootElement().getChildren().get(i).getAttributeValue("occupation");
     System.out.println("PP "+message+ "occupation "+attrOcc);
     String idXML=doc.getRootElement().getChildren().get(i).getAttributeValue("uri");
     String nameFile=idXML.substring(idXML.lastIndexOf('/')+1);
     System.out.println("Name File "+nameFile);
     if(i==0){
    	String xml1=http.sendGet(urlServer+nameFile).toString();
    	System.out.println(xml1);
     }
    }*/

} catch (JDOMException e) {
    // handle JDOMException
} catch (IOException e) {
    // handle IOException
}
	
		
	}

	// HTTP GET request
	private String sendGet(String url){

	try{	

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept", "*/*");
		int responseCode = con.getResponseCode();
		//if (responseCode!=200)
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
if (responseCode==200){
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
			//response.append("\n");
		}
		in.close();
	
		return response.toString();
}
else return "Error";
	}
catch(Exception e){ return "Error";}
		//print result
		//System.out.println(response.toString());

	}
}
