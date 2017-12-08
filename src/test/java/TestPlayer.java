import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Calendar;
import java.util.Set;

public class TestPlayer {
    private Player player;
    private Validator validator;

    @Before
    public void before() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);

        player = new Player("engeeene", "Tymur", "Levchnko", Gender.Male, "en9eeene@gmail.com", calendar);

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void testNick() {
        player.setPlayerNick("sdfdsdsf~");
        Set<ConstraintViolation<Player>> constraintViolations = validator.validate(player);

        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals("Invalid nick", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void testFirstName() {
        player.setPlayerFirstName("tymur");
        Set<ConstraintViolation<Player>> constraintViolations = validator.validate(player);

        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals("Invalid first name", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void testSecondName() {
        player.setPlayerSecondName("Le");
        Set<ConstraintViolation<Player>> constraintViolations = validator.validate(player);

        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals("Player second name length must be from 3 to 32 symbols", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void testGender() {
        player.setGender(null);
        Set<ConstraintViolation<Player>> constraintViolations = validator.validate(player);

        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals("Then who are you?", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void testEmail() {
        player.setPlayerEmail("e-mail");
        Set<ConstraintViolation<Player>> constraintViolations = validator.validate(player);

        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals("Invalid email", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void testDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        player.setRegistrationDate(calendar);
        Set<ConstraintViolation<Player>> constraintViolations = validator.validate(player);

        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals("Registration date cant be at future", constraintViolations.iterator().next().getMessage());
    }


}
