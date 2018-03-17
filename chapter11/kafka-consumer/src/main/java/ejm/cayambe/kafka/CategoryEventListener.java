package ejm.cayambe.kafka;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.enterprise.context.ApplicationScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.aerogear.kafka.cdi.annotation.Consumer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;

/**
 * @author Ken Finnigan
 */
@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{KAFKA_SERVICE_HOST}:#{KAFKA_SERVICE_PORT}")
public class CategoryEventListener {

    private static final String DATASOURCE = "java:/jboss/datasources/CayambeDS";

    @Consumer(topics = "category_topic", keyType = Integer.class, groupId = "cayambe-listener", offset = "earliest")
    public void handleEvent(Integer key, Category category) {
        System.out.println("Key: " + key);
        System.out.println("Value: " + category);

        if (null == category) {
            // Remove Category
            executeUpdateSQL("delete from category where category_id = " + key);
            // Remove from Category Hierarchy
            executeUpdateSQL("delete from category_category where category_id = " + key);
            executeUpdateSQL("delete from category_category where parent_id = " + key);
        } else {
            boolean update = rowExists("select * from category where category_id = " + key);
            if (update) {
                // Update Category
                executeUpdateSQL("update category set name = '" + category.getName()
                                         + "' header = '" + category.getHeader()
                                         + "' image = '" + category.getImagePath()
                                         + "' where category_id = " + key);
            } else {
                // Create Category
                executeUpdateSQL("insert into category (id,name,header,visible,image) values("
                                         + key + ",'" + category.getName() + "', '"
                                         + category.getHeader() + "', " + (category.isVisible() ? 1 : 0)
                                         + ", '" + category.getImagePath() + "')");
                executeUpdateSQL("insert into category_category (category_id, parent_id)"
                                         + " values (" + category.getId() + "," + category.getParent().getId() + ")");
            }
        }
    }

    private void executeUpdateSQL(String sql) {
        Statement statement = null;
        Connection conn = null;

        try {
            conn = getDatasource().getConnection();
            statement = conn.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != statement) {
                    statement.close();
                }
                if (null != conn) {
                    conn.close();
                }
            } catch (SQLException sqle) {
                // Ignored
            }
        }
    }

    private boolean rowExists(String sql) {
        Statement statement = null;
        Connection conn = null;
        ResultSet results = null;

        try {
            conn = getDatasource().getConnection();
            statement = conn.createStatement();
            results = statement.executeQuery(sql);
            return results.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != results) {
                    results.close();
                }
                if (null != statement) {
                    statement.close();
                }
                if (null != conn) {
                    conn.close();
                }
            } catch (SQLException sqle) {
                // Ignored
            }
        }
        return false;
    }

    private DataSource getDatasource() {
        if (null == dataSource) {
            try {
                dataSource = (DataSource) new InitialContext().lookup(DATASOURCE);
            } catch (NamingException ne) {
                ne.printStackTrace();
            }
        }
        return dataSource;
    }

    private DataSource dataSource = null;
}
