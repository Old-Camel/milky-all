package cn.sliew.milky.common;

public enum DisabledTicker implements Ticker {
    INSTANCE;

    @Override
    public long read() {
        return 0L;
    }
}
