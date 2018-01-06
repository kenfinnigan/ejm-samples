package ejm.adminclient;

import java.time.LocalDateTime;

/**
 * @author Ken Finnigan
 */
public class TestCategoryObject extends Category {

    public TestCategoryObject(Integer id, LocalDateTime created, Integer version) {
        this.id = id;
        this.created = created;
        this.version = version;
    }

}
