package com.ichunming.mg.common.util;

import java.util.Random;

public class RandomUtil {
	private static char[] digitalArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	private static char[] letterArray = { 'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e',
			'E', 'f', 'F', 'g', 'G', 'h', 'H', 'i', 'I', 'j', 'J', 'k', 'K',
			'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P', 'q', 'Q', 'r',
			'R', 's', 'S', 't', 'T', 'u', 'U', 'v', 'V', 'w', 'W', 'x', 'X',
			'y', 'Y', 'z', 'Z' };
	private static char[] characterArray = { 'a', 'A', '0', 'b', 'B', 'c', 'C', '1',
			'd', 'D', 'e', '6', 'E', 'f', 'F', 'g', 'G', '8', 'h', 'H', 'i',
			'I', 'j', '2', 'J', 'k', '7', 'K', 'l', 'L', '3', 'm', 'M', 'n',
			'N', 'o', 'O', '5', 'p', 'P', 'q', 'Q', 'r', 'R', 's', 'S', 't',
			'T', '9', 'u', 'U', 'v', 'V', 'w', 'W', 'x', '4', 'X', 'y', 'Y',
			'z', 'Z'};

	/**
	 * 生成指定长度的随机数字组合的字符串
	 * @param length
	 * @return
	 */
	public static String genDigitalString(int length) {
		int i;
		int count = 0;

		StringBuffer digitalString = new StringBuffer();
		Random r = new Random();
		while (count < length) {
			i = Math.abs(r.nextInt(digitalArray.length));
			if (i >= 0 && i < digitalArray.length) {
				digitalString.append(digitalArray[i]);
				count++;
			}
		}

		return digitalString.toString();
	}
	
	/**
	 * 生成指定长度的随机字母组合的字符串
	 * @param length
	 * @return
	 */
	public static String genLetterString(int length) {
		int i;
		int count = 0;

		StringBuffer letterString = new StringBuffer("");
		Random r = new Random();
		while (count < length) {
			i = Math.abs(r.nextInt(letterArray.length));
			if (i >= 0 && i < letterArray.length) {
				letterString.append(letterArray[i]);
				count++;
			}
		}

		return letterString.toString();
	}
	
	/**
	 * 生成指定长度的随机字符组合的字符串
	 * @param length
	 * @return
	 */
	public static String genCharacterString(int length) {
		int i;
		int count = 0;

		StringBuffer characterString = new StringBuffer("");
		Random r = new Random();
		while (count < length) {
			i = Math.abs(r.nextInt(characterArray.length));
			if (i >= 0 && i < characterArray.length) {
				characterString.append(characterArray[i]);
				count++;
			}
		}

		return characterString.toString();
	}
}
