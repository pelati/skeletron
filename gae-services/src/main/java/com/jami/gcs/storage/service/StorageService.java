package com.jami.gcs.storage.service;

import java.io.InputStream;

/**
 * @author igor
 */
public interface StorageService {
    String uploadObject(String bucketName, String objectName, byte[] data);
    InputStream downloadObject(String bucketName, String objectName);
}
