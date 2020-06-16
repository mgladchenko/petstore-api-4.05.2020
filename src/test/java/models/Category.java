package models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Category {
    @Builder.Default
    private long id = 0;
    @Builder.Default
    private String name = "zoo";
}
