package com.example.demo.service.impl;

import io.minio.*;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


import io.minio.MinioClient;
import io.minio.errors.*;


@Service
public class MinioService {

    private final MinioClient minioClient;

    @Value("${minio.bucketName}")
    private String bucketName;

    @Value("${minio.endpoint}")
    private String endpoint;
    @Autowired
    public MinioService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }


    public String uploadFile(String folderName, MultipartFile file) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, ErrorResponseException, InternalException, InvalidResponseException, ServerException, XmlParserException {
        // 检查存储桶是否存在，如果不存在则创建
        if (!bucketExists(bucketName)) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
        // 上传文件到MinIO
        try (InputStream inputStream = file.getInputStream()) {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(folderName + "/" + file.getOriginalFilename())
                    .stream(inputStream, file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build());
        }
        // 返回文件的URL
        return endpoint + "/" + bucketName + "/" + folderName + "/" + file.getOriginalFilename();
    }

    public String getFileUrl(String folderName, String fileName) {
        // 拼接文件的URL
        return endpoint + "/" + bucketName + "/" + folderName + "/" + fileName;
    }

    private boolean bucketExists(String bucketName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, ErrorResponseException, InternalException, InvalidResponseException, ServerException, InsufficientDataException, XmlParserException {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }
}
