package com.github.perryth3platypus.interfaces;

public interface ValidEntityListener {
    /* Used when saving/editing entities (like books or patrons); Will alert the MainPanel or whatever panel is
    * holding the save/edit button that it's ok or not ok to use the button, depending on if the mandatory fields are
    * filled or not and all fields are valid (e.g. names are not too long)*/
    public void entityIsValid(boolean entityStatus);
}
