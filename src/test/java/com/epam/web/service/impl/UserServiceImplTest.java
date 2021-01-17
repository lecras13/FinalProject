package com.epam.web.service.impl;

import com.epam.web.dao.UserDao;
import com.epam.web.dao.helper.DaoHelper;
import com.epam.web.dao.helper.DaoHelperFactory;
import com.epam.web.entity.Role;
import com.epam.web.entity.User;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.of;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class UserServiceImplTest {
    private static final Long ID = 1L;
    private static final String LOGIN_ADMIN = "admin";
    private static final String PASSWORD_ADMIN = "admin";
    private static final User ADMIN = new User(1L, "admin", "admin", "Роман", "Александров", Role.ADMIN, 0.0D, false, 0.0D, null, null, 0);
  //  private static final User ADMIN_PASSWORD = new User(1L, "admin", "admin-admin", "Роман", "Александров", Role.ADMIN, 0.0D, false, 0.0D, null, null, 0);
    private static final User FIRST_USER = new User(2L, "user1", "user1", "Дмитрий", "Смирнов", Role.USER, 0.0D, false, 0.0D, null, null, 0);
    private static final User SECOND_USER = new User(3L, "user2", "user2", "Федор", "Козлов", Role.USER, 0.0D, false, 0.0D, null, null, 0);
    private static final User THIRD_USER = new User(4L, "user3", "user3", "Алексей", "Чернов", Role.USER, 0.0D, false, 0.0D, null, null, 0);
    private static final User FOURTH_USER = new User(5L, "user4", "user4", "Давид", "Орлов", Role.USER, 0.0D, false, 0.0D, null, null, 0);
    private static final List<User> USERS = Arrays.asList(ADMIN, FIRST_USER, SECOND_USER, THIRD_USER, FOURTH_USER);

    private DaoHelperFactory helperFactoryMock;
    private DaoHelper helperMock;
    private UserDao userDaoMock;
    private UserService userService;

    @Before
    public void setUp() {
        helperFactoryMock = Mockito.mock(DaoHelperFactory.class);
        helperMock = Mockito.mock(DaoHelper.class);
        userDaoMock = Mockito.mock(UserDao.class);
        userService = new UserServiceImpl(helperFactoryMock);
    }

    @Test
    public void testFindUserByLoginAndPasswordShouldReturnOptionalUser() throws ServiceException, DaoException {
        Optional<User> expected = of(ADMIN);
        when(helperFactoryMock.create()).thenReturn(helperMock);
        when(helperMock.createUserDao()).thenReturn(userDaoMock);
        when(userDaoMock.findUserByLoginAndPassword((anyString()), anyString())).thenReturn(expected);

        Optional<User> actual = userService.login(LOGIN_ADMIN, PASSWORD_ADMIN);

        Assert.assertEquals(actual, expected);
    }

    @Test(expected = ServiceException.class)
    public void testFindUserByLoginAndPasswordShouldReturnServiceException() throws ServiceException, DaoException {
        when(helperFactoryMock.create()).thenReturn(helperMock);
        when(helperMock.createUserDao()).thenReturn(userDaoMock);
        when(userDaoMock.findUserByLoginAndPassword((anyString()), anyString())).thenThrow(DaoException.class);

        Optional<User> actual = userService.login(LOGIN_ADMIN, PASSWORD_ADMIN);
    }

    @Test
    public void testFindUsersShouldReturnAllUsers() throws ServiceException, DaoException {
        List<User> expected = USERS;
        when(helperFactoryMock.create()).thenReturn(helperMock);
        when(helperMock.createUserDao()).thenReturn(userDaoMock);
        when(userDaoMock.getAll()).thenReturn(expected);

        List<User> actual = userService.getAllUsers();

        Assert.assertEquals(actual, expected);
    }

    @Test(expected = ServiceException.class)
    public void testFindUsersShouldReturnServiceException() throws ServiceException, DaoException {
        when(helperFactoryMock.create()).thenReturn(helperMock);
        when(helperMock.createUserDao()).thenReturn(userDaoMock);
        when(userDaoMock.getAll()).thenThrow(DaoException.class);

        List<User> actual = userService.getAllUsers();
    }


 /*   @Test
    public void testChangeUserPasswordShouldValid() throws ServiceException, DaoException {
        Optional<User> expected = changePassword(ADMIN, "admin-admin");
        when(helperFactoryMock.create()).thenReturn(helperMock);
        when(helperMock.createUserDao()).thenReturn(userDaoMock);
        //when(userDaoMock.changePasswordByUserId(1L, "admin-admin"));
        when(userDaoMock.getById(1L)).thenReturn(expected);

        userService.changePassword(ADMIN.getId(), "admin-admin");

        Optional<User> actual = userService.login(LOGIN_ADMIN, "admin-admin");

        Assert.assertEquals(actual, expected);
    }

    private Optional<User> changePassword(User user, String password) {
        user.setPassword(password);
        return Optional.of(user);

    }


    @Test
    public void testChangeUserPasswordShouldValid() throws ServiceException, DaoException {
        Optional<User> actual = of(ADMIN);
        Optional<User> expected = changePassword(ADMIN, "admin-admin");
        when(helperFactoryMock.create()).thenReturn(helperMock);
        when(helperMock.createUserDao()).thenReturn(userDaoMock);
        when(userDaoMock.changePasswordByUserId(1L, "admin-admin")).then(changePassword(actual.get(), "admin-admin"));
        when(userDaoMock.getById(1L)).thenReturn(actual);

        userService.changePassword(ADMIN.getId(), "admin-admin");

        Assert.assertEquals(ADMIN.getPassword(), "admin-admin");
    }*/

}
