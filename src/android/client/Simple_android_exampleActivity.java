package android.client;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Simple_android_exampleActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void sendMsg(View button) {
    	final EditText inputBox = (EditText) findViewById(R.id.MsgBox);
    	String msg = inputBox.getText().toString();

    	final EditText convBox = (EditText) findViewById(R.id.ConvTextBox);
    	String conv = convBox.getText().toString();
   		
    	ServiceClient sc = (ServiceClient) new ServiceClient().execute(new String[] {msg});
    	String sp;
		try {
			sp = sc.get();
			if (sp != null) {
				conv += '\n' + sp.toString();
			} else {
				conv += '\n' + sc.getException();
			}
		} catch (Exception e) {
			conv += '\n' + e.toString();
		}
		
		
    	inputBox.setText("");
    	convBox.setText(conv);
    }

}