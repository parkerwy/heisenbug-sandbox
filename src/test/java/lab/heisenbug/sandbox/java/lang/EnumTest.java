package lab.heisenbug.sandbox.java.lang;

import lab.heisenbug.sandbox.Assessment;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: parker
 * Date: 7/28/12
 * Time: 2:28 PM
 * To change this template use File | Settings | File Templates.
 */
@Assessment
public class EnumTest {
    
    private Mode mode ;
    
    @Test
    public void printMode(){
        System.out.println(mode.M2010.x);
    }
    
}

enum Mode {
    M2010(2010), M2012(2012) ;

    int x ;

    Mode(int x){
        this.x = x;
    }
}
