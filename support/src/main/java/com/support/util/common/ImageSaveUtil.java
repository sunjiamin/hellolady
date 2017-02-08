package com.support.util.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.Toast;
import com.support.util.imageloader.ImageLoader;
import com.support.util.imageloader.ImageLoaderConfig;

/**
 * 图片保存工具
 */
public class ImageSaveUtil {

	private static final String TAG = "ImageSaveUtil";
	
	public static String PATH;

	private static ImageLoaderConfig mConfig = new ImageLoaderConfig.Builder().setBitmapConfig(Bitmap.Config.RGB_565).build();

	/**
	 * 判断图片是否已经保存的标志
	 */
	private static final String HAS_SAVED_FLAG = "[hassaved]";

	/**
	 * 下载图片到本地并显示在相册中 
	 *
	 * @param mContext
	 * @param imageUrl
	 */
	public static void insertImage(final Context mContext, final String imageUrl) {
		String title = getCacheKey(imageUrl);
		if (imageUrl!=null && imageUrl.endsWith(".png")) {
			title += ".png";
		} else {
			title += ".jpg";
		}
		insertImage(mContext, imageUrl, title, mConfig);
	}
	
	/**
	 * 下载图片到本地并显示在相册中 title是保存到sdcard中指定文件夹的名字，如果名字相同，图片将会被强制覆盖
	 *
	 * @param mContext
	 * @param imageUrl
	 * @param title
	 */
	public static void insertImage(final Context mContext, final String imageUrl, final String title) {
		insertImage(mContext, imageUrl, title, mConfig);
	}

	/**
	 * 下载图片到本地并显示在相册中 title是保存到sdcard中指定文件夹的名字，如果名字相同，图片将会被强制覆盖
	 *
	 * @param mContext
	 * @param imageUrl
	 * @param title
	 */
	public static void insertImage(final Context mContext, final String imageUrl, final String title,
			String imageRootDir) {
		if (imageRootDir != null && !imageRootDir.equals("")) {
			saveOfflineImage(mContext, imageUrl, title, imageRootDir);// 保存离线下载图片
		} else {
			insertImage(mContext, imageUrl, title, mConfig);
		}
	}

