package br.com.intel.innovators.context.android.events;

import com.intel.context.error.ContextError;

/**
 * Created by ubiratansoares on 4/29/15.
 */

public class SensingConnectionFail {

    private ContextError error;

    public static SensingConnectionFail with(ContextError e) {
        return new SensingConnectionFail(e);
    }

    private SensingConnectionFail(ContextError error) {
        this.error = error;
    }

    public ContextError getError() {
        return error;
    }
}
