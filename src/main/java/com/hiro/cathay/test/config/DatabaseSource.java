package com.hiro.cathay.test.config;

public enum DatabaseSource {

    DB1,
    DB2,
    ;

    public static DatabaseSource fromName(String name) {
        for (DatabaseSource ds : DatabaseSource.values()) {
            if (ds.name().equalsIgnoreCase(name)) {
                return ds;
            }
        }
        throw new IllegalArgumentException("unknown database: " + name);
    }
}