	/**
	 * 下载图片到本地并显示在相册中 title是保存到sdcard中指定文件夹的名字，如果名字相同，图片将会被强制覆盖
	 *
	 * @param mContext
	 * @param imageUrl
	 * @param title
	 */
	public static void insertImage(final Context mContext, final String imageUrl, final String title,
			ImageLoaderConfig config) {
		final String name = catchFileNameFromUrl(imageUrl);
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			if (config == null) {
				config = mConfig;
			}
			new AsyncTask<Void, Void, Bitmap>() {

				@Override
				protected Bitmap doInBackground(Void... params) {
					Bitmap bitmap = ImageLoader.getBitmap(mContext, imageUrl);
					return bitmap;
				}

				@Override
				protected void onPostExecute(Bitmap result) {
					File file = new File(PATH);
					try {
						ContentResolver cr = mContext.getContentResolver();
						String url = ImageSaveUtil.insertImage(cr, result, title, name, title);
						// 图片是否已经保存
						if (HAS_SAVED_FLAG.equals(url)) {
							Toast.makeText(mContext, "图片已保存!", Toast.LENGTH_SHORT).show();
							return;
						}
						updateSystemImageDataBase(mContext, url);
						Toast.makeText(mContext, "图片已保存于" + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
						return;
					} catch (IOException e) {
						e.printStackTrace();
						if (file.exists()) {// 图片被覆盖下载
							Toast.makeText(mContext, "图片已保存于" + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
						} else {// 图片保存失败
							Toast.makeText(mContext, name + "保存失败!", Toast.LENGTH_SHORT).show();
						}
					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(mContext, name + "保存失败!", Toast.LENGTH_SHORT).show();
					}
				}
			}.execute();
			// ImageLoader.getBitmap(mContext,imageUrl);
			// ImageLoader.getInstance().loadImage(imageUrl, options, new
			// ImageLoadingListener() {
			// @Override
			// public void onLoadingStarted(String s, View view) {
			// }
			//
			// @Override
			// public void onLoadingFailed(String s, View view, FailReason
			// failReason) {
			// }
			//
			// @Override
			// public void onLoadingComplete(String s, View view, Bitmap bitmap)
			// {
			//// File file = new File(PATH, name);
			// File file = new File(PATH);
			// try {
			// ContentResolver cr = mContext.getContentResolver();
			// String url = ImageUtils.insertImage(cr, bitmap, title, name,
			// title);
			// //图片是否已经保存
			// if (HAS_SAVED_FLAG.equals(url)) {
			// Toast.makeText(mContext, "图片已保存!", Toast.LENGTH_SHORT).show();
			// return;
			// }
			// updateSystemImageDataBase(mContext, url);
			// Toast.makeText(mContext, "图片已保存于" + file.getAbsolutePath(),
			// Toast.LENGTH_SHORT).show();
			// return;
			// } catch (IOException e) {
			// e.printStackTrace();
			// if (file.exists()) {//图片被覆盖下载
			// Toast.makeText(mContext, "图片已保存于" + file.getAbsolutePath(),
			// Toast.LENGTH_SHORT).show();
			// } else {//图片保存失败
			// Toast.makeText(mContext, name + "保存失败!", Toast.LENGTH_SHORT).show();
			// }
			// } catch (Exception e) {
			// e.printStackTrace();
			// Toast.makeText(mContext, name + "保存失败!", Toast.LENGTH_SHORT).show();
			// }
			// }
			//
			// @Override
			// public void onLoadingCancelled(String s, View view) {
			// Toast.makeText(mContext, name + "保存失败!", Toast.LENGTH_SHORT).show();
			// }
			// });
		} else {
			Toast.makeText(mContext, "保存失败！请插入存储卡", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * Insert an image and create a thumbnail for it.
	 *
	 * @param cr
	 *            The content resolver to use
	 * @param source
	 *            The stream to use for the image
	 * @param title
	 *            The name of the image
	 * @param name
	 *            The name of the local image
	 * @param description
	 *            The description of the image
	 * @return The URL to the newly created image, or <code>null</code> if the
	 *         image failed to be stored for any reason.
	 */
	public static final String insertImage(ContentResolver cr, Bitmap source, String title, String name,
			String description) throws IOException {
		File saveDir = new File(PATH);
		// 检查后缀
		if (!name.endsWith(".jpg") && !name.endsWith(".png")) {
			name += ".jpg";
		}
		File saveFile = new File(saveDir, name);
		if (!saveDir.exists()) {
			saveDir.mkdirs();
			saveFile.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(saveFile);
		if (source != null) {
			source.compress(Bitmap.CompressFormat.JPEG, 75, fos);
		}
		ContentValues values = new ContentValues();
		values.put(MediaStore.Images.Media.DATA, saveFile.getAbsolutePath());
		values.put(MediaStore.Images.Media.TITLE, title);
		values.put(MediaStore.Images.Media.DESCRIPTION, description);
		values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
		Uri uri = cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
		if (uri == null) {
			return HAS_SAVED_FLAG;
		}
		return uri.toString();
	}

	/**
	 * 存储缩略图
	 *
	 * @param cr
	 * @param source
	 * @param id
	 * @param width
	 * @param height
	 * @param kind
	 * @return
	 */
	private static final Bitmap storeThumbnail(ContentResolver cr, Bitmap source, long id, float width, float height,
			int kind) {
		// create the matrix to scale it
		Matrix matrix = new Matrix();

		float scaleX = width / source.getWidth();
		float scaleY = height / source.getHeight();

		matrix.setScale(scaleX, scaleY);

		Bitmap thumb = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);

		ContentValues values = new ContentValues(4);
		values.put(MediaStore.Images.Thumbnails.KIND, kind);
		values.put(MediaStore.Images.Thumbnails.IMAGE_ID, (int) id);
		values.put(MediaStore.Images.Thumbnails.HEIGHT, thumb.getHeight());
		values.put(MediaStore.Images.Thumbnails.WIDTH, thumb.getWidth());

		Uri url = cr.insert(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, values);

		try {
			OutputStream thumbOut = cr.openOutputStream(url);

			thumb.compress(Bitmap.CompressFormat.JPEG, 100, thumbOut);
			thumbOut.close();
			return thumb;
		} catch (FileNotFoundException ex) {
			return null;
		} catch (IOException ex) {
			return null;
		}
	}

	private static String getCacheKey(String key) {
		String stdKey = null;
		if (key != null && key.trim().length() > 0) {
			stdKey = UUID.nameUUIDFromBytes(key.getBytes()).toString();
		}
		return stdKey;
	}

	/**
	 * 获取下载的图片文件名
	 *
	 * @param imageUrl
	 * @return
	 */
	private static String catchFileNameFromUrl(String imageUrl) {
		if (!TextUtils.isEmpty(imageUrl)) {
			int index = imageUrl.lastIndexOf("/");
			if (index > 0) {
				String name = imageUrl.substring(index + 1);
				if (!name.endsWith(".jpg") && !name.endsWith(".png")) {
					name += ".jpg";
				}
				return name;
			}
		}
		return "";
	}

	/**
	 * 更新系统图库
	 *
	 * @param mContext
	 * @param url
	 */
	private static void updateSystemImageDataBase(final Context mContext, final String url) {
		Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		Uri uri = Uri.parse(url);
		intent.setData(uri);
		mContext.sendBroadcast(intent);
	}

	/**
	 * 保存离线图片
	 *
	 * @param mContext
	 * @param imageUrl
	 * @param title
	 * @param imageRootDir
	 */
	private static void saveOfflineImage(final Context mContext, final String imageUrl, final String title,
			String imageRootDir) {
		String name = catchFileNameFromUrl(imageUrl);
		String localImageUrl = imageUrl.replace("http://", "");
		String path = imageRootDir + "/" + localImageUrl;
		if (!path.endsWith(".cache")) {
			path = path + ".cache";
		}
		try {
			ContentResolver cr = mContext.getContentResolver();
			Bitmap source = BitmapFactory.decodeFile(path);
			String url = ImageSaveUtil.insertImage(cr, source, title, name, title);
			updateSystemImageDataBase(mContext, url);
			Toast.makeText(mContext, name + "保存成功!", Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(mContext, name + "保存失败", Toast.LENGTH_SHORT).show();
		}
	}
}
