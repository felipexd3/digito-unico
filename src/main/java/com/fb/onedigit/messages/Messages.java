package com.fb.onedigit.messages;

public class Messages {
    public static final String MALFORMED_JSON_REQUEST = "101#Malformed json request#Some parameters are required or invalid for this request";
    public static final String ENTITY_NOT_FOUND = "102#Operation failed.#%s not found";
    public static final String ENTITY_ALREADY_EXISTS = "106#Operation failed.#Entity already exists.";
    public static final String ENTITY_CANNOT_BE_DELETED_BECAUSE_IT_DOESNT_EXIST_OR_IT_WAS_ALREADY_DELETE = "108#Operation failed.#Entity cannot be deleted because it " +
        "doesnt exist or it was already deleted";
    public static final String CRYPTOGRAPHY_FAILED = "107#Cryptography failed#Make sure that you pass the correct public key.";
    public static final String DECODE_FAILED = "107#Decode failed#Public key decode has fail.";
    public static final String INVALID_PUBLIC_KEY = "107#Invalid public key#Make sure that you pass a valid key.";
}
