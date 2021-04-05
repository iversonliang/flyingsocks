package com.lzf.flyingsocks.url;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

class ClasspathURLConnection extends URLConnection {

    private final ClassLoader classLoader;
    private final String location;
    private final String pacakgeResourcePrefix = "/BOOT-INF/classes/";

    ClasspathURLConnection(URL url, ClassLoader classLoader) {
        super(url);
        this.classLoader = Objects.requireNonNull(classLoader);
        location = url.getAuthority() + url.getPath();
    }

    @Override
    public void connect() throws IOException {
//        if(classLoader.getResource(location) == null)
        if(getInputStream() == null)
            throw new IOException("Classpath resource [" + location + "] not found");
    }

    @Override
    public InputStream getInputStream() throws IOException {
//        String path = pacakgeResourcePrefix + location;
//        InputStream inputStream = classLoader.getResourceAsStream("BOOT-INF/classes/" + location);
//
//        inputStream = this.getClass().getResourceAsStream(path);
//        String path2 = "";
        InputStream inputStream = null;
        URL url = this.getClass().getResource("");
        System.out.println("url:"+url.getPath());
        if (!url.getPath().contains("!")) {
            url = classLoader.getResource("BOOT-INF/classes/" + location);
            inputStream = new FileInputStream(url.getPath());
        } else {
            inputStream = classLoader.getResourceAsStream("BOOT-INF/classes/" + location);
            if (inputStream == null) {
                inputStream = classLoader.getResourceAsStream(location);
                System.out.println(location + ": load from outside folder");
            }
            if (inputStream == null) {
                inputStream = classLoader.getResourceAsStream("BOOT-INF/classes/encrypt/" + location);
                System.out.println(location + ": load from jar, /BOOT-INF/classes/encrypt/");
            }
        }

        if(inputStream == null) {
            System.out.println("------------------null" );
            throw new IOException("Classpath resource [" + location + "] not found");
        }
        return inputStream;
    }
}