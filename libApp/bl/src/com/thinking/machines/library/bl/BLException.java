/*package com.thinking.machines.library.bl;
import java.util.*;
public class BLException extends Exception
{
private HashMap<String,String> exception;
public BLException()
{
exception=new HashMap<String,String>();
}
public void addException(String key,String value) throws BLException
{
exception.put(key,value);
}
public String toString() 
{
return "BLException";
}
public String getMessage()
{
return "BLException";
}
public int size()
{
return exception.size();
}
public String getException(String key)
{
return exception.get(key);
}
public ArrayList<String> getProperties()
{
ArrayList<String> properties=new ArrayList<String>(exception.keySet());
return properties;
}
}*/
//.......................................................................................................................

package com.thinking.machines.library.bl;
public class BLException extends Exception
{
public BLException(String message)
{
super(message);
}
}

