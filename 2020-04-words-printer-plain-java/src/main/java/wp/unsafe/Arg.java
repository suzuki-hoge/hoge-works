package wp.unsafe;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class Arg {
    final String v;

    boolean empty() {
        return v.trim().equals("");
    }

    boolean notOption() {
        return !v.startsWith("--");
    }

    boolean isOption(String s) {
        return v.equals("--" + s);
    }
}
