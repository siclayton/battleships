import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.assertEquals;

class BoardMatrixTest {
    BoardMatrix underTest;

    @BeforeEach
    public void setUp() {
        underTest = new BoardMatrix(3,3,7,7);
    }

    @Test
    public void hasSpaceForShipTest() {
        //Given
        boolean exp = true;

        //When
        boolean obs = underTest.hasSpaceForShip(new boolean[]{false, false, false, true, true, true, true});

        //Then
        assertEquals(exp,obs);
    }
}
