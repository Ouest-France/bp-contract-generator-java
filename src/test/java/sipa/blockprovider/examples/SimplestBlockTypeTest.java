package sipa.blockprovider.examples;

import org.junit.jupiter.api.Test;
import sipa.blockprovider.BlockProviderGenerator;

public class SimplestBlockTypeTest extends AbstractTest {

    @Test
    void shouldSerialize() {
        // the simplest block type ever, no parameter, with default template that does not output anything
        output(
                BlockProviderGenerator.create()
                        .addType("simplest").withVersion(1, 0, 0)
                        .registerType()
                        .generate()
        );
    }
}
