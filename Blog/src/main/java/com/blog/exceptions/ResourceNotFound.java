package com.blog.exceptions;

public class ResourceNotFound extends RuntimeException{

    String resName;
    String fileName;
    Integer id;

    public ResourceNotFound(String resName, String fileName, Integer id){
        super(String.format("%s not found with %s = %s", fileName, resName, id));
        this.fileName = fileName;
        this.resName = resName;
        this.id = id;
    }

}
