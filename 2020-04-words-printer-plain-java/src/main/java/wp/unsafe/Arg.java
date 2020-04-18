package wp.unsafe;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class Arg {
    private final String v;

    boolean notOption() {
        return false; // todo impl
    }

    boolean isOption(String s) {
        return false; // todo impl
    }
}
