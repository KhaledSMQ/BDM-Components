package transform;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Decoder {
    private InputStream inputStream;
    private ConcurrentHashMap<String, String> mibs = new ConcurrentHashMap<>();

    private static Decoder instance;

    private static void init() throws IOException {
        instance = new Decoder();
    }

    public static Decoder getInstance() throws IOException {
        if (instance == null)
            init();

        return instance;
    }

    private Decoder() throws IOException {
        Config config = ConfigFactory.load();
        Path dir = null;
        try {
            dir = Paths.get(config.getString("mibs.path"));
        } catch (InvalidPathException e) {
            throw new IOException(e);
        }


        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path entry : stream) {
                Properties prop = new Properties();
                inputStream = new FileInputStream(entry.toAbsolutePath().toFile());
                prop.load(inputStream);
                Enumeration e = prop.keys();

                while (e.hasMoreElements()) {
                    String key = (String)e.nextElement();
                    mibs.put(prop.getProperty(key), key);
                }
                inputStream.close();
            }
        }
    }

    public String getPropKey(String oid) throws IOException {
        return mibs.get(oid);
    }
}
