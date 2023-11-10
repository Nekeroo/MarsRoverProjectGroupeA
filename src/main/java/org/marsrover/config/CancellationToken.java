package org.marsrover.config;

import java.util.concurrent.atomic.AtomicBoolean;

public class CancellationToken {
    private final AtomicBoolean isCancelled = new AtomicBoolean(false);

    public boolean isCancellationRequested() {
        return isCancelled.get();
    }

    public void cancel() {
        isCancelled.set(true);
    }
}
