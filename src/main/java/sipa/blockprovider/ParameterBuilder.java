package sipa.blockprovider;

import sipa.blockprovider.domain.Endpoint;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The class to generate parameter.
 */
public class ParameterBuilder {

    private final String name;
    private String inSection;
    private final BlockTypeBuilder owner;
    private EndpointParameterBuilder endpointParameterBuilder;
    private boolean required;
    private String title;
    private String description;
    private String uiType;
    private final Map<String, Object> additionalUIProperties = new LinkedHashMap<>();
    private String expressionModeDisabled;

    ParameterBuilder(final BlockTypeBuilder owner, final String name) {
        this.owner = owner;
        this.name = name;
    }

    /**
     * Finalize the parameter.
     *
     * @return the block type builder
     */
    public BlockTypeBuilder registerParameter() {
        if (this.endpointParameterBuilder != null) {
            if (this.owner.blockConfiguration.getEndpoint().getUrl() == null) {
                throw new IllegalStateException("you must define the endpoint first");
            }
            this.owner.blockConfiguration.getEndpoint().getParameters().add(
                    new Endpoint.Parameter(
                            this.name,
                            this.endpointParameterBuilder.in.value,
                            this.required,
                            new Endpoint.Schema(this.endpointParameterBuilder.type.value, "string")
                    )
            );
        }

        final String sectionName = this.inSection != null ? this.inSection : "Configuration";

        this.owner.blockConfiguration.getEndpoint().getUi().addProperty(sectionName, Map.of(this.name, toProperty()));

        if (this.expressionModeDisabled != null) {
            this.owner.blockConfiguration.getEndpoint().getUi().disableExpressionMode(sectionName, this.name, this.expressionModeDisabled);
        }

        if (this.required) {
            this.owner.blockConfiguration.getEndpoint().getUi().addRequired(sectionName, this.name);
        }

        this.owner.blockConfiguration.getEndpoint().getUi().order(sectionName, this.name);


        return this.owner;
    }

    private Map<String, Object> toProperty() {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        result.put("title", this.title != null ? this.title : this.name);
        result.put("type", this.uiType);
        if (this.description != null) {
            result.put("description", this.description);
        }
        result.putAll(this.additionalUIProperties);
        return result;
    }

    /**
     * Set the title that will be displayed in the backoffice.
     *
     * @param title the title, if null or not set, the name will be used
     * @return the parameter builder
     */
    public ParameterBuilder withTitle(final String title) {
        this.title = title;
        return this;
    }

    /**
     * Set the section that holds this parameter.
     *
     * @param section the section title
     * @return the parameter builder
     */
    public ParameterBuilder inSection(final String section) {
        this.inSection = section;
        return this;
    }

    /**
     * Mark this parameter as required.
     *
     * @return the parameter builder
     */
    public ParameterBuilder required() {
        this.required = true;
        return this;
    }

    /**
     * Specify the way this parameter must be passed to the endpoint.
     *
     * @param in   the location of the parameter
     * @param type the parameter type (used for serialization)
     * @return the parameter builder
     */
    public ParameterBuilder inEndpoint(final EndpointParameterBuilder.In in, final EndpointParameterBuilder.Type type) {
        this.endpointParameterBuilder = new EndpointParameterBuilder(in, type);
        return this;
    }

    /**
     * Set the description that will be displayed in the backoffice.
     *
     * @param description the description
     * @return the parameter builder
     */
    public ParameterBuilder withDescription(final String description) {
        this.description = description;
        return this;
    }

    /**
     * Specify this parameter is configured as an enum in the backoffice.
     *
     * @param values the list of enum values
     * @return the parameter builder
     */
    public ParameterBuilder configurableAsEnum(final List<String> values) {
        this.uiType = "string";
        this.additionalUIProperties.put("enum", values);
        return this;
    }

    /**
     * Specify this parameter is configured using a dedicated widget.
     *
     * @param widget        the widget name
     * @param version       the widget version
     * @param configuration the additional widget configuration
     * @return the parameter builder
     */
    public ParameterBuilder configurableAsWidget(final String widget, final String version, final Object configuration) {
        this.uiType = "string";
        this.additionalUIProperties.put("x-ui-configuration",
                Map.of("widget",
                        Map.of(
                                "name", widget,
                                "version", version,
                                "configuration", configuration
                        )
                )
        );
        return this;
    }

