package com.congnhanvienthong.qltt;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.ksoap2.serialization.SoapObject;

import android.content.res.Resources;


public class Utilities {
	public static String LoadCerFile(String fileName, Resources resources)
			throws IOException {
		InputStream iS;
		iS = resources.getAssets().open(fileName);
		byte[] buffer = new byte[iS.available()];
		iS.read(buffer);
		ByteArrayOutputStream oS = new ByteArrayOutputStream();
		oS.write(buffer);
		oS.close();
		iS.close();
		return oS.toString();
	}

	public static void GetObjectFromSoapObject(Object object,
			SoapObject soapObject) {
		try {
			@SuppressWarnings("rawtypes")
			// Field[] filFields = clazz.getFields();
			Class clazz = object.getClass();

			Field[] filFields = clazz.getDeclaredFields();

			for (Field field : filFields) {
				field.setAccessible(true);
				String name = field.getName();
				Object value;
				if (soapObject.hasProperty(name)) {
					value = soapObject.getProperty(name);
				} else {
					value = "Không có thông tin";
				}
				Class<?> type = field.getType();
				String typeName = type.getName();
				if (typeName.contains("String")) {
					field.set(object, value.toString());
				}
				if (typeName.contains("int")) {
					field.setInt(object, Integer.valueOf(value.toString()));
				}
				if (typeName.contains("float")) {
					field.setFloat(object, Float.valueOf(value.toString()));
				}
				if (typeName.contains("boolean")) {
					field.setBoolean(object, Boolean.valueOf(value.toString()));
				}
				if (typeName.contains("long")) {
					field.setLong(object, Long.valueOf(value.toString()));
				}
			}

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static String md5(String input) {

		try {

			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			try {
				messageDigest
						.update(input.getBytes("UTF-8"), 0, input.length());
				byte[] encryptionKeyBytes = messageDigest.digest();

				BigInteger number = new BigInteger(1, encryptionKeyBytes);

				return String.format("%032x", number);

			} catch (UnsupportedEncodingException e) {

			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

}