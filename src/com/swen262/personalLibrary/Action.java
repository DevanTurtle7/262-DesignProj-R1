package com.swen262.personalLibrary;

import com.swen262.exceptions.GUIDNotFoundException;

public interface Action {
    public void performAction(Object o) throws Exception;
}
