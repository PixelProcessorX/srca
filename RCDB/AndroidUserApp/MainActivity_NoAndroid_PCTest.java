package AndroidUserApp;

import java.io.IOException;

import lipermi.handler.CallHandler;
import lipermi.net.Client;
import RmiService.RmiService;

public class MainActivity_NoAndroid_PCTest
{
	private static String serverIP = "127.0.0.1"; //SERVER ADDRESS HERE, NOT LOCALHOST
	private static int  serverPort = 7777;
	private static String admin_key= "8EA8E23FA108B328613678DEEDD9142D6558386DCD6ECECC42F3461443DF5577";
	
	/** THIS IS A SIMPLE ONE-THREAD LOOP; JUST FOR TESTING. IT     */
	/** DOESN'T BOTHER DOING ALL THE COMPLICATED ASYNCRONOUS CODE. */
	public static void main(String[] args)
	{
		//while(true){
			try {
	            CallHandler callHandler = new CallHandler();
	            Client client = new Client(serverIP, serverPort, callHandler);
	            RmiService db = (RmiService) client.getGlobal(RmiService.class);
	            //############################################
	            //THIS PART IS WHERE YOU CAN CALL DATABASE FUNCTIONS.
	            
	            String txt = "TESTTEXTHERE123";
	            String msg = db.TestItOut(txt);
	            System.out.println(msg);
	            System.out.println(admin_key);
	            
	            
	            
	            //############################################
	            
	            client.close();
	        }catch(IOException e){
	            e.printStackTrace();
	        }
		//}
		/**while(true){}*/
	}
}