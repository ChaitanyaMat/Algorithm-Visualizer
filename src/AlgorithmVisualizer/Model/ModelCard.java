
package AlgorithmVisualizer.Model;

import javax.swing.ImageIcon;

public class ModelCard {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getUses() {
        return uses;
    }

    public void setUses(double uses) {
        this.uses = uses;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public ModelCard() {
    }

    public ModelCard(String title, double uses, ImageIcon imageIcon) {
        this.title = title;
        this.uses = uses;
        this.imageIcon = imageIcon;
    }

    private String title;
    private double uses;
    private ImageIcon imageIcon;
}
