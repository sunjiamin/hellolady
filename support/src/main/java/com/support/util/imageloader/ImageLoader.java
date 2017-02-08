package com.support.util.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.Listener;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Transformation;
import com.squareup.picasso.UrlConnectionDownloader;

import java.io.File;
import java.io.IOException;

import static android.os.Build.VERSION.SDK_INT;

/**
 * 图片下载工具类
 *
 */
public class ImageLoader {

	private static final String TAG = "ImageLoader";

	// 图片缓存目录,在Picasso第一次实例化之前初始化才有效。否则取默认设定的目录
	public static File cacheDir;
	private static final String JOURNAL_FILE = "journal";
	private static final String PICASSO_CACHE = "picasso-bitmaps";
	private static int MIN_DISK_CACHE_SIZE = 5 * 1024 * 1024; // 5MB
	private static int MAX_DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB

	// 加载类型
	public static final int NETWORK = 0; // 网络下载
	public static final int FILE = 1; // 本地文件
	public static final int ASSETS = 2; // assets下的图片资源
	public static final int RESROUCEID = 3; // 按照指定资源id读取图片
	public static final int URI = 4; // 按照指定Uri读取图片

	/**
	 * 初始化默认Picaso 在应用程序入口处调用 一般情况这个已经够用了
	 *
	 * 设置磁盘缓存目录
	 * 
	 * @param context
	 */
	public static void init(Context context) {
		Picasso.Builder builder = new Picasso.Builder(context);
		// 创建下载器、指定图片缓存目录
		builder.downloader(createDownloader(context));
		builder.listener(new Listener() {

			@Override
			public void onImageLoadFailed(Picasso arg0, Uri arg1, Exception arg2) {
				if (arg2 != null) {
					arg2.printStackTrace();
				}
			}
		});
		Picasso.setSingletonInstance(builder.build());
	}

	/**
	 * 初始化picaso配置，开发人员自定义
	 * 
	 * @param builder
	 */
	public static void init(Picasso.Builder builder) {
		if (builder != null) {
			Picasso.setSingletonInstance(builder.build());
		}
	}

	/**
	 * 加载图片资源
	 * 
	 * @param context
	 * @param url
	 *            图片地址(网络地址、本地文件路径)
	 * @param imageView
	 *            ImageView填充组件
	 */
	public static void loadImage(Context context, String url, ImageView imageView) {
		load(context, creatorRequest(context, NETWORK, url), imageView, null, null, null);
	}

	/**
	 * 加载图片资源，可配置
	 * 
	 * @param context
	 * @param url
	 *            图片地址(网络地址、本地文件路径)
	 * @param imageView
	 * @param imageLoaderConfig
	 *            图片下载配置
	 */
	public static void loadImage(Context context, String url, ImageView imageView,
			ImageLoaderConfig imageLoaderConfig) {
		load(context, creatorRequest(context, NETWORK, url), imageView, imageLoaderConfig, null, null);
	}

	/**
	 * 加载载图片资源，可配置
	 * 
	 * @param context
	 * @param url
	 *            图片地址(网络地址、本地文件路径)
	 * @param imageView
	 * @imageLoaderConfig 下载配置
	 * @param imageLoaderListener
	 *            下载监听器
	 */
	public static void loadImage(Context context, String url, ImageView imageView, ImageLoaderConfig imageLoaderConfig,
			ImageLoadingListener imageLoaderListener) {
		load(context, creatorRequest(context, NETWORK, url), imageView, imageLoaderConfig, imageLoaderListener, null);
	}

	/**
	 * 从磁盘加载本地图片资源
	 * 
	 * @param context
	 * @param file
	 * @param imageView
	 * @param imageLoaderConfig
	 */
	public static void loadImageFromFile(Context context, File file, ImageView imageView,
			ImageLoaderConfig imageLoaderConfig) {
		load(context, creatorRequest(context, FILE, file), imageView, imageLoaderConfig, null, null);
	}


	public static void loadImageFromFile(Context context, File file, ImageView imageView,
			ImageLoaderConfig imageLoaderConfig, Transformation transformation) {
		load(context, creatorRequest(context, FILE, file), imageView, imageLoaderConfig, null, transformation);
	}

	/**
	 * 从存储介质加载本地图片资源
	 * 
	 * @param context
	 * @param file
	 * @param imageView
	 * @param imageLoaderConfig
	 */
	public static void loadImageFromFile(Context context, File file, ImageView imageView,
			ImageLoaderConfig imageLoaderConfig, ImageLoadingListener imageLoaderListener) {
		load(context, creatorRequest(context, FILE, file), imageView, imageLoaderConfig, imageLoaderListener, null);
	}

	/**
	 * 从assert加载图片资源
	 * 
	 * @param context
	 * @param resName
	 *            assert下载图片资源如: xxx.png
	 * @param imageView
	 * @param imageLoaderConfig
	 */
	public static void loadImageFromAssets(Context context, String resName, ImageView imageView,
			ImageLoaderConfig imageLoaderConfig, ImageLoadingListener imageLoaderListener) {
		load(context, creatorRequest(context, ASSETS, resName), imageView, imageLoaderConfig, imageLoaderListener,
				null);
	}

