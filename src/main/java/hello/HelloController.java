package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.SpanAccessor;
import org.springframework.cloud.sleuth.Span;

@RestController
public class HelloController {
    private static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private SpanAccessor accessor;

    @RequestMapping("/")
    public String index() throws Exception {
        Span currentSpan = this.accessor.getCurrentSpan();
        List<Long> parents = currentSpan.getParents();
        List<String> parentsHex = new ArrayList<String>();

        for (int i = 0; i < parents.size(); i++) {
            String hex = String.format("%016x", parents.get(i));
            parentsHex.add(hex);
        }
        String traceID = String.format("%016x", currentSpan.getTraceId());
        String spanID = String.format("%016x", currentSpan.getSpanId());

        logger.info("handling request");
        return "current span: [Trace: " + traceID + ", Span: "+ spanID +", exportable:" + currentSpan.isExportable() +"]\n parents: " + parentsHex;
    }
}
