package course.labs.intentslab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ExplicitlyLoadedActivity extends Activity {

	static private final String TAG = "Lab-Intents";

	private EditText mEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.explicitly_loaded_activity);

		// Получаем ссылку на поле EditText 
		mEditText = (EditText) findViewById(R.id.editText);

		// Объявляем и устанавливаем кнопку "Enter"
		Button enterButton = (Button) findViewById(R.id.enter_button);
		enterButton.setOnClickListener(new OnClickListener() {

			// Вызываем enterClicked() при нажатии

			@Override
			public void onClick(View v) {

				enterClicked();
			
			}
		});

	}

	// Устанавливает результат для отправки назад в вызывающий Activity и завершается
	
	private void enterClicked() {

		Log.i(TAG,"Вошли в enterClicked()");

		String mes = mEditText.getText().toString();

		Intent returnTextIntent = new Intent();
		returnTextIntent.putExtra("EXTRA_MESSAGE", mes);

		setResult(RESULT_OK, returnTextIntent);

		finish();

	}
}
