package sipa.blockprovider.examples;

import org.junit.jupiter.api.Test;
import sipa.blockprovider.BlockProviderGenerator;

public class BlockTypeWithoutEndpointTest extends AbstractTest {

    @Test
    void shouldSerializeOneBlockType() {
        // a simple block type that output the input text as bolded text
        // it has 2 templates to render the text in different manner

        output(
                BlockProviderGenerator.create()
                        // the block type
                        .addType("bold").withVersion(1, 0, 0)

                        // the input text
                        .addParameter("text")
                        .configurableAsString(null)
                        .required()
                        .withTitle("Text")
                        .withDescription("Text to display as bold")
                        .registerParameter()

                        // first template to output using html tag
                        .addTemplate("html-tag")
                        .withSource("<b>{{ text }}<b>")
                        .registerTemplate()

                        // second template to output using css style
                        .addTemplate("html-css")
                        .withSource("<span style=\"font-weight: bold;\">{{ text }}</span>")
                        .registerTemplate()

                        .registerType()
                        .generate()
        );
    }

    @Test
    void shouldSerializeTwoBlockTypes() {
        // a simple block type that output the input text as bolded text
        // and another simple block type that output the input text as italic text

        output(
                BlockProviderGenerator.create()
                        // the block type
                        .addType("bold").withVersion(1, 0, 0)

                        // the input text
                        .addParameter("text")
                        .configurableAsString(null)
                        .required()
                        .withTitle("Text")
                        .withDescription("Text to display as bold")
                        .registerParameter()

                        // first template to output using html tag
                        .addTemplate("html-tag")
                        .withSource("<b>{{ text }}<b>")
                        .registerTemplate()

                        // second template to output using css style
                        .addTemplate("html-css")
                        .withSource("<span style=\"font-weight: bold;\">{{ text }}</span>")
                        .registerTemplate()

                        .registerType()

                        // the block type
                        .addType("italic").withVersion(1, 0, 0)

                        // the input text
                        .addParameter("text")
                        .configurableAsString(null)
                        .required()
                        .withTitle("Text")
                        .withDescription("Text to display as italic")
                        .registerParameter()

                        // first template to output using html tag
                        .addTemplate("html-tag")
                        .withSource("<i>{{ text }}<i>")
                        .registerTemplate()

                        // second template to output using css style
                        .addTemplate("html-css")
                        .withSource("<span style=\"font-style: italic;\">{{ text }}</span>")
                        .registerTemplate()

                        .registerType()
                        .generate()
        );
    }
}
