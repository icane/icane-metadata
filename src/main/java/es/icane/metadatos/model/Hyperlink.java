package es.icane.metadatos.model;

import java.io.Serializable;

/**
 *
 * @author Alejandro Villar <avillar@ticnor.es>
 */
public class Hyperlink implements Serializable {
    private final String title, url;

    public Hyperlink(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Hyperlink{" + "title=" + title + ", url=" + url + '}';
    }
}
