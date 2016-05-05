package AndroidUserApp;

/**#######################################################################
//##
//##	WARNING!!!! THIS CODE CAN ONLY BE WORKED WITH ON ANDROID. 
//##    Ryan/Vincent MUST TEST THIS.
//##
//########################################################################*/

/*
import java.io.IOException;

import lipermi.handler.CallHandler;
import lipermi.net.Client;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import RmiService.RmiService;

public class MainActivity extends Activity
{
	private String serverIP = "127.0.0.1"; //SERVER ADDRESS HERE, NOT LOCALHOST
	
	
	@Override protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    Button btnGet = (Button)findViewById(R.id.btnGet);
	    btnGet.setOnClickListener(new OnClickListener(){
	        @Override public void onClick(View arg0) {new Conn().execute();} });
	}
	class Conn extends AsyncTask<Void, Void, MainActivity>
	{
	    @Override protected MainActivity doInBackground(Void... params)
	    {
	        Looper.prepare();
	        try {
	            CallHandler callHandler = new CallHandler();
	            Client client = new Client(serverIP, 7777, callHandler);
	            RmiService rsv = (RmiService) client.getGlobal(RmiService.class);
	            //############################################
	            //THIS PART IS WHERE YOU CAN CALL DATABASE FUNCTIONS.
	            
	            String msg = rsv.TestItOut("qwe");
	            
	            
	            
	            
	            //############################################
	            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
	            client.close();
	        }catch(IOException e){
	            e.printStackTrace();
	        }
	        Looper.loop();
	        return null;
	    }
	}
}*/