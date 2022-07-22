package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config 
{
	private static Config manager;

    FileInputStream fileStream;

    private static final Properties prop = new Properties();

    public Config() throws IOException
    {

        String fileLocation;
        fileLocation = System.getProperty("user.dir");

        File location = new File(fileLocation+"/Config/configEBE2E.properties");
                
        fileStream = new FileInputStream(location);
        
        prop.load(fileStream);
    }

    public static Config getInstance()
    {
        if(manager == null)
        {
            synchronized (Config.class)
            {
                try
                {
                    manager = new Config();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        return manager;
    }
    
   
	
	public String getSalesforceLoginURL()
    {
    	return prop.getProperty(getSalesforceLoginURL());
    }
    
    public String getSFUserName() 
    {
		return prop.getProperty("SalesforceLogin.user");
	}
	
	public String getSFPassword() 
	{
		return prop.getProperty("SalesforceLogin.password");
	}
	
	

    public String getString(String key)
    {
        return System.getProperty(key, prop.getProperty(key));
    }
    
	
	public String getSetting(String property) {
		
		return prop.getProperty(property);
	}

}
