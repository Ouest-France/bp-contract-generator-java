# bp-contract-generator-java

Here is an easy and programmatic way to generate block provider contracts. Contracts are valid by design and will fit
perfectly with the BMS.

This fluid API helps you to declare multiple block types with theirs endpoint, parameters and associated templates.

You simply need to generate the object and let your favorite JSON framework serialize it.

## How to install

```xml

<dependecy>
    <groupId>io.github.ouest-france</groupId>
    <artifactId>bp-contract-generator</artifactId>
    <version>1.0.0</version>
</dependecy>
```

## Demonstration

Here is an example with a block type that takes an input text, sends it to a remote service (for demonstration only),
and outputs the bolded text with different templates.

```java

@RequestMapping("/block-provider")
@RestController
@RequiredArgsConstructor
public class BlockProviderResource {

    @GetMapping("/configurations")
    public ResponseEntity<List<BlockType>> configurations() {
        return ResponseEntity.ok(
                BlockProviderGenerator.create()
                        // the block type
                        .addType("bold").withVersion(1, 0, 0)

                        // declare the endpoint url
                        .withEndpoint("https://localhost/block-configurations", "GET", "pure")

                        // the input text
                        .addParameter("text")
                        .configurableAsString()
                        .required()
                        .withTitle("Text")
                        .withDescription("Text to display as bold")

                        // send this parameter to the endpoint as a query parameter
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
```

