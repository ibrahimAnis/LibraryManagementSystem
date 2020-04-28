package com.thinking.machines.library.bl;
public class Author implements AuthorInterface
{
private int code;
private String name;
public Author()
{
this.code=0;
this.name=null;
};
public Author(int code,String name)
{
this.code=code;
this.name=name;
}
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
return this.code==otherAuthor.getCode();
}
public int compareTo(AuthorInterface author)
{
return this.name.toUpperCase().compareTo(author.getName().toUpperCase());
}
public int hashCode()
{
return this.code;
}
} 
