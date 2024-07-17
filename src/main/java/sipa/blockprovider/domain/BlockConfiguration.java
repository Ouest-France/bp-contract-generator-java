package sipa.blockprovider.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Internal use only, do not interact.
 */
public class BlockConfiguration {

    private String version;
    private final Documentation documentation = new Documentation();
    private final Endpoint endpoint = new Endpoint();
    private final List<Template> templates = new ArrayList<>();

    public String getVersion() {
        return this.version;
    }

    public Endpoint getEndpoint() {
        return this.endpoint;
    }

    public void setVersion(final String version) {
        this.version = version;
    }

    public List<Template> getTemplates() {
        return this.templates;
    }

    public Documentation getDocumentation() {
        return this.documentation;
    }

    public void setDocumentation(final String url) { this.documentation.url = url; }

    public class Documentation {
        private String url;
    }
}
