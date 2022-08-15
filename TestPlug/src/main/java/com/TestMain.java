package com;

import java.net.URL;
import java.net.URLClassLoader;

public class TestMain {
    public static void main(String[] args) {
        String path ="application.yml";
        System.out.println(path);
        URL url =Thread.currentThread().getContextClassLoader().getResource(path);
        URLClassLoader urlClassLoader = (URLClassLoader) Thread.currentThread().getContextClassLoader();
        System.out.println(urlClassLoader.findResource(path));
        System.out.println(Thread.currentThread().getContextClassLoader().getParent());
        System.out.println(url);
        System.out.print(url.toString());
    }
}
