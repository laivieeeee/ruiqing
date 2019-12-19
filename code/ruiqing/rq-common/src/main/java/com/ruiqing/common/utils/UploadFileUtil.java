package com.ruiqing.common.utils;

import com.ruiqing.common.constans.GdProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UploadFileUtil {

	private static Logger logger = LoggerFactory.getLogger(UploadFileUtil.class);

	private static GdProperties gDproperties = null;

	static {
		gDproperties = (GdProperties) SpringUtils.getContext().getBean("gdProperties");
	};

	private static final String FASTDFS_SERVER_URL = gDproperties.getProperties().getProperty("fastdfs.server.url").trim();

	//单文件上传
	public static Map<String,Object> uploadFile(HttpServletRequest request, HttpServletResponse response){
		return null;
	}

	//单文件上传
	public static String uploadFile(File file){
		return FastDFSClient.uploadFile(file,file.getName());
	}

	//多文件上传
	public static Map<String,Object> uploadMultiFile(HttpServletRequest request, HttpServletResponse response){
		Map<String,Object> result = new HashMap();
		try{
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json; charset=utf-8");

			int index = 0;
			for(Part part:request.getParts()){
				if(part == null){
					continue;
				}
				String header = part.getHeader("content-disposition");
				String fileName = getFileName(header);
				File tmpFile = File.createTempFile("",fileName);
				part.write(tmpFile.getAbsolutePath());
				String fid = FastDFSClient.uploadFile(tmpFile,tmpFile.getName());
				tmpFile.delete();
				result.put(""+index++,fid);
			}
			return result;
		}catch(Exception e){
			logger.error("文件上传失败",e);
			return null;
		}

	}

	public static Map<String,Object> uploadImage(HttpServletRequest request, String spic, String suffix){
		return null;
	}

	public static String getFileName(String header) {
		/**
		 * String[] tempArr1 = header.split(";");代码执行完之后，在不同的浏览器下，tempArr1数组里面的内容稍有区别
		 * 火狐或者google浏览器下：tempArr1={form-data,name="file",filename="snmp4j--api.zip"}
		 * IE浏览器下：tempArr1={form-data,name="file",filename="E:\snmp4j--api.zip"}
		 */
		String[] tempArr1 = header.split(";");
		/**
		 *火狐或者google浏览器下：tempArr2={filename,"snmp4j--api.zip"}
		 *IE浏览器下：tempArr2={filename,"E:\snmp4j--api.zip"}
		 */
		String[] tempArr2 = tempArr1[2].split("=");
		//获取文件名，兼容各种浏览器的写法
		return tempArr2[1].substring(tempArr2[1].lastIndexOf("\\")+1).replaceAll("\"", "");
	}

	public static File imageFromBase64String2File(String spic,String suffix) throws IOException {
		//base64解密
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] bpic = decoder.decodeBuffer(spic);
		//字节码转图片
		ByteArrayInputStream bais = new ByteArrayInputStream(bpic);
		BufferedImage bi = ImageIO.read(bais);
		//创建临时文件
		File w2 = File.createTempFile("img","."+suffix);
		ImageIO.write(bi, suffix, w2);
		return w2;
	}

	public static File imageFromString2File(String spic,String suffix) throws IOException {

		BufferedImage bi = ImageIO.read(new ByteArrayInputStream(spic.getBytes()));
		//创建临时文件
		File w2 = File.createTempFile("img","."+suffix);
		ImageIO.write(bi, suffix, w2);
		return w2;
	}

}