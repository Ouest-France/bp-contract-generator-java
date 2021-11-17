package sipa.blockprovider.domain;

import java.util.List;

/**
 * Internal use only, do not interact.
 */
public class BlockType {

    private String name;
    private String type = "Display";

    private final List<BlockConfiguration> configurations;

    public BlockType(final BlockConfiguration blockConfiguration) {
        this.configurations = List.of(blockConfiguration);
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public List<BlockConfiguration> getConfigurations() {
        return this.configurations;
    }
}
