package RCDB;

// Student Recreation Center Application
// (Database Code and LipeRMI Code by Dillon Simion)

public class RC_WEBSERVER
{
	public static void main(String[] args) {
		try{
			DB.ConnectToDatabase();//FOR PUBLIC USER ACCOUNTS!!!
			RmiServer rsv = new RmiServer();
			rsv.ListenForClients();//HANDLES CONNECTIONS AND ANDROID DEVICE'S USER FUNCTION CALLS.
		}catch(Exception e){
		    throw new IllegalStateException("Generic Exception Catch Triggered", e);
		}
	}
}
