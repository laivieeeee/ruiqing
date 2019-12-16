package com.ruiqing.common.utils;


import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>Description: FastDFS文件上传下载工具类 </p>
 * <p>Copyright: Copyright (c) 2016</p>
 *
 */
public class FastDFSClient {

    private static final Logger logger = LoggerFactory.getLogger(FastDFSClient.class);

    private static final String CONFIG_FILENAME = "properties/fastdfs-client.properties";

    private volatile static StorageClient1 storageClient1 = null;

    // 初始化FastDFS Client
    static{
        try {
            storageClient1Init();
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        } catch (MyException e) {
            logger.error(e.getMessage(),e);
        }
    }

    public static void storageClient1Init() throws IOException, MyException {

        //双重校验锁
        if(storageClient1 == null){
            synchronized (FastDFSClient.class){
                if(storageClient1 == null){

                    ClientGlobal.initByProperties(CONFIG_FILENAME);
                    TrackerClient trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
                    TrackerServer trackerServer = trackerClient.getConnection();
                    if (trackerServer == null) {
                        throw new IllegalStateException("getConnection return null");
                    }

                    StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
                    if (storageServer == null) {
                        throw new IllegalStateException("getStoreStorage return null");
                    }

                    storageClient1 = new StorageClient1(trackerServer,storageServer);
                }
            }
        }
    }

    /**
     * 上传文件
     * @param file 文件对象
     * @param fileName 文件名
     * @return
     */
    public static String uploadFile(File file, String fileName) {
        return uploadFile(file,fileName,null);
    }

    /**
     * 上传文件
     * @param file 文件对象
     * @param fileName 文件名
     * @param metaList 文件元数据
     * @return
     */
    public static String uploadFile(File file, String fileName, Map<String,String> metaList) {
        try {
            if(storageClient1 == null){
                storageClient1Init();
            }
            NameValuePair[] nameValuePairs = null;
            if (metaList != null) {
                nameValuePairs = new NameValuePair[metaList.size()];
                int index = 0;
                for (Iterator<Map.Entry<String,String>> iterator = metaList.entrySet().iterator(); iterator.hasNext();) {
                    Map.Entry<String,String> entry = iterator.next();
                    String name = entry.getKey();
                    String value = entry.getValue();
                    nameValuePairs[index++] = new NameValuePair(name,value);
                }
            }
            //TODO 获取文件扩展名这里要改一下,以适应没有后缀的文件
            String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
            return storageClient1.upload_file1(file.getAbsolutePath(),prefix,nameValuePairs);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }
    
    /**
     * 上传文件
     * @param file 文件对象
     * @param fileName 文件名
     * @param metaList 文件元数据
     * @return
     */
    public static String uploadFile(byte[] file_buff, String fileName, Map<String,String> metaList) {
        try {
            if(storageClient1 == null){
                storageClient1Init();
            }
            NameValuePair[] nameValuePairs = null;
            if (metaList != null) {
                nameValuePairs = new NameValuePair[metaList.size()];
                int index = 0;
                for (Iterator<Map.Entry<String,String>> iterator = metaList.entrySet().iterator(); iterator.hasNext();) {
                    Map.Entry<String,String> entry = iterator.next();
                    String name = entry.getKey();
                    String value = entry.getValue();
                    nameValuePairs[index++] = new NameValuePair(name,value);
                }
            }
            //TODO 获取文件扩展名这里要改一下,以适应没有后缀的文件
            String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
            return storageClient1.upload_file1(file_buff, prefix, nameValuePairs);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 获取文件元数据
     * @param fileId 文件ID
     * @return
     */
    public static Map<String,String> getFileMetadata(String fileId) {
        try {
            if(storageClient1 == null){
                storageClient1Init();
            }
            NameValuePair[] metaList = storageClient1.get_metadata1(fileId);
            if (metaList != null) {
                HashMap<String,String> map = new HashMap<String, String>();
                for (NameValuePair metaItem : metaList) {
                    map.put(metaItem.getName(),metaItem.getValue());
                }
                return map;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 删除文件
     * @param fileId 文件ID
     * @return 删除失败返回-1，否则返回0
     */
    public static int deleteFile(String fileId) {
        try {
            if(storageClient1 == null){
                storageClient1Init();
            }
            return storageClient1.delete_file1(fileId);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return -1;
    }

    /**
     * 下载文件
     * @param fileId 文件ID（上传文件成功后返回的ID）
     * @param outFile 文件下载保存位置
     * @return
     */
    public static int downloadFile(String fileId, File outFile) {
        FileOutputStream fos = null;
        try {
            if(storageClient1 == null){
                storageClient1Init();
            }
            byte[] content = storageClient1.download_file1(fileId);
            fos = new FileOutputStream(outFile);
            fos.write(content);
            return 0;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            }
        }
        return -1;
    }
    

	public static byte[] downloadFile(String path) throws IOException, MyException {
		return storageClient1.download_file1(path);
	}


    public static StorageClient1 getStorageClient1() {
        try {
            if(storageClient1 == null){
                storageClient1Init();
            }
            return storageClient1;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

//    public static void setStorageClient1(StorageClient1 storageClient1) {
//        FastDFSClient.storageClient1 = storageClient1;
//    }


}
