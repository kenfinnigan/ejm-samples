package ejm.adminclient;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) &&
                Objects.equals(name, category.name) &&
                Objects.equals(header, category.header) &&
                Objects.equals(visible, category.visible) &&
                Objects.equals(imagePath, category.imagePath) &&
                (parent == null ? category.parent == null : Objects.equals(parent.getId(), category.parent.getId())) &&
                Objects.equals(created, category.created) &&
                Objects.equals(updated, category.updated) &&
                Objects.equals(version, category.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, header, visible, imagePath, parent == null ? null : parent.getId(), created, updated, version);
    }
}
