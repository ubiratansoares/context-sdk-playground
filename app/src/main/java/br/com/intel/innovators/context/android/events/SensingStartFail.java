package br.com.intel.innovators.context.android.events;

import com.intel.context.error.ContextError;

/**
 * Created by ubiratansoares on 4/30/15.
 */

public class SensingStartFail {

    private ContextError error;

    public static SensingStartFail with(ContextError e) {
        return new SensingStartFail(e);
    }

    private SensingStartFail(ContextError error) {
        this.error = error;
    }

    public ContextError getError() {
        return error;
    }
}
