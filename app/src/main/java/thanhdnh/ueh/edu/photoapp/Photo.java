package thanhdnh.ueh.edu.photoapp;

public class Photo {
    private int id;
    private String source_photo;
    private String title_photo;
    private String description_photo;

    public Photo(int id, String source_photo, String caption_photo, String description_photo) {
        this.id = id;
        this.source_photo = source_photo;
        this.title_photo = caption_photo;
        this.setDescription_photo(description_photo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource_photo() {
        return source_photo;
    }

    public void setSource_photo(String source_photo) {
        this.source_photo = source_photo;
    }

    public String getTitle_photo() {
        return title_photo;
    }

    public void setTitle_photo(String title_photo) {
        this.title_photo = title_photo;
    }

    public String getDescription_photo() {
        return description_photo;
    }

    public void setDescription_photo(String description_photo) {
        this.description_photo = description_photo;
    }
}
