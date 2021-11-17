package sipa.blockprovider;

import sipa.blockprovider.domain.Assets;
import sipa.blockprovider.domain.Template;

/**
 * The class to generate template.
 */
public class TemplateBuilder {

    private final Template template;
    private final BlockTypeBuilder owner;

    TemplateBuilder(final BlockTypeBuilder owner, final String name) {
        this.owner = owner;
        this.template = new Template();
        this.template.setName(name);
    }

    /**
     * Specify the position where this template will be outputted.
     *
     * @param position the position
     * @return the template builder
     */
    public TemplateBuilder withPosition(final Position position) {
        this.template.setPosition(position.value);
        return this;
    }

    /**
     * Specify the template source code to render.
     *
     * @param source the mustache source
     * @return the template builder
     */
    public TemplateBuilder withSource(final String source) {
        this.template.setSource(source);
        return this;
    }

    /**
     * Specify if the template purpose is for regular HTML pages or for AMP.
     *
     * @param targetFormat the target format
     * @return the template builder
     */
    public TemplateBuilder withTargetFormat(final TargetFormat targetFormat) {
        this.template.setTargetFormat(targetFormat.value);
        return this;
    }

    /**
     * Add a shared javascript file url.
     *
     * @param url the url
     * @return the template builder
     */
    public TemplateBuilder withSharedJsUrl(final String url) {
        initAssets();
        this.template.getAssets().getSharedJs().add(url);
        return this;
    }

    /**
     * Add a shared css file url.
     *
     * @param url the url
     * @return the template builder
     */
    public TemplateBuilder withSharedCssUrl(final String url) {
        initAssets();
        this.template.getAssets().getSharedCss().add(url);
        return this;
    }

    private void initAssets() {
        if (this.template.getAssets() == null) {
            this.template.setAssets(new Assets());
        }
    }

    /**
     * Add a javascript file url.
     *
     * @param url the url
     * @return the template builder
     */
    public TemplateBuilder withJsUrl(final String url) {
        initAssets();
        this.template.getAssets().getJs().add(url);
        return this;
    }

    /**
     * Add a css file url.
     *
     * @param url the url
     * @return the template builder
     */
    public TemplateBuilder withCssUrl(final String url) {
        initAssets();
        this.template.getAssets().getCss().add(url);
        return this;
    }

    /**
     * Finalize the template.
     *
     * @return the block type builder
     */
    public BlockTypeBuilder registerTemplate() {
        this.owner.blockConfiguration.getTemplates().add(this.template);
        return this.owner;
    }


    /**
     * The position where the template will be outputted.
     */
    public enum Position {
        /**
         * In the middle of the body.
         */
        PLACEHOLDER("placeholder"),
        /**
         * At the top of the head tag.
         */
        HEAD_TOP("head-top"),
        /**
         * At the bottom of the head tag.
         */
        HEAD_BOTTOM("head-bottom"),
        /**
         * At the top of the body tag.
         */
        BODY_TOP("body-top"),
        /**
         * At the bottom of the body tag.
         */
        BODY_BOTTOM("body-bottom");

        private final String value;

        Position(final String value) {
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
     * The target format of the template.
     */
    public enum TargetFormat {
        /**
         * Used for regular HTML pages.
         */
        HTML("html"),
        /**
         * Used for AMP pages.
         */
        AMP("amp");

        private final String value;

        TargetFormat(final String value) {
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
