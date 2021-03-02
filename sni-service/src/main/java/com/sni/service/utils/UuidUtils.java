package com.sni.service.utils;

import org.springframework.util.StringUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UuidUtils {

    public static String generateUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("/Users/gaoyang/Documents/拆分/consumer_mediation.txt");
            int ch = 0;
            StringBuilder sb = new StringBuilder();
            while((ch = fileReader.read()) != -1) {
                sb.append((char) ch);
            }
            String txt = sb.toString().replace("%3A", ":").replace("%2F", "/").replace("%3D", "=")
                      .replace("%26", "&").replace("%2C", ",").replace("%3F", "?");
            System.out.println(txt);

            Set<String> consumers = new HashSet<>();
            String[] arr = txt.split("consumer://");
            Arrays.stream(arr).forEach(s -> {
                if (s.length() >= 10) {
                    String consumer = "";
                    s = s.substring(0, s.indexOf("&category"));
                    consumer += s.substring(s.indexOf("application=") + 12);
                    consumer += ":";
                    consumer += s.substring(0, s.indexOf("/"));
                    consumers.add(consumer);
                }
            });
            consumers.stream().forEach(System.out::println);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
