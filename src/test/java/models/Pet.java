package models;

import lombok.Builder;
import lombok.Getter;

import static models.Status.*;

@Getter
@Builder
public class Pet {
    @Builder.Default
    private long id = 0;
    @Builder.Default
    private Category category = Category.builder().build();
    @Builder.Default
    private String name = "kitty";
    @Builder.Default
    private String[] photoUrls = new String[] {"url1", "url2"};
    @Builder.Default
    private Tags[] tags = new Tags[]{Tags.builder().build()};
    @Builder.Default
    private Status status = AVAILABLE;
}