	/**
	 * 加载本地资源图片
	 * 
	 * @param context
	 * @param resId
	 *            资源id : R.drawable.landing_screen
	 * @param imageView
	 * @param imageLoaderConfig
	 */
	public static void loadImageFromRes(Context context, int resId, ImageView imageView,
			ImageLoaderConfig imageLoaderConfig, ImageLoadingListener imageLoaderListener) {
		load(context, creatorRequest(context, RESROUCEID, resId), imageView, imageLoaderConfig, imageLoaderListener,
				null);
	}

	/**
	 * 从Uri中加载图片资源
	 * 
	 * @param context
	 * @param uri
	 * @param imageView
	 * @param imageLoaderConfig
	 */
	public static void loadImageFromUri(Context context, Uri uri, ImageView imageView,
			ImageLoaderConfig imageLoaderConfig, ImageLoadingListener imageLoaderListener) {
		load(context, creatorRequest(context, URI, uri), imageView, imageLoaderConfig, imageLoaderListener, null);
	}

	/**
	 * 取消将要下载的图片任务
	 * 
	 * @param context
	 * @param tag
	 *            任务标记
	 */
	public static void cancleByTag(Context context, Object tag) {
		if (context == null || tag == null)
			throw new IllegalArgumentException("context==null |  tag==null");
		Picasso.with(context).cancelTag(tag);
	}

	/**
	 * 加载图片
	 * 
	 * @param context
	 * @param requestCreator
	 * @param imageView
	 *            ImageView组件
	 * @param imageLoaderListener
	 */
	private static void load(Context context, RequestCreator requestCreator, ImageView imageView,
			ImageLoaderConfig imageLoaderConfig, final ImageLoadingListener imageLoaderListener,
			final Transformation transformation) {
		if (context == null || requestCreator == null || imageView == null) {
			Log.e(TAG, "context==null | requestCreator ==null | imageView==null");
			if (imageLoaderListener != null) {
				imageLoaderListener.onError();
			}
			return;
		}

		if (imageLoaderConfig != null) {
			// 设置请求tag标记
			if (imageLoaderConfig.getTag() != null)
				requestCreator.tag(imageLoaderConfig.getTag());
			// 设置下载图片优先级
			if (imageLoaderConfig.getPriority() != null) {
				switch (imageLoaderConfig.getPriority()) {
				case HIGH:
					requestCreator.priority(Picasso.Priority.HIGH);
					break;
				case NORMAL:
					requestCreator.priority(Picasso.Priority.NORMAL);
					break;
				case LOW:
					requestCreator.priority(Picasso.Priority.LOW);
					break;
				}
			}

			// 设置解码类型
			if (imageLoaderConfig.getDecodingOptions() != null) {
				requestCreator.config(imageLoaderConfig.getDecodingOptions());
			}

			if (imageLoaderConfig.getErrorResId() > 0) {
				// 设置下载失败默认图
				requestCreator.error(imageLoaderConfig.getErrorResId());
			}

			if (imageLoaderConfig.getDefResId() > 0) {
				// 设置下载之前默认图
				requestCreator.placeholder(imageLoaderConfig.getDefResId());
			} else {
				requestCreator.noPlaceholder();
			}

			// 取消淡出动画
			if (imageLoaderConfig.isNoFade()) {
				requestCreator.noFade();
			}
			// 设置图片目标尺寸
			if (imageLoaderConfig.getImageSize() != null) {
				requestCreator.resize(imageLoaderConfig.getImageSize().getTargetWidth(),
						imageLoaderConfig.getImageSize().getTargetHeight());
				if (imageLoaderConfig.getImageSize().getResizeScaleType() == ImageTargetSize.CENTER_CROP) {
					requestCreator.centerCrop();
				} else if (imageLoaderConfig.getImageSize().getResizeScaleType() == ImageTargetSize.CENTER_INSIDE) {
					requestCreator.centerInside();
				}
			}
			if (!TextUtils.isEmpty(imageLoaderConfig.getStableKey())) {
				requestCreator.stableKey(imageLoaderConfig.getStableKey());
			}
			if (imageLoaderConfig.getRotateDegree() != 0) {
				requestCreator.rotate(-imageLoaderConfig.getRotateDegree());
			}
		} else {
			requestCreator.noPlaceholder();
		}
		if (transformation != null) {
			requestCreator.transform(transformation);
		}

		requestCreator.into(imageView, new Callback() {
			@Override
			public void onSuccess() {
				if (imageLoaderListener != null) {
					imageLoaderListener.onSuccess();
				}
			}

			@Override
			public void onError() {
				if (imageLoaderListener != null) {
					imageLoaderListener.onError();
				}
			}
		});
	}

