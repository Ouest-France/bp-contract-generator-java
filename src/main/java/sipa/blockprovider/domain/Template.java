package sipa.blockprovider.domain;

import sipa.blockprovider.TemplateBuilder;

/**
 * Internal use only, do not interact.
 */
public class Template {

    private String name;
    private String position = TemplateBuilder.Position.PLACEHOLDER.getValue();
    private String engine = "mustache";
    private String targetFormat = "html";
    private String source = "";
    private Assets assets;

    public String getName() {
        return this.name;
    }

    public String getPosition() {
        return this.position;
    }

    public String getEngine() {
        return this.engine;
    }

    public String getTargetFormat() {
        return this.targetFormat;
    }

    public String getSource() {
        return this.source;
    }

    public Assets getAssets() {
        return this.assets;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setPosition(final String position) {
        this.position = position;
    }

    public void setEngine(final String engine) {
        this.engine = engine;
    }

    public void setTargetFormat(final String targetFormat) {
        this.targetFormat = targetFormat;
    }

    public void setSource(final String source) {
        this.source = source;
    }

    public void setAssets(final Assets assets) {
        this.assets = assets;
    }
}
