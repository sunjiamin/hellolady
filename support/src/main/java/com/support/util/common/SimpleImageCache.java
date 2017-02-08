package com.support.util.common;

import android.graphics.Bitmap;
import android.util.LruCache;


/**
 * 简单的图片一级缓存
 * 
 * @ClassName: SimpleImageCache
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author sunjiamin
 * @date 2015-9-18 上午10:28:24
 * 
 */
public class SimpleImageCache {

	private SimpleImageCache() {
		// use 1/8 of available heap size
		cache = new LruCache<String, Bitmap>((int) (Runtime.getRuntime()
				.maxMemory() / 8)) {
			@Override
			protected int sizeOf(String key, Bitmap value) {
				return value.getRowBytes() * value.getHeight();
			}
		};
	}

	private static SimpleImageCache imageCache = null;

	public static synchronized SimpleImageCache getInstance() {
		if (imageCache == null) {
			imageCache = new SimpleImageCache();
		}
		return imageCache;

	}

	private LruCache<String, Bitmap> cache = null;

	/**
	 * put bitmap to image cache
	 * 
	 * @param key
	 * @param value
	 * @return the puts bitmap
	 */
	public Bitmap put(String key, Bitmap value) {
		return cache.put(key, value);
	}

	/**
	 * return the bitmap
	 * 
	 * @param key
	 * @return
	 */
	public Bitmap get(String key) {
		return cache.get(key);
	}
}
