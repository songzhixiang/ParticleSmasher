package com.andy.library.compress;

import java.io.Serializable;

/**
 * @author andysong
 * @data 2019-06-17
 * @discription xxx
 */
public class Photo implements Serializable {

    private String originalPath;//原始路径

    private boolean compressed;

    private String compressPath;


    public Photo(String originalPath) {
        this.originalPath = originalPath;
    }

    public String getOriginalPath() {
        return originalPath;
    }

    public void setOriginalPath(String originalPath) {
        this.originalPath = originalPath;
    }

    public boolean isCompressed() {
        return compressed;
    }

    public void setCompressed(boolean compressed) {
        this.compressed = compressed;
    }

    public String getCompressPath() {
        return compressPath;
    }

    public void setCompressPath(String compressPath) {
        this.compressPath = compressPath;
    }
}
