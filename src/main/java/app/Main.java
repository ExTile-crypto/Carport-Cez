package app;

import app.configuration.ThymeleafConfig;
import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;


public class Main {

    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/%s?currentSchema=public";
    private static final String DB = "carport";
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance(USER, PASSWORD, URL, DB);

    public static void main(String[] args) {
        Javalin.create(config -> {

            config.staticFiles.add("/public");
            config.fileRenderer(new JavalinThymeleaf(ThymeleafConfig.templateEngine()));

            // Static page previews (replace with controller routes as features land)
            config.routes.get("/", ctx -> ctx.render("index"));
            config.routes.get("/login", ctx -> ctx.render("login"));
            config.routes.get("/request", ctx -> ctx.render("request"));
            config.routes.get("/orders", ctx -> ctx.render("orders"));
            config.routes.get("/orders/{id}", ctx -> ctx.render("order"));
            config.routes.get("/customer/orders", ctx -> ctx.render("customer-order"));
            config.routes.get("/customer/orders/{id}", ctx -> ctx.render("customer-order"));

            // Register controllers here, e.g.:
            // UserController userController = new UserController(connectionPool);
            // userController.setRoutes(config);

        }).start(7070);
    }
}
