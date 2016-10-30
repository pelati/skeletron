package com.jami.gcs.storage.service.impl;

import com.google.appengine.tools.cloudstorage.*;
import com.jami.gcs.storage.service.StorageService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;

/**
 * @author igor
 */
public class StorageServiceImpl implements StorageService {
    private GcsService gcsService = GcsServiceFactory.createGcsService();

    @Override
    public String uploadObject(String bucketName, String objectName, byte[] data) {
        GcsFileOptions instance = GcsFileOptions.getDefaultInstance();
        GcsFilename fileName = new GcsFilename(bucketName, objectName);
        GcsOutputChannel outputChannel;
        try {
            outputChannel = gcsService.createOrReplace(fileName, instance);
            OutputStream outputStream = Channels.newOutputStream(outputChannel);
            outputStream.write(data);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public InputStream downloadObject(String bucketName, String objectName) {
        GcsFilename fileName = new GcsFilename(bucketName, objectName);

        try {
            GcsInputChannel readChannel = gcsService.openReadChannel(fileName, 0);
            return Channels.newInputStream(readChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
