# MMLS Implementation - Group 5
Team Project Implementation for Spring 2022.
## Initialisation
Use run.bat for windows systems, run.sh for linux-based systems. 
## Usage
```
"==========================",
"         COMMANDS         ",
"==========================",
"searchlib [cat] [attr] [query]",
"   Searches your personal library.",
"   [cat]: The type of object you're searching for (artist, song, release).",
"   [attr]: The attribute you're searching by (name, type, title, duration, rating, artist).",
"   [query]: Your search query.",
"searchdb [cat] [attr] [query]",
"   Searches the database",
"   [cat]: The type of object you're searching for (artist, song, release).",
"   [attr]: The attribute you're searching by (name, type, title, duration, rating, artistName, guid, dateRange, trackGUID, trackName, artistGUID).",
"   [query]: Your search query.",
"add [guid] <date>",
"   Adds to your personal library.",
"   [guid]: The GUID of a song or release you want to add.",
"   <date>: (OPTIONAL) The date the song or release was acquired. Defaults to the current time. Format in MM-DD-YYYY",
"remove [guid]",
"   Removes from your personal library.",
"   [guid]: The GUID of a song or release you want to remove.",
"rate [guid] [rating]",
"   Rates a song",
"   [guid]: The GUID of the song you want to rate",
"   [rating]: The rating you want to give the song. An integer between 1 and 5.",
"save",
"   Saves library in it's current state.",
"browse",
"   Enters into browsing mode. Displays your personal library.",
"quit",
"   Exits the program.",
```
