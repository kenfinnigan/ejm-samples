package ejm.shopping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * @author Ken Finnigan
 */
@Path("/")
public class CartController {
    private static List<CartItem> items = new ArrayList<>();

    static {
        items.add(new CartItem("sunscreen", 3));
        items.add(new CartItem("towel", 1));
        items.add(new CartItem("hat", 5));
        items.add(new CartItem("umbrella", 1));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CartItem> all() throws Exception {
        return items;
    }

    @GET
    @Path("/add")
    public String addOrUpdateItem(@QueryParam("item") String itemName, @QueryParam("qty") Integer qty) throws Exception {
        Optional<CartItem> item = items.stream()
                .filter(i -> i.getItemName().equalsIgnoreCase(itemName))
                .findFirst();

        if (item.isPresent()) {
            Integer total = item.get().increaseQuantity(qty).getItemQuantity();
            return "Updated quantity of '" + itemName + "' to " + total;
        }

        items.add(new CartItem(itemName, qty));
        return "Added '" + itemName + "' to shopping cart";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{itemName}")
    public CartItem getItem(@PathParam("itemName") String itemName) throws Exception {
        return items.stream()
                .filter(i -> i.getItemName().equalsIgnoreCase(itemName))
                .findFirst()
                .get();
    }
}
