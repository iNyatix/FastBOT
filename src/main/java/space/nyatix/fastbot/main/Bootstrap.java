package space.nyatix.fastbot.main;

import space.nyatix.fastbot.FastBOT;

import javax.security.auth.login.LoginException;

/**
 * @author Nyatix
 * @created 28.08.2021 - 20:43
 */
public final class Bootstrap {

    public static void main(String[] args) throws LoginException {
        new FastBOT().start();
    }

}
