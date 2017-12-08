import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.UUID;

@AllArgsConstructor
public class Player {

    @NotNull
    @Size(min = 3, max = 32, message = "Player nick length must be from 3 to 32 symbols")
    @Pattern(regexp = "[a-zA-Z0-9_]+", message = "Invalid nick")
    @Getter @Setter private String playerNick;

    @NotNull
    @Size(min = 3, max = 32, message = "Player first name length must be from 3 to 32 symbols")
    @Pattern(regexp = "^([A-Z])[a-z]+", message = "Invalid first name")
    @Getter
    @Setter
    private String playerFirstName;

    @NotNull
    @Size(min = 3, max = 32, message = "Player second name length must be from 3 to 32 symbols")
    @Pattern(regexp = "^([A-Z])[a-z]+", message = "Invalid second name")
    @Getter
    @Setter
    private String playerSecondName;

    @NotNull(message = "Then who are you?")
    @Getter @Setter private Gender gender;

    @NotNull
    @Size(max = 64, message = "Email length can not be more than 64 symbols")
    @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message = "Invalid email")
    @Getter
    @Setter
    private String playerEmail;

    @PastOrPresent(message = "Registration date cant be at future")
    @Getter
    @Setter
    private Calendar registrationDate;


}
