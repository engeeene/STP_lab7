import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameServer {

    public GameServer() {
        gameId = UUID.randomUUID();
        players = new ArrayList<Player>();
    }

    @NotNull(message = "Null game id")
    @Getter
    @Setter
    private UUID gameId;

    @NotNull(message = "Unset game mode")
    @Getter
    @Setter
    private GameMode gameMode;

    @Valid
    @NotNull (message = "List of players can not be null")
    @Size(min = 2, max = 32, message = "At game must be at least 2 players")
    @Getter @Setter private List<Player> players;

    @NotNull
    @Size(min = 3, max = 21, message = "Invalid host ip length")
    @Pattern(regexp = "((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?):((6553[0-5])|(655[0-2][0-9])|(65[0-4][0-9]{2})|(6[0-4][0-9]{3})|([1-5][0-9]{4})|([0-5]{0,5})|([0-9]{1,4}))", message = "Invalid host ip")
    @Getter
    @Setter
    private String hostIp;
}
