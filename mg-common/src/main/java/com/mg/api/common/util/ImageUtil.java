/**
 * image util
 * ming 2016/12/22
 */
package com.mg.api.common.util;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;

public class ImageUtil {

	/**
	 * 图片裁剪
	 * @param src
	 * @param dest
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @throws IOException
	 */
	public static void cut(String src, String dest, int x, int y, int width, int height) throws IOException{
		InputStream input = null;
		OutputStream output = null;
		
		try {
			input = new FileInputStream(src);
			output = new FileOutputStream(dest);
			
			// 原图
            BufferedImage srcImg = ImageIO.read(input);
            int orgWidth = srcImg.getWidth();
            int orgHeight = srcImg.getHeight();
            
            // 坐标计算
            x = Math.max(x, 0);
            y = Math.max(y, 0);
            int xx = Math.min(orgWidth - 1, x);
            int yy = Math.min(orgHeight - 1, y);

            // 宽高计算
            int newWidth = Math.max(width, 0);
            if (xx + newWidth > orgWidth) {
                newWidth = Math.max(1, orgWidth - xx);
            }
            int newHeight = Math.max(height, 0);
            if (yy + height > orgHeight) {
                newHeight = Math.max(1, orgHeight - yy);
            }
            
            // 图片裁剪
            BufferedImage destImg = srcImg.getSubimage(xx, yy, newWidth, newHeight);
            BufferedImage tag = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(destImg, 0, 0, Color.white, null);
            ImageIO.write(tag, "png", output);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
        }
	}
	
	/**
	 * 图片压缩--最大宽高,等比例
	 * @param src
	 * @param dest
	 * @param maxWidth
	 * @param maxHeight
	 * @throws IOException
	 */
	public static void resize(String src, String dest, int maxWidth, int maxHeight) throws IOException{
		InputStream input = null;
		OutputStream output = null;
		
		try {
			input = new FileInputStream(src);
			output = new FileOutputStream(dest);
			
			// 原图
            BufferedImage srcImg = ImageIO.read(input);
            int orgWidth = srcImg.getWidth();
            int orgHeight = srcImg.getHeight();

            float oriRadio = orgWidth / orgHeight;
    		float cRadio = maxWidth / maxHeight;
    		
    		// 宽高计算
    		int newWidth = 0;
    		int newHeight = 0;
            if ((oriRadio-cRadio) < Float.MIN_VALUE) {  
            	newWidth = maxWidth;
            	newHeight = (int) Math.round(orgHeight * ((float) maxWidth / orgWidth));  
            } else {
            	newWidth = (int) Math.round(orgWidth * ((float) maxHeight / orgHeight));  
            	newHeight = maxHeight;
            }

            // 图片压缩
            BufferedImage tag = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(srcImg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
            ImageIO.write(tag, "png", output);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
        }
	}
}
