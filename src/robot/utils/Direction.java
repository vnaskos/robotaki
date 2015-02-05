package robot.utils;

/**
 *
 * @author Vasilis Naskos
 */
public class Direction {
    private final DirectionMap map;
    
    public Direction(DirectionMap map) {
        this.map = map;
    }
    
    public static Direction parseDirection(String str) {
        DirectionMap dir = DirectionMap.valueOf(str);

        return new Direction(dir);
    }

    public DirectionMap getDirectionMap() {
        return map;
    }
    
    @Override
    public String toString() {
        return map.toString();
    }
}
