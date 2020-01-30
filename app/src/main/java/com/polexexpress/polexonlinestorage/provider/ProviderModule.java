package com.polexexpress.polexonlinestorage.provider;

public final class ProviderModule {
    public static ProviderImpl getProvider() {
        return Provider.S_PROVIDER;
    }

    private static final class Provider {
        private static final ProviderImpl S_PROVIDER = new ProviderImpl();
    }
}