	/**
	 * 根据加载类型创建 RequestCreator 支持网络http图片资源、本地图片File加载、Assert下图片文件 加载
	 * 
	 * @param context
	 * @param loadType
	 *            加载类型 默认为NETWORK
	 * @param obj
	 *            加载对象 (网络图片地址), (磁盘图片：File)
	 *            ，(Assert:file:///android_asset/文件名)，（磁盘图片：完整路径）
	 * @return
	 */
	private static RequestCreator creatorRequest(Context context, int loadType, Object obj) {
		if (context == null || obj == null || "".equals(obj)) {
			return null;
		}
		RequestCreator requestCreator = null;
		switch (loadType) {
		case NETWORK: // 网络
			if (!checkUrl(String.valueOf(obj))) {
				requestCreator = Picasso.with(context).load(String.valueOf(obj));
			}
			break;
		case ASSETS: // Assert文件下的图片资源
			requestCreator = Picasso.with(context).load("file:///android_asset/" + String.valueOf(obj));
			break;
		case FILE: // 本地图片文件
			requestCreator = Picasso.with(context).load((File) obj);
			break;
		case RESROUCEID: // 本地资源id
			requestCreator = Picasso.with(context).load((Integer) obj);
			break;
		case URI: // 指定uri
			requestCreator = Picasso.with(context).load(Uri.parse(String.valueOf(obj)));
			break;
		default:
		}
		return requestCreator;
	}

	/**
	 * 根据图片地址或者路径获取bitmap对象
	 * 
	 * @param context
	 * @param uri
	 * @return
	 */
	public static Bitmap getBitmap(Context context, String uri) {
		if (context == null || uri == null)
			throw new IllegalArgumentException("context and uri can't be null");
		try {
			return Picasso.with(context).load(uri).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取已缓存所有图片大小
	 * 
	 * @return
	 */
	public synchronized static long getCacheSize() {
		long size = 0;
		if (cacheDir != null && cacheDir.exists()) {
			File[] files = cacheDir.listFiles();
			if (files != null) {
				for (File imageCache : files) {
					if (imageCache.isFile()) {
						size += imageCache.length();
					}
				}
			}
		}
		return size;
	}

	/**
	 * 清除磁盘缓存图片
	 */
	public static void clearCache() {
		if (cacheDir != null && cacheDir.exists()) {
			File[] files = cacheDir.listFiles();
			if (files != null)
				for (File imageCache : files) {
					if (imageCache.getName().equals(JOURNAL_FILE))
						continue;
					imageCache.delete();
				}
		}
	}

	/**
	 * 创建图片下载对象
	 * 
	 * @param context
	 * @return
	 */
	private static Downloader createDownloader(Context context) {
		if (Build.VERSION.SDK_INT >= 9) {
			try {
				Class.forName("com.squareup.okhttp.OkHttpClient");
				if (cacheDir == null) {
					cacheDir = createCacheDir(context);
				}
				return new OkHttpDownloader(cacheDir, calculateDiskCacheSize(cacheDir));
			} catch (ClassNotFoundException ignored) {
				ignored.printStackTrace();
			}
		}
		return new UrlConnectionDownloader(context);
	}

	/**
	 * 创建磁盘缓存目录
	 * 
	 * @param context
	 * @return
	 */
	private static File createCacheDir(Context context) {
		try {
			String cachePath = getBitmapCachePath(context, PICASSO_CACHE);
			File cache = new File(cachePath);
			if (cache != null && !cache.exists()) {
				cache.mkdirs();
			}
			return cache;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取图片缓存路径
	 * 
	 * @param context
	 * @param paramName
	 *            目录名
	 * @return
	 */
	private static String getBitmapCachePath(Context context, String paramName) {
		String filepath = "";
		filepath = context.getCacheDir().getAbsolutePath() + File.separatorChar + paramName;
		if (isSDCardAvaliable()) {
			if (SDK_INT <= 9) {
				filepath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separatorChar + paramName;
			} else if (context.getExternalCacheDir() != null) {
				filepath = context.getExternalCacheDir().getAbsolutePath() + File.separatorChar + paramName;
			}
		}
		return filepath;
	}

	private static boolean isSDCardAvaliable() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}

	/**
	 * 返回设置磁盘缓存大小
	 * 
	 * @param dir
	 * @return
	 */
	private static long calculateDiskCacheSize(File dir) {
		long size = MIN_DISK_CACHE_SIZE;

		try {
			StatFs statFs = new StatFs(dir.getAbsolutePath());
			long available = ((long) statFs.getBlockCount()) * statFs.getBlockSize(); // 磁盘空间
			// long surplus = ((long) statFs.getAvailableBlocks()) *
			// statFs.getBlockSize(); //磁盘剩余可用空间

			size = available / 50;
			size = Math.min(size, MAX_DISK_CACHE_SIZE);
			// if(size > surplus || surplus < MIN_DISK_CACHE_SIZE){
			// size = surplus / 2;
			// MIN_DISK_CACHE_SIZE = (int) (surplus / 2);
			// }
		} catch (IllegalArgumentException ignored) {
		}

		return Math.max(size, MIN_DISK_CACHE_SIZE);
	}

	/**
	 * 检查url,返回true为不合法url
	 * 
	 * @param url
	 * @return
	 */
	private static boolean checkUrl(String url) {
		if (TextUtils.isEmpty(url) || !url.startsWith("http")) {
			return true;
		}
		return false;
	}
}
