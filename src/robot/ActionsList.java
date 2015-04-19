package robot;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import robot.actions.Action;

/**
 *
 * @author Vasilis Naskos
 */
public class ActionsList extends JList<Action> implements ActionObserver {

    DefaultListModel model;

    public ActionsList() {
        model = new DefaultListModel();
        this.setModel(model);
    }

    public ActionsList(DefaultListModel model) {
        this.model = model;
        this.setModel(model);
    }
    
    @Override
    public void notifySelection(int line) {
        this.setSelectedIndex(line);
    }

    @Override
    public void addAction(Action action) {
        model.addElement(action);
    }
    
    public ArrayList<Action> getActions() {
        ArrayList<Action> actions = new ArrayList<Action>();
        
        for (int i = 0; i < model.size(); i++) {
            Action a = (Action) model.get(i);
            actions.add(a);
        }
        
        return actions;
    }
    
    public void removeAllActions() {
        model.removeAllElements();
    }
    
    public void removeSelected() {
        int[] selected = this.getSelectedIndices();
        for (int i = selected.length - 1; i >= 0; i--) {
            model.remove(selected[i]);
        }
    }
    
    public void moveUp() {
        int[] selected = this.getSelectedIndices();
        for (int i = 0; i < selected.length; i++) {
            moveElement(selected[i], -1);
            selected[i]--;
        }
        this.setSelectedIndices(selected);
    }

    public void moveDown() {
        int[] selected = this.getSelectedIndices();
        for (int i = selected.length - 1; i >= 0; i--) {
            moveElement(selected[i], 1);
            selected[i]++;
        }
        this.setSelectedIndices(selected);
    }

    private void moveElement(int indexOfSelected, int direction) {
        swapElements(indexOfSelected, indexOfSelected + 1 * direction);
        indexOfSelected = indexOfSelected + 1 * direction;
        this.setSelectedIndex(indexOfSelected);
        this.updateUI();
    }

    private void swapElements(int pos1, int pos2) {
        Action tmp = (Action) model.get(pos1);
        model.set(pos1, model.get(pos2));
        model.set(pos2, tmp);
    }
}
