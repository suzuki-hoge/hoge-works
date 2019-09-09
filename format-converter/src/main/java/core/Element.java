package core;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@AllArgsConstructor
@ToString(includeFieldNames = false)
public class Element {
    @Getter
    private final Key k;
    @Getter
    private final Value v;
}
