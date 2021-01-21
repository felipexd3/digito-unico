package com.fb.onedigit.messages;

public class Messages {
    public static final String MALFORMED_JSON_REQUEST = "101#Malformed json request#Some parameters are required or invalid for this request";
    public static final String ENTITY_NOT_FOUND = "102#Operation failed. %s not found";
    public static final String ENTITY_ALREADY_EXISTS = "106#Entity already exists.";
    public static final String ENTITY_UUID_SHOULD_BE_NOT_NULL = "107#Entity uuid should not be null.";;
    public static final String ENTITY_CANNOT_BE_DELETED_BECAUSE_IT_DOESNT_EXIST_OR_IT_WAS_ALREADY_DELETE = "108#Entity cannot be deleted because it " +
        "doesnt exist or it was already deleted";
}
