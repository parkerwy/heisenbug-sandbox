package lab.heisenbug.sandbox.jsoup;

/**
 * Created with IntelliJ IDEA.
 * User: parker
 * Date: 5/3/14
 * Time: 6:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class Fix {

    private String publishDate;

    private String id;

    private String summary;

    private String aparLink;

    private String readmeLink;

    private String packageLink;

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAparLink() {
        return aparLink;
    }

    public void setAparLink(String aparLink) {
        this.aparLink = aparLink;
    }

    public String getReadmeLink() {
        return readmeLink;
    }

    public void setReadmeLink(String readmeLink) {
        this.readmeLink = readmeLink;
    }

    public String getPackageLink() {
        return packageLink;
    }

    public void setPackageLink(String packageLink) {
        this.packageLink = packageLink;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getPublishDate());
        builder.append("\t");
        builder.append(getId());
        builder.append("\t");
        builder.append(getSummary());
        return builder.toString();
    }
}
