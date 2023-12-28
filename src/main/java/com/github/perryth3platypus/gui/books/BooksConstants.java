package com.github.perryth3platypus.gui.books;

import java.util.HashMap;
import java.util.Map;

public final class BooksConstants {

    private BooksConstants(){
        // don't instantiate this
    }

    /* fields that appear in GUI */
    public static final String[] FIELDS = {"id", "Title", "Author(s)", "Subject", "Genre", "Series", "Volume", "Edition",
            "Country", "Language", "Release Year", "Imprint", "Publisher", "Imprint", "Publisher", "ISBN", "Barcode", "Accession", "Location",
            "Entry Date", "Status", "Current Holder", "Notes"};

    /* this exists to map the name of the user-friendly gui fields to the actual book attribute names
     keys are gui string name and values are book attributes */
    public static final Map<String, String> ATTRIBUTE_MAP = new HashMap<>();
    static {
        ATTRIBUTE_MAP.put("id", "bookID");
        ATTRIBUTE_MAP.put("Title", "title");
        ATTRIBUTE_MAP.put("Author(s)", "author");
        ATTRIBUTE_MAP.put("Subject", "subject");
        ATTRIBUTE_MAP.put("Genre", "genre");
        ATTRIBUTE_MAP.put("Series", "series");
        ATTRIBUTE_MAP.put("Volume", "volumeNumber");
        ATTRIBUTE_MAP.put("Edition", "editionNumber");
        ATTRIBUTE_MAP.put("Country", "country");
        ATTRIBUTE_MAP.put("Language", "bookLanguage");
        ATTRIBUTE_MAP.put("Release Year", "releasedYear");
        ATTRIBUTE_MAP.put("Imprint", "imprint");
        ATTRIBUTE_MAP.put("Publisher", "publisher");
        ATTRIBUTE_MAP.put("ISBN", "isbn");
        ATTRIBUTE_MAP.put("Barcode", "barcode");
        ATTRIBUTE_MAP.put("Accession", "accession_number");
        ATTRIBUTE_MAP.put("Location", "location");
        ATTRIBUTE_MAP.put("Entry Date", "entryDate");
        ATTRIBUTE_MAP.put("Status", "status");
        ATTRIBUTE_MAP.put("Current Holder", "currentHolder");
        ATTRIBUTE_MAP.put("Notes", "notes");

    }
}
