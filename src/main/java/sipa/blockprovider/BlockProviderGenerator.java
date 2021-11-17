package sipa.blockprovider;

import sipa.blockprovider.domain.BlockType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The main class to generate block provider contracts.
 */
public class BlockProviderGenerator {

    /**
     * Prepare a new block provider builder.
     *
     * @return see description
     */
    public static BlockProviderGenerator create() {
        return new BlockProviderGenerator();
    }

    private BlockProviderGenerator() {

    }

    private final List<BlockTypeBuilder> types = new ArrayList<>();

    /**
     * Add a block type to the block provider.
     *
     * @param name the name of the block type
     * @return the builder
     */
    public BlockTypeBuilder addType(final String name) {
        final BlockTypeBuilder builder = new BlockTypeBuilder(this, name);
        this.types.add(builder);
        return builder;
    }

    /**
     * Finalize the block provider.
     *
     * @return a ready to serialize list of declared block types
     */
    public List<BlockType> generate() {
        return this.types.stream().map(type -> type.blockType).collect(Collectors.toList());
    }
}