    /**
     * Specify this parameter is configured as a boolean in the backoffice.
     *
     * @param defaultValue the default value of this parameter
     * @return the parameter builder
     */
    public ParameterBuilder configurableAsBoolean(final boolean defaultValue) {
        this.uiType = "boolean";
        this.additionalUIProperties.put("default", defaultValue);
        return this;
    }

    /**
     * Specify this parameter is configured as a string in the backoffice.
     *
     * @return the parameter builder
     */
    public ParameterBuilder configurableAsString() {
        return configurableAsString(null);
    }

    /**
     * Specify this parameter is configured as a string in the backoffice.
     *
     * @param defaultValue the default value
     * @return the parameter builder
     */
    public ParameterBuilder configurableAsString(final String defaultValue) {
        this.uiType = "string";
        if (defaultValue != null) {
            this.additionalUIProperties.put("default", defaultValue);
        }
        return this;
    }


    /**
     * Mark this parameter can not be configured through expression (aka SPEL), only hardcoded values are allowed.
     *
     * @param mode when the expression mode is disabled
     * @return the parameter builder
     */
    public ParameterBuilder disableExpressionMode(final ExpressionModeDisabled mode) {
        this.expressionModeDisabled = mode.value;
        return this;
    }

    /**
     * Specify this parameter is configured as a number (with decimals) in the backoffice.
     *
     * @return the parameter builder
     */
    public ParameterBuilder configurableAsNumber() {
        this.uiType = "number";
        return this;
    }

    /**
     * Specify this parameter is configured as an integer in the backoffice.
     *
     * @return the parameter builder
     */
    public ParameterBuilder configurableAsInteger() {
        this.uiType = "integer";
        return this;
    }

    /**
     * A flag to disable the expression mode for a parameter.
     */
    public enum ExpressionModeDisabled {
        /**
         * In any case, the expression mode is disabled.
         */
        ALWAYS("always"),
        /**
         * The expression mode is disabled only when the block has been set as the leading block.
         */
        LEADING("leading");

        private final String value;

        ExpressionModeDisabled(final String value) {
            this.value = value;
        }

        /**
         * Get the value for serialization.
         *
         * @return see description
         */
        public String getValue() {
            return this.value;
        }
    }

    /**
     * The enpoint parameter information.
     */
    public static class EndpointParameterBuilder {
        // inherit private String name;
        private final In in;
        private final Type type;

        EndpointParameterBuilder(final In in, final Type type) {
            this.in = in;
            this.type = type;
        }

        /**
         * How the parameter is transmitted to the endpoint.
         */
        public enum In {
            /**
             * Passed in a header with name <code>parameter-name</code>, and the value serialized as a string.
             */
            HEADER("header"),
            /**
             * Passed in a cookie with name <code>parameter-name</code>, and the value serialized as a string.
             */
            COOKIE("cookie"),
            /**
             * Passed in the path.
             * The endpoint url must have a <code>{parameter-name}</code> fragment (https://service/path/{parameter-name}/fetch).
             * This fragment will be replaced by the parameter value serialized as a string.
             */
            PATH("path"),
            /**
             * Passed as a query parameter with name <code>parameter-name</code>, and the value serialized as a string.
             */
            QUERY("query"),
            /**
             * Passed in the body of the request.
             * The body is composed of a json object with the <code>parameter-name</code> as top-level property, and the associated value.
             */
            BODY("body");

            private final String value;

            In(final String value) {
                this.value = value;
            }

            /**
             * Get the value for serialization.
             *
             * @return see description
             */
            public String getValue() {
                return this.value;
            }
        }

        /**
         * The type of parameter.
         */
        public enum Type {
            /**
             * Serialize as an integer.
             */
            INTEGER("integer"),
            /**
             * Serialize as a number.
             */
            NUMBER("number"),
            /**
             * Serialize as a boolean.
             */
            BOOLEAN("boolean"),
            /**
             * Serialize as an object.
             */
            OBJECT("object"),
            /**
             * Serialize as a string.
             */
            STRING("string");

            private final String value;

            Type(final String value) {
                this.value = value;
            }

            /**
             * Get the value for serialization.
             *
             * @return see description
             */
            public String getValue() {
                return this.value;
            }
        }
    }
}
