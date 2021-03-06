package urjc.isi.pruebasSparkJava;

import static spark.Spark.*;

import spark.Request;
import spark.Response;

import java.net.URISyntaxException;

public class Main {
  
    public static String doWork(Request request, Response response) throws ClassNotFoundException, URISyntaxException {
	String result = new String("Hello Awesome");

	return result;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        port(getHerokuAssignedPort());

        // spark server
        //cuando en el servidor en el puerto 4567 le llames con hello, contestará
        //si no se llama con /hello no llama a doWork (el main)
        get("/hello", Main::doWork);

    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
