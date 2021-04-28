package Model.Ressource;

import java.io.Serializable;

public class Image implements Serializable {
    private byte[] fileByte;
    private String fileName;

    public void setFileByte(byte[] fileByte) {
        this.fileByte = fileByte;
    }

    public byte[] getFileByte() {
        return fileByte;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
