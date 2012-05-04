package android.client;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import android.os.AsyncTask;

public class ServiceClient extends AsyncTask<String, Void, String> {

	//String URL = "http://alucard:8080/simple_server_example/services/DoesMagic";
	// method name = doBasicStuff
	private Exception e;
		// you can get these values from the wsdl file
    private static final String METHOD_NAME = "doBasicStuff";
    private static final String SOAP_ACTION = "";
    private static final String NAMESPACE = "http://test.com";
    private static final String URL = "http://alucard:8080/simple_server_example/services/DoesMagic";
	
	/*private static final String METHOD_NAME = "TopGoalScorers";
	private static final String SOAP_ACTION = "http://footballpool.dataaccess.eu/data/TopGoalScorers";
	private static final String NAMESPACE = "http://footballpool.dataaccess.eu";
	private static final String URL = "http://footballpool.dataaccess.eu/data/info.wso?WSDL";
	*/
	@Override
	protected String doInBackground(String... input) {
		this.e = null;
		try {
				// set up request
	    	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
	    		// variable name, value. I got the variable name, from the wsdl file!
	    	request.addProperty("Msg", input[0]);
	    		// put all required data into a soap envelope
	    	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    		// prepare request
	    	envelope.setOutputSoapObject(request);
	    	HttpTransportSE httpTransport = new HttpTransportSE(URL);
	    	  	// this is optional, use it if you don't want to use a packet sniffer to check what the sent message was (httpTransport.requestDump)
	    	httpTransport.debug = true;
	    		// send request
	    	httpTransport.call(SOAP_ACTION, envelope);
	    		// get response
	    	SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
	    	return result.toString();
		} catch (Exception exception) {
			this.e = exception;
			return null;
		}
	}
	
	public String getException() {
		if (e != null) {
			return e.toString();
		}
		return "No exception has occured";
	}
}
