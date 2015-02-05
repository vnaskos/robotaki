package robot.actions;

import java.awt.Robot;

/**
 *
 * @author Vasilis Naskos
 */
public class Repeat implements Action {
    
    public static final int ID = 5;
    
    private int times;
    private int counter;
    private int startLine;

    public Repeat(int times) {
        this.times = times;
    }

    public Repeat(String encoded) {
        parse(encoded);
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter() {
        this.counter = times;
    }
    
    public void decreaseCounter() {
        counter--;
    }
    
    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    @Override
    public final void parse(String encoded) {
        String[] fields = encoded.split(":");
        
        times = Integer.parseInt(fields[1]);
    }

    @Override
    public String encode() {
        String encoded = ID+":"+times;
        return encoded;
    }

    @Override
    public void run(Robot robot) {}

    @Override
    public String toString() {
        String action = "Repeat " + times + " times";
        return action;
    }
}
