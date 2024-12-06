package com.neplus.erp.service.impl;


import com.neplus.erp.dictionary.Fixcode;
import com.neplus.erp.mapper.TmFilePOMapper;
import com.neplus.erp.model.TmFilePO;
import com.neplus.erp.service.CommonService;
import com.neplus.framework.core.exception.ServiceException;
import com.neplus.framework.core.util.LocalFileUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.Date;

/**
 * Created by summer on 2020/1/21.
 */
@Slf4j
@Service
public class CommonServiceImpl implements CommonService
{
    @Value("${file.local.path}")
    private String basePath;

    @Resource
    private TmFilePOMapper tmFilePOMapper;

    @Override
    public String fileUploadLocal(String relativePath, MultipartFile file) throws ServiceException
    {
        try
        {
            LocalFileUtil fileUtil = new LocalFileUtil();
            String randomFileName = fileUtil.createRandomFileName(file.getOriginalFilename());
            if (basePath.endsWith("/"))
            {
                basePath = basePath.substring(0, basePath.length() - 1);
            }
            if (!relativePath.startsWith(File.separator))
            {
                relativePath = File.separator + relativePath;
            }
            fileUtil.upload(basePath + relativePath, randomFileName, file.getInputStream());
            String filePath = (relativePath.endsWith(File.separator) ? relativePath + randomFileName : relativePath + File.separator + randomFileName);
            return filePath;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("文件上传失败", e);
        }


    }

    @Override
    public byte[] downloadFile(String relativePath) throws ServiceException
    {
        try
        {
            LocalFileUtil fileUtil = new LocalFileUtil();
            if (basePath.endsWith("/"))
            {
                basePath = basePath.substring(0, basePath.length() - 1);
            }
            if (!relativePath.startsWith(File.separator))
            {
                relativePath = File.separator + relativePath;

            }
            return fileUtil.download(basePath + relativePath);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("获取文件失败", e);
        }
    }

    @Override
    public String fileUploadLocal(String relativePath, String filename, String base64File) throws ServiceException
    {
        try
        {
            LocalFileUtil fileUtil = new LocalFileUtil();
            String randomFileName = fileUtil.createRandomFileName(filename);
            String filePath = (relativePath.endsWith(File.separator) ? relativePath + randomFileName : relativePath + File.separator + randomFileName);
            Files.write(Paths.get(basePath + filePath), Base64.getDecoder().decode(base64File), StandardOpenOption.CREATE);
            return filePath;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("文件上传失败", e);
        }
    }

    @Override
    public TmFilePO insertFile(String relativePath, MultipartFile file)
    {
        try
        {
            String filePath = this.fileUploadLocal(relativePath, file);
            TmFilePO filePO = new TmFilePO();
            filePO.setFileName(file.getName());
            filePO.setFilePath(filePath);
            filePO.setIsDelete(Fixcode.IF_TYPE_NO.fixcode);
            filePO.setCreateBy(getLoginUser().getUserCode());
            filePO.setCreateDate(new Date());
            tmFilePOMapper.insertSelective(filePO);
            return filePO;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("Failed to upload file[" + file.getName() + "].", e);
        }
    }

    @Override
    public TmFilePO insertFile(String relativePath, String filename, String base64File)
    {
        String filePath = this.fileUploadLocal(relativePath, filename, base64File);
        TmFilePO filePO = new TmFilePO();
        filePO.setFileName(filename);
        filePO.setFilePath(filePath);
        filePO.setIsDelete(Fixcode.IF_TYPE_NO.fixcode);
        filePO.setCreateBy(getLoginUser().getUserCode());
        filePO.setCreateDate(new Date());
        tmFilePOMapper.insertSelective(filePO);
        return filePO;
    }
}


