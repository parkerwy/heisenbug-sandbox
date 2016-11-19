package lab.heisenbug.sandbox.javassist;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: parker
 * Date: Jul 2, 2010
 * Time: 10:51:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class JavassistLoggerTest {

    @Before
    public void setup() throws NotFoundException, CannotCompileException {
        ClassPool pool = ClassPool.getDefault();
        CtClass ftpClient = pool.get("org.apache.commons.net.ftp.FTPClient");
        CtClass ftp = ftpClient.getSuperclass();
        CtClass socketClient = ftp.getSuperclass();
        AnywhereLogger.inject(socketClient);
        AnywhereLogger.inject(ftpClient);
        AnywhereLogger.inject(ftp);
        socketClient.toClass();
        ftp.toClass();
        ftpClient.toClass();
    }

    @Ignore
    @Test
    public void RunFtp() throws IOException {
        FTPClient ftp = new FTPClient();
        ftp.connect("192.168.1.240");
        ftp.login("parker", "");
        ftp.quit();
    }
}
