package com.swen262;

public class RemoveRelease implements Action{
    private PersonalLibrary library;

    public RemoveRelease(PersonalLibrary library){
        this.library = library;
    }

    @Override
    public void performAction(Object o) {
        if(o instanceof Release){
            Release newRelease = (Release) o;
            library.removeRelease(newRelease);
        }
    }
}
