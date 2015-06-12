package br.com.intel.innovators.context.android.listener;

import com.intel.context.error.ContextError;
import com.intel.context.sensing.InitCallback;

import br.com.intel.innovators.context.android.events.SensingStartSuccess;
import de.greenrobot.event.EventBus;

/**
 * Created by ubiratansoares on 4/29/15.
 */

public class SensingStartCallback implements InitCallback {

    @Override public void onSuccess() {
        EventBus.getDefault().post(new SensingStartSuccess());
    }

    @Override public void onError(ContextError contextError) {
        EventBus.getDefault().post(contextError);
    }
}
