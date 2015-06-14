package com.jetbrains.jbmake.processing.renaming;

/**
 * @author daywalker
 * @since 14/06/15.
 */
public class FileDescription {
    private final String fileName;
    private final String fileNameWoExtension;
    private final String fileExtension;

    public FileDescription(String fileName) {
        this.fileName = fileName;
        final int extensionIndex = fileName.lastIndexOf('.');
        if (extensionIndex >= 0) {
            this.fileNameWoExtension = fileName.substring(0, extensionIndex);
            this.fileExtension = fileName.substring(extensionIndex + 1);
        } else {
            this.fileNameWoExtension = fileName;
            this.fileExtension = "";
        }
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileNameWoExtension() {
        return fileNameWoExtension;
    }

    public String getFileExtension() {
        return fileExtension;
    }
}
