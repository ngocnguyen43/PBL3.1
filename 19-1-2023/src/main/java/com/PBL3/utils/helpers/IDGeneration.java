package com.PBL3.utils.helpers;

import java.util.Random;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;

public class IDGeneration {
	private static final char[] salt = "_-0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
			.toCharArray();

	public static String generate() {
		Random random = new Random();
		String nanoId = NanoIdUtils.randomNanoId(random, salt, 23);
		return nanoId;
	}
}
