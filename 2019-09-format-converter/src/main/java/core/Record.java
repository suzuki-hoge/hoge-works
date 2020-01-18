package core;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@AllArgsConstructor
@ToString(includeFieldNames = false)
public class Record {
    @Getter
    private final List<Element> es;
}
