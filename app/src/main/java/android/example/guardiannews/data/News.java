package android.example.guardiannews.data;

public class News  {


    private String NewsSectionName;
    private String NewsPublishedDate;
    private String NewsTitle;
    private String NewsThumbnail;
    private String NewsUrl;

    public News( String newsSectionName, String newsPublishedDate, String newsTitle, String newsThumbnail, String newsUrl) {

        NewsSectionName = newsSectionName;
        NewsPublishedDate = newsPublishedDate;
        NewsTitle = newsTitle;
        NewsThumbnail = newsThumbnail;
        NewsUrl = newsUrl;
    }




    public String getNewsSectionName() {
        return NewsSectionName;
    }

    public String getNewsPublishedDate() {
        return NewsPublishedDate;
    }

    public String getNewsTitle() {
        return NewsTitle;
    }

    public String getNewsThumbnail() {
        return NewsThumbnail;
    }

    public String getNewsUrl() {
        return NewsUrl;
    }
}
