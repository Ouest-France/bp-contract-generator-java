package sipa.blockprovider.domain;

import java.util.*;

/**
 * Internal use only, do not interact.
 */
public class UI {

    public static final String SECTION_TITLE = "title";
    public static final String SECTION_PROPERTIES = "properties";
    public static final String SECTION_X_PROPERTIES_ORDER = "x-properties-order";
    public static final String SECTION_REQUIRED = "required";
    public static final String SECTION_X_EXPRESSION_MODE_DISABLED = "x-expression-mode-disabled";

    List<Map<String, Object>> sections = new ArrayList<>();

    public void addRequired(final String title, final String name) {
        final Map<String, Object> section = this.getByTitle(title);
        section.putIfAbsent(SECTION_REQUIRED, new ArrayList<>());
        ((List<String>) section.get(SECTION_REQUIRED)).add(name);
    }

    public void order(final String title, final String name) {
        final Map<String, Object> section = this.getByTitle(title);
        section.putIfAbsent(SECTION_X_PROPERTIES_ORDER, new ArrayList<>());
        ((List<String>) section.get(SECTION_X_PROPERTIES_ORDER)).add(name);
    }

    public void addProperty(final String title, final Map<String, Object> property) {
        final Map<String, Object> section = this.getByTitle(title);
        section.putIfAbsent(SECTION_PROPERTIES, new LinkedHashMap<>());
        ((Map<String, Object>) section.get(SECTION_PROPERTIES)).putAll(property);
    }

    public void disableExpressionMode(final String title, final String name, final String when) {
        final Map<String, Object> section = this.getByTitle(title);
        section.putIfAbsent(SECTION_X_EXPRESSION_MODE_DISABLED, new ArrayList<>());
        ((List<Map<String, Object>>) section.get(SECTION_X_EXPRESSION_MODE_DISABLED)).add(Map.of("property", name, "when", when));

    }

    public Map<String, Object> getByTitle(final String title) {
        final Optional<Map<String, Object>> section = this.sections.stream().filter(s -> s.get(SECTION_TITLE).equals(title)).findFirst();
        if (section.isPresent()) {
            return section.get();
        }
        final Map<String, Object> newSection = new LinkedHashMap<>();
        newSection.put(SECTION_TITLE, title);
        this.sections.add(newSection);
        return newSection;
    }

    public List<Map<String, Object>> getSections() {
        return this.sections;
    }
}
