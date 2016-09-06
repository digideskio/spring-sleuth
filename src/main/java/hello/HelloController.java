package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.*;


@RestController
public class HelloController {
    private static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/")
    public String index() throws Exception {
        logger.info("handling request");
        return getAPI();
    }

    private static String getAPI() throws Exception {
        logger.info("getting api info");
        StringBuilder result = new StringBuilder();
        URL url = new URL("https://api.superman.cf-app.com/v2/info");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        logger.info("got api info");
        return result.toString();
    }
}
