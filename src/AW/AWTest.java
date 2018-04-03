
package AW;
import java.io.BufferedReader;
import org.testng.annotations.Test;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AWTest {
	
public String WindowId(String windowTitle) throws JSONException{
	
	String Wid=null;
	int counttry=0;
	String idget="";
	
	//System.out.print("try1");
	String connectionId = "29bacd";
	//String pUrl = "http://staging-jobssystem-v3.appspot.com/api/businessHours/getBusinessHoursByAccount.htm?accountNumber=8736273354&timeZoneID=29aff3f3-9657-4955-9bca-4c9a13e5eefe";

	String pUrl = "http://localhost:9222/json";
	//String pUrl = "https://staging-historysystem-new.appspot.com/Interaction/getInteraction.do?apiKey=3cb8cc68-ecb9-4f00-92fb-fe38bfa103a4&type=7773327f-9328-435d-bec9-c868bc3a301e&isParent=false&connectionId=7878787887";
	BufferedReader reader = null;
	HttpURLConnection connection = null;
	String lResponseString = "";
	String lRespObj = "";
	JSONArray interactionArray = null;
	try
		{
		//System.out.print("try1");
			URL url = new URL( pUrl );
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput( true );
			connection.setDoOutput( true );
			connection.setRequestMethod( "POST" );
			connection.setConnectTimeout( 0 );
			connection.setReadTimeout( 60000 );
			connection.setInstanceFollowRedirects( false );
			OutputStreamWriter writer = new OutputStreamWriter( connection.getOutputStream() );
			writer.write( "" );
			writer.flush();
			reader = new BufferedReader( new InputStreamReader( connection.getInputStream() ) );
			while ( ( lResponseString = reader.readLine() ) != null )
				lRespObj += lResponseString;
			//System.out.print(lRespObj);
			
//			System.out.print("try1");
//			//JSONObject try1=new JSONObject(lRespObj);
//			System.out.print("try2");
		interactionArray = new JSONArray( lRespObj );
				//System.out.print("try3");
		}
	catch ( Exception e )
		{
			e.printStackTrace();
		}
	finally
		{
			try
				{
					if ( reader != null )
						reader.close();
					if ( connection != null )
						connection.disconnect();
				}
			catch ( Exception e )
				{
					e.printStackTrace();
				}
		}
	for(int i=0;i<interactionArray.length();i++)
	{
		 
		JSONObject interactionObject = new JSONObject(interactionArray.getString(i));
		 String id = (String) interactionObject.get("id");
		 String page = (String) interactionObject.get("title");
		 String URL= (String) interactionObject.get("url");
		// System.out.println(id);
		 System.out.println(URL);
		 System.out.println(page);
		 if(page.equalsIgnoreCase(windowTitle)){
			 Wid=id;
			 if(page.equalsIgnoreCase("AnywhereWorks")){
				 if(URL.equalsIgnoreCase("file:///Applications/AnywhereWorks.app/Contents/Resources/app.asar/view/FULL.html")){
					 
					 break;
				 }
			 }
			 
			// System.out.println("Window title with id "+windowTitle+Wid);
//			 counttry++;
//			 
//			 if(counttry==2){
//				 Wid=id;
//			 }
		 }
		
		 // String anywherecont=id;
		 // idget += id		
	
	} 
	

	return Wid;
}

//public static void main(String args[]) throws IOException, InterruptedException, JSONException{
//	  
//	Test  testtry = new Test();
//	  String getid= testtry.WindowId("AnyWhereWorks");
//	  System.out.print("Got the 3rd one"+getid);
//}

} 
/*
	public static void main(String[] args) throws JSONException {
		
		int counttry=1;
		String idget="";
		
		//System.out.print("try1");
		String connectionId = "29bacd";
		//String pUrl = "http://staging-jobssystem-v3.appspot.com/api/businessHours/getBusinessHoursByAccount.htm?accountNumber=8736273354&timeZoneID=29aff3f3-9657-4955-9bca-4c9a13e5eefe";

		String pUrl = "http://localhost:9222/json";
		//String pUrl = "https://staging-historysystem-new.appspot.com/Interaction/getInteraction.do?apiKey=3cb8cc68-ecb9-4f00-92fb-fe38bfa103a4&type=7773327f-9328-435d-bec9-c868bc3a301e&isParent=false&connectionId=7878787887";
		BufferedReader reader = null;
		HttpURLConnection connection = null;
		String lResponseString = "";
		String lRespObj = "";
		JSONArray interactionArray = null;
		try
			{
			//System.out.print("try1");
				URL url = new URL( pUrl );
				connection = (HttpURLConnection) url.openConnection();
				connection.setDoInput( true );
				connection.setDoOutput( true );
				connection.setRequestMethod( "POST" );
				connection.setConnectTimeout( 0 );
				connection.setReadTimeout( 60000 );
				connection.setInstanceFollowRedirects( false );
				OutputStreamWriter writer = new OutputStreamWriter( connection.getOutputStream() );
				writer.write( "" );
				writer.flush();
				reader = new BufferedReader( new InputStreamReader( connection.getInputStream() ) );
				while ( ( lResponseString = reader.readLine() ) != null )
					lRespObj += lResponseString;
				//System.out.print(lRespObj);
				
//				System.out.print("try1");
//				//JSONObject try1=new JSONObject(lRespObj);
//				System.out.print("try2");
			interactionArray = new JSONArray( lRespObj );
					//System.out.print("try3");
			}
		catch ( Exception e )
			{
				e.printStackTrace();
			}
		finally
			{
				try
					{
						if ( reader != null )
							reader.close();
						if ( connection != null )
							connection.disconnect();
					}
				catch ( Exception e )
					{
						e.printStackTrace();
					}
			}
		for(int i=0;i<interactionArray.length();i++)
		{
			 
			JSONObject interactionObject = new JSONObject(interactionArray.getString(i));
			 String id = (String) interactionObject.get("id");
			 String page = (String) interactionObject.get("title");
			 System.out.println(id);
			 System.out.println(page);
			 if(page.equalsIgnoreCase("AnywhereWorks"))
			 {
				// String anywherecont=id;
				
				 if(counttry==1)
				 {
					String firstAWid= id ;
					System.out.println("first one"+id);
					counttry++;
					
				 }
				
				 
			 }
			 idget += id;
			 
			
		     
			 /*
			JSONArray interactionStatusList = new JSONArray( interactionObject.getString("interactionStatusList"));
			for(int j=0;j<interactionStatusList.length();j++)
			{
				JSONObject interactionStatusObject = new JSONObject(interactionStatusList.getString(j));
				System.out.println("Status ::: "+interactionStatusObject.getString("status"));
				if("Schedule Interaction Task".equalsIgnoreCase(interactionStatusObject.getString("status")))
				{
					System.out.println("Message saved successfully");
				} 
				
			} 
		}  
		//System.out.println(idget);
	}
}*/
