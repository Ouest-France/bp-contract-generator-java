package sipa.blockprovider.examples;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AbstractTest {

    void output(Object any) {
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();

        System.out.println(gson.toJson(any));
    }

}
