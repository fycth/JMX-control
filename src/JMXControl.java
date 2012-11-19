
/**
 * Created with IntelliJ IDEA.
 * User: Andrey Sergienko
 * Date: 11/18/12
 * Time: 11:44 AM
 */

public class JMXControl
{
    public static void main (String[] args)
    {
        JMXFunctions jmx = new JMXFunctions();

        String host = "AcePlatfor05.devlab.ad";
        Integer port = 29612; //29612 jetty, 29601 platform
        String serviceName = "UCMDB:service=Settings Services";
        String opname = "getSettingFromDB";
        Object[] params = new Object[] {1, "jetty.root.context"};

        String res = (String)jmx.runCommand(host, port, serviceName, opname, params);

        System.out.println("Result is: " + res);
    }
}
