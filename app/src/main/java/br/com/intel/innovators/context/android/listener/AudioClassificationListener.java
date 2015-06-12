package br.com.intel.innovators.context.android.listener;

import com.intel.context.error.ContextError;
import com.intel.context.item.AudioClassification;
import com.intel.context.item.Item;
import com.intel.context.sensing.ContextTypeListener;

import br.com.intel.innovators.context.android.events.AudioClassificationError;
import de.greenrobot.event.EventBus;

/**
 * Created by ubiratansoares on 4/30/15.
 */

public class AudioClassificationListener implements ContextTypeListener {

    @Override public void onReceive(Item item) {
        if (item instanceof AudioClassification) {
            EventBus.getDefault().post(item);
        }
    }

    @Override public void onError(ContextError contextError) {
        EventBus.getDefault().post(new AudioClassificationError(contextError));
    }
}
