package robot.utils;

/**
 *
 * @author Vasilis Naskos
 */
public class Button {
    private final ButtonMap map;
    
    public Button(ButtonMap map) {
        this.map = map;
    }
    
    public static Button parseButton(String str) {
        ButtonMap btn = ButtonMap.valueOf(str);
        
        return new Button(btn);
    }

    public ButtonMap getButtonMap() {
        return map;
    }
    
    @Override
    public String toString() {
        return map.toString();
    }
}
