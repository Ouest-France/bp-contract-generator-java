package sipa.blockprovider.examples;

import org.junit.jupiter.api.Test;
import sipa.blockprovider.BlockProviderGenerator;

import static sipa.blockprovider.ParameterBuilder.EndpointParameterBuilder.In.QUERY;
import static sipa.blockprovider.ParameterBuilder.EndpointParameterBuilder.Type.STRING;

public class BlockTypeWithEndpointTest extends AbstractTest {

    @Test
    void shouldSerializeOneBlockType() {
        // a simple block type that output the input text as bolded text
        // it has 2 templates to render the text in different manner

        output(
                BlockProviderGenerator.create()
                        // the block type
                        .addType("bold").withVersion(1, 0, 0)

                        // set documentation
                        .withDocumentation("http://ww.google.fr")

                        // declare the endpoint url
                        .withEndpoint("https://localhost/block-configurations", "GET", "pure")

                        // the input text
                        .addParameter("text")
                        .configurableAsString()
                        .required()
                        .withTitle("Text")
                        .withDescription("Text to display as bold")

                        // send this parameter to the endpoint
                        .inEndpoint(QUERY, STRING)
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

}
