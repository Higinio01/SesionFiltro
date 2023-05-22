package org.example;

import io.javalin.Javalin;

public class Main {

    public static void main(String[] args) {
        var app = Javalin.create().start(4000);

        app.before(ctx -> {
            boolean sessionExists = ctx.sessionAttribute("nombre") != null;
            String path = ctx.path();

            if (!path.equals("/formulario.html") && !path.equals("/login") && !path.equals("/profile") && !sessionExists) {
                ctx.redirect("/formulario.html");
            } else if (path.equals("/formulario.html") && sessionExists) {
                ctx.redirect("/");
            }
        });

        app.post("/login", ctx -> {
            String usuario = ctx.formParam("nombre");
            String password = ctx.formParam("password");

            if (usuario == null || password == null || usuario.isEmpty() || password.isEmpty()) {
                ctx.redirect("/formulario.html");
                return;
            }

            ctx.sessionAttribute("nombre", usuario);
            ctx.redirect("/");
        });

        app.get("/", ctx -> {
            String username = ctx.sessionAttribute("nombre");
            if (username != null) {
                ctx.html("<h3> Bienvenido " + username + " </h3>");
            } else {
                ctx.redirect("/formulario.html");
            }
        });
    }
}
