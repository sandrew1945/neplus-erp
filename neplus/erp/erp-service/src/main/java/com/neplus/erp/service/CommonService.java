package com.neplus.erp.service;


import com.neplus.erp.model.TmFilePO;
import com.neplus.framework.core.exception.ServiceException;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;


/**
 *
 *  公共Service
 * Created by summer on 2018/2/15.
 */
public interface CommonService extends BaseService
{
    /**
     *  文件上传
     * @param relativePath  相对路径
     * @param file          上传文件
     * @return
     * @throws ServiceException
     */
    String fileUploadLocal(String relativePath, MultipartFile file) throws ServiceException;

    /**
     *  文件上传
     * @param relativePath  相对路径
     * @param base64File          上传文件
     * @return
     * @throws ServiceException
     */
    String fileUploadLocal(String relativePath, String filename, String base64File) throws ServiceException;

    /**
     *  Download the specific file by relative path.
     * @param relativePath  相对路径
     * @return
     * @throws ServiceException
     */
    byte[] downloadFile(String relativePath) throws ServiceException;

    /**
     * Download the specific file by relative path.
     * @param relativePath
     * @return
     * @throws ServiceException
     */
    FileInputStream downloadFileByStream(String relativePath) throws ServiceException;

    /**
     *  Upload the file in the particular path then return the file information, including file path, file id
     * @param relativePath
     * @param file
     * @return
     */
    TmFilePO insertFile(String relativePath, MultipartFile file);

    /**
     *  Upload the file in the particular path then return the file information, including file path, file id
     * @param relativePath
     * @param filename
     * @param base64File
     * @return
     */
    TmFilePO insertFile(String relativePath, String filename, String base64File);

}
