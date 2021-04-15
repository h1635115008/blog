package cn.aftertomorrow.common.util;

public class ImageResponse {
    private String success;
    private String file_path;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    @Override
    public String toString() {
        return "ImageResponse{" +
                "success='" + success + '\'' +
                ", file_path='" + file_path + '\'' +
                '}';
    }
}
