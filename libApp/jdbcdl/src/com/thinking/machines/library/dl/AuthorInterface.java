package com.thinking.machines.library.dl;
public interface AuthorInterface extends java.io.Serializable,Comparable<AuthorInterface>
{
public enum OrderBy{NAME,CODE};
public static OrderBy NAME=OrderBy.NAME;
public static OrderBy CODE=OrderBy.CODE;
public void setCode(int code);
public int getCode();
public void setName(String name);
public String getName();
}

