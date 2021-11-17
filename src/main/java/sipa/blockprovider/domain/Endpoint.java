package sipa.blockprovider.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Internal use only, do not interact.
 */
public class Endpoint {

    private String url;
    private String method;
    private boolean pure;
    private final List<String> consumes = List.of();
    private final List<Parameter> parameters = new ArrayList<>();
    private final UI ui = new UI();


    public String getUrl() {
        return this.url;
    }

    public String getMethod() {
        return this.method;
    }

    public boolean isPure() {
        return this.pure;
    }

    public List<String> getConsumes() {
        return this.consumes;
    }

    public List<Parameter> getParameters() {
        return this.parameters;
    }

    public UI getUi() {
        return this.ui;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public void setMethod(final String method) {
        this.method = method;
    }

    public void setPure(final boolean pure) {
        this.pure = pure;
    }

    public static class Parameter {
        private final String name;
        private final String in; // header, cookie, path, query, body
        private final boolean required;
        private final Schema schema;

        public Parameter(final String name, final String in, final boolean required, final Schema schema) {
            this.name = name;
            this.in = in;
            this.required = required;
            this.schema = schema;
        }

        public String getName() {
            return this.name;
        }

        public String getIn() {
            return this.in;
        }

        public boolean isRequired() {
            return this.required;
        }

        public Schema getSchema() {
            return this.schema;
        }
    }

    public static class Schema {
        private final String type;
        private final String format;

        public Schema(final String type, final String format) {
            this.type = type;
            this.format = format;
        }

        public String getType() {
            return this.type;
        }

        public String getFormat() {
            return this.format;
        }
    }


}
