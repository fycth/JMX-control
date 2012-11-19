
/**
 * Created with IntelliJ IDEA.
 * User: Andrey Sergienko
 * Date: 11/18/12
 * Time: 10:24 AM
 */

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;

public class JMXFunctions
{
    public Object runCommand(String host, Integer port, String serviceName, String opName, Object[] params)
    {
        JMXConnector jmxConnector = null;
        try
        {
          ObjectName onObjectName = new ObjectName(serviceName);
          jmxConnector = this.getJMXConnector(host, port);
          MBeanServerConnection mBeanServerConnection = jmxConnector.getMBeanServerConnection();
          int paramslen = params.length;
          String[] paramNames = new String[paramslen];
          for (int i = 0; i < paramslen; i++) paramNames[i] = params[i].getClass().getName();

          return mBeanServerConnection.invoke(onObjectName, opName, params, paramNames);
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
        finally
        {
          this.closeJMXConnector(jmxConnector);
        }
    }

    private JMXConnector getJMXConnector(String host, Integer port)
    {
        String url = "service:jmx:rmi:///jndi/rmi://" + host + ":" + port + "/jmxrmi";
        try
        {
            JMXServiceURL jmxServiceURL = new JMXServiceURL(url);
            return JMXConnectorFactory.connect(jmxServiceURL);
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    private void closeJMXConnector(JMXConnector jmxConnector)
    {
        if (jmxConnector != null)
        {
            try
            {
                jmxConnector.close();
            }
            catch (IOException e)
            {
                System.out.println(e);
            }
        }
    }
}
