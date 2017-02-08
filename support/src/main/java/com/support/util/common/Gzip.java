package com.support.util.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class Gzip {

	/**
	 * 解压缩 GZIP 二进制数据流
	 *
	 * @param bytes 用 GZIP 压缩的二进制数据
	 * @return 解压缩后的二进制数据流
	 * @throws IOException
	 */
	public static byte[] unGzipBinary(byte[] bytes) throws IOException {
		if (bytes == null || bytes.length == 0) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		GZIPInputStream gunzip = new GZIPInputStream(in);
		byte[] buffer = new byte[256];
		int n;
		while ((n = gunzip.read(buffer))>= 0) {
			out.write(buffer, 0, n);
		}
		in.close();

		byte[] result = out.toByteArray();
		out.close();
		return result;
	}

}
