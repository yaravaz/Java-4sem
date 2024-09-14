import com.example.lab13.builder.UserBuilder;
import com.example.lab13.exception.RepositoryException;
import com.example.lab13.model.User;
import com.example.lab13.repository.dbconstants.UserTableConstants;
import com.example.lab13.util.HashPassword;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserBuilderTest {

    private static final String LOGIN = "admin";
    private static final byte [] PASSWORD = HashPassword.getHash("admin");

    private static User EXPECTED_USER = null;

    @BeforeAll
    public static void initExpectedUser(){
        EXPECTED_USER = new User(
                LOGIN,
                PASSWORD
        );
    }

    @Test
    public void shouldBuildAndReturnUserWithParameters() throws SQLException, RepositoryException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getString(UserTableConstants.LOGIN.getFieldName())).thenReturn(LOGIN);
        when(resultSet.getBytes(UserTableConstants.PASSWORD.getFieldName())).thenReturn(PASSWORD);

        UserBuilder userBuilder = new UserBuilder();
        User actualUser = userBuilder.build(resultSet);

        assertEquals(EXPECTED_USER, actualUser);
    }
}
