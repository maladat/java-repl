package javarepl.completion;

import org.junit.Test;

import static com.googlecode.totallylazy.Sequences.empty;
import static com.googlecode.totallylazy.Sequences.sequence;
import static javarepl.completion.CompleterTestHelper.*;
import static org.junit.Assert.assertThat;

public class CommandCompleterTest {
    @Test
    public void shouldCompleteCommandWithGivenCandidates() {
        CommandCompleter completer = new CommandCompleter("command", sequence("first", "second"));

        assertThat(completer.apply("invalid"), completesTo(candidates(), position(0)));
        assertThat(completer.apply(""), completesTo(candidates("command"), position(0)));
        assertThat(completer.apply("command "), completesTo(candidates("first", "second"), position(8)));
    }

    @Test
    public void shouldCompleteCommandWithoutCandidates() {
        CommandCompleter completer = new CommandCompleter("command", empty(String.class));

        assertThat(completer.apply(""), completesTo(candidates("command"), position(0)));
        assertThat(completer.apply("command "), completesTo(candidates(), position(0)));
    }
}
