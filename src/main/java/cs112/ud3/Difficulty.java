package cs112.ud3;

/*
 * Difficulty.java - This class will be the abstract parent class that will serve as a template for the construction of the Minesweeper game. Based on the difficulty chosen, the game will be adjusted to have a smaller or bigger playing board, as well as more or less mines and flags.
 * @author Noah Shew
 * @version v1
 */
public abstract class Difficulty {
    //CONSTANT VARIABLES
    public static final String DEFAULT_NAME = "Difficulty";
    public static final int DEFAULT_WIDTH = 10;
    public static final int DEFAULT_HEIGHT = 10;
    public static final int DEFAULT_MINE_TILES = 5;
    public static final int DEFAULT_FLAGS = 5;

    //INSTANCE VARIABLES
    private String name;
    private int width;
    private int height;
    private int mineTiles;
    private int flags;
    //CONSTRUCTORS
    //Default constructor
    public Difficulty() {
        this(DEFAULT_NAME, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_MINE_TILES, DEFAULT_FLAGS);
    }

    //Full constructor
    public Difficulty(String name, int width, int height, int mineTiles, int flags) throws IllegalArgumentException{
        if (!this.setAll(name, width, height, mineTiles, flags)) {
            //System.out.println("ERROR: Invalid data given to the Difficulty full constructor. Exiting program.");
            //System.exit(0); //placeholder for a data mismatch or null data exception
            throw new IllegalArgumentException("Invalid data given to the Difficulty full constructor.");
        }
    }

    //Copy constructor
    public Difficulty(Difficulty other) throws IllegalArgumentException {
        if (other == null) {
            //System.out.println("Error: Null data given to the Difficulty copy constructor. Exiting program.");
            //System.exit(0);
            throw new IllegalArgumentException("Null data given to the Difficulty copy constructor.");
        } else {
            this.setAll(other.name, other.width, other.height, other.mineTiles, other.flags);
        }
    }
    //SETTERS
    public boolean setAll(String name, int width, int height, int mineTiles, int flags) {
        return this.setName(name)
                && this.setWidth(width)
                && this.setHeight(height)
                && this.setMineTiles(mineTiles)
                && this.setFlags(flags);
    }

    public boolean setName(String name) {
        if (name != null) {
            this.name = name;
            return true;
        } else {
            return false;
        }
    }

    public boolean setWidth(int width) {
        if (width >= 0) {
            this.width = width;
            return true;
        } else {
            return false;
        }
    }

    public boolean setHeight(int height) {
        if (height >= 0) {
            this.height = height;
            return true;
        } else {
            return false;
        }
    }

    public boolean setMineTiles(int mineTiles) {
        if (mineTiles >= 0) {
            this.mineTiles = mineTiles;
            return true;
        } else {
            return false;
        }
    }

    public boolean setFlags(int flags) {
        if (flags >= 0){
            this.flags = flags;
            return true;
        } else {
            return false;
        }
    }

    //GETTERS
    public String getName() {
        return this.name;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getMineTiles() {
        return this.mineTiles;
    }

    public int getFlags() {
        return this.flags;
    }

    //TO-STRING, EQUALS & OTHER METHODS
    @Override
    public String toString() {
        return String.format("Difficulty:%nName: %s, Width: %d, Height: %d, Mine Tiles: %d, Flags: %d", this.name,
                this.width, this.height, this.mineTiles, this.flags);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Difficulty otherDifficulty)) {
            return false;
        }

        return this.name.equals(otherDifficulty.name)
                && this.width == otherDifficulty.width
                && this.height == otherDifficulty.height
                && this.mineTiles == otherDifficulty.mineTiles
                && this.flags == otherDifficulty.flags;
    }

    // ABSTRACT METHODS
    // Each difficulty creates a Board configured for itself
    public abstract Board createBoard();
}
