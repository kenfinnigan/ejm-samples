package ejm.resteasyclient;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author Ken Finnigan
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Category {

    protected Integer id;

    protected String name;

    protected String header;

    protected Boolean visible;

    protected String imagePath;

    protected Category parent;

    private Collection<Category> children = new HashSet<>();

    protected LocalDateTime created = LocalDateTime.now();

    protected LocalDateTime updated;

    protected Integer version;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Category getParent() {
        return parent;
    }

    public Collection<Category> getChildren() {
        return children;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public Integer getVersion() {
        return version;
    }
}
