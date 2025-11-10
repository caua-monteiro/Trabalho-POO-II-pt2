package com.appacademia.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CloudinaryStorageService {

    private Cloudinary cloudnary;

    public CloudinaryStorageService(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", LeitorPDF.getProperty("cloudinary.cloud-name"));
        config.put("api_key", LeitorPDF.getProperty("cloudinary.api-key"));
        config.put("api_secret", LeitorPDF.getProperty("cloudinary.api-secret"));

        this.cloudnary = new Cloudinary(config);

        System.out.println(config);
    }

    public String uploadFiles(File file) throws IOException{
        String uniqueName = "treinos_pdf/" + UUID.randomUUID().toString();

        Map<?,?> uploadResult = cloudnary.uploader().upload(file, ObjectUtils.asMap(
                "resource_type", "raw",
                "public_id", uniqueName,
                "type", "upload"
        ));

        return (String) uploadResult.get("secure_url");
    }
}
