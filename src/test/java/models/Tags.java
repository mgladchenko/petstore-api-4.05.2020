package models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Tags {
    @Builder.Default
    private long id = 0;
    @Builder.Default
    private String name = "animals";
}
