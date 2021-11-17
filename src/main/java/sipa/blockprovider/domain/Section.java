package sipa.blockprovider.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Internal use only, do not interact.
 */
public class Section {

    String title;
    Map<String, Object> properties = new LinkedHashMap<>();

    List<String> xPropertiesOrder = new ArrayList<>();
    List<String> required = new ArrayList<>();
    List<String> xExpressionModeDisabled = new ArrayList<>();

    public String getTitle() {
        return this.title;
    }

    public Map<String, Object> getProperties() {
        return this.properties;
    }


}
