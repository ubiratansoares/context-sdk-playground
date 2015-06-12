package br.com.intel.innovators.context.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.intel.context.Sensing;
import com.intel.context.exception.ContextProviderException;
import com.intel.context.item.AudioClassification;
import com.intel.context.option.audio.AudioOptionBuilder;
import com.intel.context.option.audio.Mode;
import com.intel.context.sensing.SensingEvent;

import br.com.intel.innovators.context.android.events.SensingConnectionFail;
import br.com.intel.innovators.context.android.events.SensingStartFail;
import br.com.intel.innovators.context.android.events.SensingStartSuccess;
import br.com.intel.innovators.context.android.listener.AudioClassificationListener;
import br.com.intel.innovators.context.android.listener.SensingConnectionCallback;
import br.com.intel.innovators.context.android.listener.SensingStartCallback;
import de.greenrobot.event.EventBus;

import static android.widget.Toast.LENGTH_SHORT;
import static com.intel.context.item.ContextType.AUDIO;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Sensing";

    private Sensing sensing;
    private TextView messageLabel;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageLabel = (TextView) findViewById(R.id.txt_message);
        sensing = new Sensing(getApplicationContext(), new SensingConnectionCallback());
    }

    @Override protected void onStart() {
        EventBus.getDefault().register(this);
        sensing.start(new SensingStartCallback());
        super.onStart();
    }


    @Override protected void onStop() {
        EventBus.getDefault().unregister(this);
        sensing.stop();
        super.onStop();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
    }

    public void onEvent(AudioClassification e) {
        final String audioName = e.getMostProbableAudio().getName().name();
        final int probability = e.getMostProbableAudio().getProbability();
        messageLabel.setText(audioName + "\n" + probability + " %");
    }

    public void onEvent(SensingEvent e) {
        messageLabel.setText(e.getDescription());
    }

    public void onEvent(SensingConnectionFail e) {
        messageLabel.setText(R.string.label_sensing_connection_fail);
    }

    public void onEvent(SensingStartFail e) {
        messageLabel.setText(R.string.label_sensing_start_fail);
    }

    public void onEvent(SensingStartSuccess e) {
        messageLabel.setText(R.string.label_sensing_start_success);
        startAudioClassification();
    }

    private void startAudioClassification() {

        AudioOptionBuilder settings = new AudioOptionBuilder().setMode(Mode.FAST);

        try {
            sensing.enableSensing(AUDIO, settings.toBundle());
            sensing.addContextTypeListener(AUDIO, new AudioClassificationListener());
        } catch (ContextProviderException e) {
            Toast.makeText(this, e.getMessage(), LENGTH_SHORT).show();
        }

    }
}



