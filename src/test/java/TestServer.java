import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Calendar;
import java.util.Set;

public class TestServer {
    private Validator validator;
    private GameServer gameServer;

    @Before
    public void before() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);

        Player player1 = new Player("engeeene", "Tymur", "Levchnko", Gender.Male, "en9eeene@gmail.com", calendar);
        Player player2 = new Player("engeeene", "Tymur", "Levchnko", Gender.Male, "en9eeene@gmail.com", calendar);
        gameServer = new GameServer();
        gameServer.setGameMode(GameMode.Hardcore);
        gameServer.getPlayers().add(player1);
        gameServer.getPlayers().add(player2);
        gameServer.setHostIp("195.65.78.250:21505");

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void testGet() {
        Assert.assertNotNull(gameServer.getGameId());
        Assert.assertEquals(GameMode.Hardcore, gameServer.getGameMode());
        Assert.assertTrue(gameServer.getPlayers().size() == 2);
        Assert.assertEquals("195.65.78.250:21505", gameServer.getHostIp());
    }

    @Test
    public void testId() {
        gameServer.setGameId(null);
        Set<ConstraintViolation<GameServer>> constraintViolations = validator.validate(gameServer);

        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals("Null game id", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void testHostIp() {
        gameServer.setHostIp("666.54.87.ds;");
        Set<ConstraintViolation<GameServer>> constraintViolations = validator.validate(gameServer);

        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals("Invalid host ip", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void testGameMode() {
        gameServer.setGameMode(null);
        Set<ConstraintViolation<GameServer>> constraintViolations = validator.validate(gameServer);

        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals("Unset game mode", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void testPlayerList() {
        gameServer.getPlayers().remove(0);
        Set<ConstraintViolation<GameServer>> constraintViolations = validator.validate(gameServer);

        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals("At game must be at least 2 players", constraintViolations.iterator().next().getMessage());
    }

}
