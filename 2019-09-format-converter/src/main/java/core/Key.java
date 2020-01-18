package core;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@AllArgsConstructor
@ToString(includeFieldNames = false)
public class Key {
    @Getter
    private final List<String> words;
}
