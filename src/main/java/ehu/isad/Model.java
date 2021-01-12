package ehu.isad;

public class Model {

    private String url;
    private String md5;
    private String version;

    public Model(String url, String md5, String version) {
        this.url = url;
        this.md5 = md5;
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public String getMd5() {
        return md5;
    }

    public String getVersion() {
        return version;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Model{" +
                "url='" + url + '\'' +
                ", md5='" + md5 + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
