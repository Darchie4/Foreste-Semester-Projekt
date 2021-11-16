package worldofzuul;
/*
* æ - \u00E6
* ø - \u00F8
* å - \u00E5
* */
public enum CommandWord {
    GO("g\u00E5"), QUIT("afslut"), HELP("hj\u00E6lp"), UNKNOWN("?"), COLLECT("samle"),
    BUY("k\u00F8b"), USE("brug"), REMOVE("fjern"), RECYCLE("sorter"), UP("op"), DOWN("ned"),
    RIGHT("h\u00F8jre"), LEFT("venstre");
    
    private String commandString;
    
    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    public String toString() {
        return commandString;
    }
}
