import com.example.lab13.builder.UniversityBuilder;
import com.example.lab13.exception.RepositoryException;
import com.example.lab13.model.University;
import com.example.lab13.repository.dbconstants.UniversityTableConstants;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UniversityBuilderTest {
    private static final String NAME = "qwerty";
    private static final String RATING = "3";
    private static final String ADDRESS = "sdff";

    private static University EXPECTED = null;

    @BeforeAll
    public static void initExpectedClass() {
        EXPECTED = new University(
                NAME,
                RATING,
                ADDRESS
        );
    }

    @Test
    public void shouldBuildAndReturnUserWithParameters() throws SQLException, RepositoryException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getString(UniversityTableConstants.NAME.getFieldName())).thenReturn(NAME);
        when(resultSet.getString(UniversityTableConstants.RATING.getFieldName())).thenReturn(RATING);
        when(resultSet.getString(UniversityTableConstants.ADDRESS.getFieldName())).thenReturn(ADDRESS);

        UniversityBuilder universityBuilder = new UniversityBuilder();
        University actualUniversity = universityBuilder.build(resultSet);

        assertEquals(EXPECTED, actualUniversity);
    }
}


