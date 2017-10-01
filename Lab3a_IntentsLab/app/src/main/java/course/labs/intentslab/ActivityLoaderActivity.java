package course.labs.intentslab;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityLoaderActivity extends Activity {
    
	static public final int GET_TEXT_REQUEST_CODE = 1;
	static private final String URL = "http://www.google.com";
	static private final String TAG = "Lab-Intents";
    
	// Для использования при выборе приложения
	static private final String CHOOSER_TEXT = "Load " + URL + " with:";
    
	// TextView  который отображает текст, введенный в ExplicitlyLoadedActivity
	private TextView mUserTextView;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loader_activity);
		
		// Получаем ссылку на textView
		mUserTextView = (TextView) findViewById(R.id.textView1);
        
		// Объявляем и устанавливаем кнопку Explicit Activation
		Button explicitActivationButton = (Button) findViewById(R.id.explicit_activation_button);
		explicitActivationButton.setOnClickListener(new OnClickListener() {
            
			// Вызываем startExplicitActivation() при нажатии
			@Override
			public void onClick(View v) {
				
				startExplicitActivation();
                
			}
		});
        
		// Объявляем и устанавливаем кнопку Implicit Activation 
		Button implicitActivationButton = (Button) findViewById(R.id.implicit_activation_button);
		implicitActivationButton.setOnClickListener(new OnClickListener() {
            
			// Вызываем startImplicitActivation() при нажатии
			@Override
			public void onClick(View v) {
                
				startImplicitActivation();
                
			}
		});
        
	}
    
	
	// Start the ExplicitlyLoadedActivity
	
	private void startExplicitActivation() {
        
		Log.i(TAG,"Вошли в startExplicitActivation()");


		Intent getTextIntent = new Intent(this, ExplicitlyLoadedActivity.class);

		startActivityForResult(getTextIntent, GET_TEXT_REQUEST_CODE);
        
        
	}
    
	// Start a Browser Activity to view a web page or its URL
	
	private void startImplicitActivation() {
        
		Log.i(TAG, "Вошли в startImplicitActivation()");

		Uri webpage = Uri.parse(URL);
		Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        

		Intent chooser = Intent.createChooser(webIntent, CHOOSER_TEXT);

		if (webIntent.resolveActivity(getPackageManager()) != null) {
			startActivity(chooser);
		}

        
	}
    
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        
		Log.i(TAG, "Entered onActivityResult()");

		if (requestCode == GET_TEXT_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {
				String message = data.getStringExtra("EXTRA_MESSAGE");

				TextView textView = (TextView) findViewById(R.id.textView1);
				textView.setText(message);
			}
		}
    
    }
}
