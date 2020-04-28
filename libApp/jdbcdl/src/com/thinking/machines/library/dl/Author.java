package com.thinking.machines.library.dl;
import java.util.*;
public class Author implements AuthorInterface
{
private int code;
private String name;
public void setCode(int code)
{
this.code=code;
}
public int getCode()
{
return this.code;
}
public void setName(String name)
{
this.name=name;
}
public String getName()
{
return this.name;
}
public boolean equals(Object o)
{
if(!(o instanceof Author)) return false;
Author otherAuthor=(Author)o;
return this.name.equals(otherAuthor.getName());
}
public int compareTo(AuthorInterface author)
{
return this.code-author.getCode();
}
public int hashCode()
{
return this.code;
}
} 
