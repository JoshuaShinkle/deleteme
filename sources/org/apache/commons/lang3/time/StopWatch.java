package org.apache.commons.lang3.time;

/* loaded from: classes.dex */
public class StopWatch {
    private static final long NANO_2_MILLIS = 1000000;
    private State runningState = State.UNSTARTED;
    private SplitState splitState = SplitState.UNSPLIT;
    private long startTime;
    private long startTimeMillis;
    private long stopTime;

    public enum SplitState {
        SPLIT,
        UNSPLIT
    }

    public enum State {
        UNSTARTED { // from class: org.apache.commons.lang3.time.StopWatch.State.1
            @Override // org.apache.commons.lang3.time.StopWatch.State
            public boolean isStarted() {
                return false;
            }

            @Override // org.apache.commons.lang3.time.StopWatch.State
            public boolean isStopped() {
                return true;
            }

            @Override // org.apache.commons.lang3.time.StopWatch.State
            public boolean isSuspended() {
                return false;
            }
        },
        RUNNING { // from class: org.apache.commons.lang3.time.StopWatch.State.2
            @Override // org.apache.commons.lang3.time.StopWatch.State
            public boolean isStarted() {
                return true;
            }

            @Override // org.apache.commons.lang3.time.StopWatch.State
            public boolean isStopped() {
                return false;
            }

            @Override // org.apache.commons.lang3.time.StopWatch.State
            public boolean isSuspended() {
                return false;
            }
        },
        STOPPED { // from class: org.apache.commons.lang3.time.StopWatch.State.3
            @Override // org.apache.commons.lang3.time.StopWatch.State
            public boolean isStarted() {
                return false;
            }

            @Override // org.apache.commons.lang3.time.StopWatch.State
            public boolean isStopped() {
                return true;
            }

            @Override // org.apache.commons.lang3.time.StopWatch.State
            public boolean isSuspended() {
                return false;
            }
        },
        SUSPENDED { // from class: org.apache.commons.lang3.time.StopWatch.State.4
            @Override // org.apache.commons.lang3.time.StopWatch.State
            public boolean isStarted() {
                return true;
            }

            @Override // org.apache.commons.lang3.time.StopWatch.State
            public boolean isStopped() {
                return false;
            }

            @Override // org.apache.commons.lang3.time.StopWatch.State
            public boolean isSuspended() {
                return true;
            }
        };

        public abstract boolean isStarted();

        public abstract boolean isStopped();

        public abstract boolean isSuspended();
    }

    public long getNanoTime() {
        long jNanoTime;
        long j9;
        State state = this.runningState;
        if (state == State.STOPPED || state == State.SUSPENDED) {
            jNanoTime = this.stopTime;
            j9 = this.startTime;
        } else {
            if (state == State.UNSTARTED) {
                return 0L;
            }
            if (state != State.RUNNING) {
                throw new RuntimeException("Illegal running state has occurred.");
            }
            jNanoTime = System.nanoTime();
            j9 = this.startTime;
        }
        return jNanoTime - j9;
    }

    public long getSplitNanoTime() {
        if (this.splitState == SplitState.SPLIT) {
            return this.stopTime - this.startTime;
        }
        throw new IllegalStateException("Stopwatch must be split to get the split time. ");
    }

    public long getSplitTime() {
        return getSplitNanoTime() / 1000000;
    }

    public long getStartTime() {
        if (this.runningState != State.UNSTARTED) {
            return this.startTimeMillis;
        }
        throw new IllegalStateException("Stopwatch has not been started");
    }

    public long getTime() {
        return getNanoTime() / 1000000;
    }

    public boolean isStarted() {
        return this.runningState.isStarted();
    }

    public boolean isStopped() {
        return this.runningState.isStopped();
    }

    public boolean isSuspended() {
        return this.runningState.isSuspended();
    }

    public void reset() {
        this.runningState = State.UNSTARTED;
        this.splitState = SplitState.UNSPLIT;
    }

    public void resume() {
        if (this.runningState != State.SUSPENDED) {
            throw new IllegalStateException("Stopwatch must be suspended to resume. ");
        }
        this.startTime += System.nanoTime() - this.stopTime;
        this.runningState = State.RUNNING;
    }

    public void split() {
        if (this.runningState != State.RUNNING) {
            throw new IllegalStateException("Stopwatch is not running. ");
        }
        this.stopTime = System.nanoTime();
        this.splitState = SplitState.SPLIT;
    }

    public void start() {
        State state = this.runningState;
        if (state == State.STOPPED) {
            throw new IllegalStateException("Stopwatch must be reset before being restarted. ");
        }
        if (state != State.UNSTARTED) {
            throw new IllegalStateException("Stopwatch already started. ");
        }
        this.startTime = System.nanoTime();
        this.startTimeMillis = System.currentTimeMillis();
        this.runningState = State.RUNNING;
    }

    public void stop() {
        State state = this.runningState;
        State state2 = State.RUNNING;
        if (state != state2 && state != State.SUSPENDED) {
            throw new IllegalStateException("Stopwatch is not running. ");
        }
        if (state == state2) {
            this.stopTime = System.nanoTime();
        }
        this.runningState = State.STOPPED;
    }

    public void suspend() {
        if (this.runningState != State.RUNNING) {
            throw new IllegalStateException("Stopwatch must be running to suspend. ");
        }
        this.stopTime = System.nanoTime();
        this.runningState = State.SUSPENDED;
    }

    public String toSplitString() {
        return DurationFormatUtils.formatDurationHMS(getSplitTime());
    }

    public String toString() {
        return DurationFormatUtils.formatDurationHMS(getTime());
    }

    public void unsplit() {
        if (this.splitState != SplitState.SPLIT) {
            throw new IllegalStateException("Stopwatch has not been split. ");
        }
        this.splitState = SplitState.UNSPLIT;
    }
}
