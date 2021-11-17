package sipa.blockprovider;

import sipa.blockprovider.domain.BlockConfiguration;
import sipa.blockprovider.domain.BlockType;

import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * The class to generate block type.
 */
public class BlockTypeBuilder {

    final BlockType blockType;
    private final BlockProviderGenerator owner;
    BlockConfiguration blockConfiguration;

    BlockTypeBuilder(final BlockProviderGenerator owner, final String name) {
        this.owner = owner;
        this.blockType = new BlockType(this.blockConfiguration = new BlockConfiguration());
        this.blockType.setName(name);
    }

    /**
     * Finalize the block type.
     *
     * @return the block provider builder
     */
    public BlockProviderGenerator registerType() {
        if (this.blockConfiguration.getTemplates().size() == 0) {
            // create default empty template
            addTemplate("default").registerTemplate();
        }
        return this.owner;
    }

    /**
     * This gives you a way to add / remove / replace custom properties to the section with the specified title.
     *
     * @param title    the title of the section to customize
     * @param consumer the consumer method that modifies the properties
     * @return the block type builder
     */
    public BlockTypeBuilder customizeSection(final String title, final Consumer<Map<String, Object>> consumer) {
        consumer.accept(this.blockConfiguration.getEndpoint().getUi().getByTitle(title));
        return this;
    }

    /**
     * Add a template with the specified name to the block type.
     *
     * @param name the template name
     * @return the block type builder
     */
    public TemplateBuilder addTemplate(final String name) {
        return new TemplateBuilder(this, name);
    }

    /**
     * Set the version of the block type.
     *
     * @param major major version
     * @param minor minor version
     * @param patch patch number
     * @return the block type builder
     */
    public BlockTypeBuilder withVersion(final int major, final int minor, final int patch) {
        this.blockConfiguration.setVersion(major + "." + minor + "." + patch);
        return this;
    }

    /**
     * Specify the endpoint settings associated with the block type.
     *
     * @param url         the url of the endpoint. This endpoint *MUST* be accessible by the BMS
     * @param method      the HTTP method to use when the BMS requests it (GET|POST|...)
     * @param cachePolicy the cache policy to associate with the endpoint (pure|impure ~=~ 1h|1min)
     * @return the block type builder
     */
    public BlockTypeBuilder withEndpoint(final String url, final String method, final String cachePolicy) {
        this.blockConfiguration.getEndpoint().setUrl(url);
        this.blockConfiguration.getEndpoint().setMethod(method);
        this.blockConfiguration.getEndpoint().setPure(Objects.equals(cachePolicy, "pure"));
        return this;
    }

    /**
     * Add a parameter to the block type.
     *
     * @param name the parameter identifier
     * @return the block type builder
     */
    public ParameterBuilder addParameter(final String name) {
        return new ParameterBuilder(this, name);
    }

}
