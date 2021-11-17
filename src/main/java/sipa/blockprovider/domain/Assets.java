package sipa.blockprovider.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Internal use only, do not interact.
 */
public class Assets {

    private final List<String> sharedJs = new ArrayList<>();
    private final List<String> sharedCss = new ArrayList<>();
    private final List<String> js = new ArrayList<>();
    private final List<String> css = new ArrayList<>();

    public List<String> getSharedJs() {
        return this.sharedJs;
    }

    public List<String> getSharedCss() {
        return this.sharedCss;
    }

    public List<String> getJs() {
        return this.js;
    }

    public List<String> getCss() {
        return this.css;
    }
}
