package com.swen262.personalLibrary;

import com.swen262.model.Release;

public class AddRelease implements Action {
    private final PersonalLibrary library;

    public AddRelease(PersonalLibrary library) {
        this.library = library;
    }

    @Override
    public void performAction(Object o) {
        if (o instanceof Release) {
            Release newRelease = (Release) o;
            library.addRelease(newRelease);
        }
    }
}
