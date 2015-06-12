package br.com.intel.innovators.context.android.listener;

import com.intel.context.error.ContextError;
import com.intel.context.sensing.SensingEvent;
import com.intel.context.sensing.SensingStatusListener;

import br.com.intel.innovators.context.android.events.SensingConnectionFail;
import de.greenrobot.event.EventBus;

/**
 * Created by ubiratansoares on 4/28/15.
 */

public class SensingConnectionCallback implements SensingStatusListener {

    @Override public void onEvent(SensingEvent sensingEvent) {
        EventBus.getDefault().post(sensingEvent);
    }

    @Override public void onFail(ContextError error) {
        EventBus.getDefault().post(SensingConnectionFail.with(error));
    }

}
