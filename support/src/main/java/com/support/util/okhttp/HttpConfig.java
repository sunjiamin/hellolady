package com.support.util.okhttp;

import java.io.File;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

import android.content.Context;

/**
 * OkHttp网络访问配置
 * 
 */
public class HttpConfig {

    private File cacheDir; // 缓存目录
    private long cacheSize; // 缓存大小
    private int cacheTime; // 缓存时间

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    private TimeUnit timeUnit; // 缓存时间
    private Map<String, String> heads; // 默认请求头

    private int connectTimeout;
    private boolean retryOnConnectionFailure;
    private Proxy proxy;
    private ProxySelector proxySelector;
    private long readTimeOut;
    private long writeTimeOut;
    private SocketFactory socketFactory;
    private SSLSocketFactory sslSocketFactory;

    private HttpConfig(Builder builder) {
        this.cacheDir = builder.cacheDir;
        this.cacheSize = builder.cacheSize;
        this.cacheTime = builder.cacheTime;
        this.connectTimeout = builder.connectTimeout;
        this.proxy = builder.proxy;
        this.proxySelector = builder.proxySelector;
        this.readTimeOut = builder.readTimeOut;
        this.writeTimeOut = builder.writeTimeOut;
        this.socketFactory = builder.socketFactory;
        this.sslSocketFactory = builder.sslSocketFactory;
    }

    private HttpConfig() {
    }

    public File getCacheDir() {
        return cacheDir;
    }

    public long getCacheSize() {
        return cacheSize;
    }

    public int getCacheTime() {
        return cacheTime;
    }

    public Map<String, String> getHeads() {
        return heads;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public boolean isRetryOnConnectionFailure() {
        return retryOnConnectionFailure;
    }

    public Proxy getProxy() {
        return proxy;
    }

    public ProxySelector getProxySelector() {
        return proxySelector;
    }

    public long getReadTimeOut() {
        return readTimeOut;
    }

    public long getWriteTimeOut() {
        return writeTimeOut;
    }

    public SocketFactory getSocketFactory() {
        return socketFactory;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return sslSocketFactory;
    }

    /**
     * 创建默认的配置
     * 
     * @return
     */
    public static HttpConfig createDefault(Context context) {
        HttpConfig config = new HttpConfig();
        config.cacheDir = createCacheDir(context);
        config.cacheSize = 10 * 1024 * 1014;// 默认缓存空间大小 10MB
        config.cacheTime = 60 * 60 * 24 * 365; // 本地缓存时间
        config.connectTimeout = 15;
        config.readTimeOut = 300;
        config.writeTimeOut = 30;
        config.heads = new HashMap<String, String>();
        // config.heads.put("User-Agent","Android APP");
        return config;
    }

    /**
     * 创建磁盘缓存目录
     *
     * @param context
     * @return
     */
    private static File createCacheDir(Context context) {
        String cachePath = context.getCacheDir().getAbsolutePath() + File.separatorChar + "http_cache";
        File cache = new File(cachePath);
        if (!cache.exists()) {
            cache.mkdirs();
        }
        return cache;
    }

    public static class Builder {
        private File cacheDir; // 缓存目录
        private long cacheSize; // 缓存大小
        private int cacheTime; // 缓存时间
        private Map<String, String> heads; // 默认请求头

        private int connectTimeout;
        private Proxy proxy;
        private ProxySelector proxySelector;
        private long readTimeOut;
        private long writeTimeOut;
        private SocketFactory socketFactory;
        private SSLSocketFactory sslSocketFactory;

        public Builder() {
            heads = new HashMap<String, String>();
        }

        public Builder setCacheDir(File cacheDir) {
            this.cacheDir = cacheDir;
            return this;
        }

        public Builder setCacheSize(long cacheSize) {
            this.cacheSize = cacheSize;
            return this;
        }

        public Builder setCacheTime(int cacheTime) {
            this.cacheTime = cacheTime;
            return this;
        }

        public Builder setConnectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder addHead(String key, String value) {
            this.heads.put(key, value);
            return this;
        }

        public Builder setProxy(Proxy proxy) {
            this.proxy = proxy;
            return this;
        }

        public Builder setProxySelector(ProxySelector proxySelector) {
            this.proxySelector = proxySelector;
            return this;
        }

        public Builder setReadTimeOut(long readTimeOut) {
            this.readTimeOut = readTimeOut;
            return this;
        }

        public Builder setWriteTimeOut(long writeTimeOut) {
            this.writeTimeOut = writeTimeOut;
            return this;
        }

        public Builder setSocketFactory(SocketFactory socketFactory) {
            this.socketFactory = socketFactory;
            return this;
        }

        public Builder setSslSocketFactory(SSLSocketFactory sslSocketFactory) {
            this.sslSocketFactory = sslSocketFactory;
            return this;
        }

        public HttpConfig build() {
            HttpConfig config = new HttpConfig(this);
            return config;
        }
    }

}
