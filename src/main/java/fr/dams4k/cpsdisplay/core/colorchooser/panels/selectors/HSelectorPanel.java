package fr.dams4k.cpsdisplay.core.colorchooser.panels.selectors;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import fr.dams4k.cpsdisplay.core.colorchooser.ImageGenerators;

public class HSelectorPanel extends SelectorPanel implements MouseInputListener {
    private static float selectorLineScale = 1;

    private int yPos = 0;

    public HSelectorPanel() {
        super(ImageGenerators.hColorSelector(), false, 0f, 4);
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    public float getHValue() {
        float yPosInGradient = yPos-topSideImage.getHeight(this); // min value of yPos is not 0 cause of the topSideImage height
        float gradientHeight = getHeight()-topSideImage.getHeight(this)-bottomSideImage.getHeight(this)-(int) (selectorLineScale/2); // remove borders
        return clamp(yPosInGradient/gradientHeight, 0f, 1f); // prevent bugs
    }

    public void updateAxis(int posY, boolean alert) {
        yPos = clamp(posY, topSideImage.getHeight(this), getHeight()-bottomSideImage.getHeight(this)-(int) (selectorLineScale/2));
        if (alert) {
            for (SelectorListener listener : listeners) {
                System.out.println(getHValue());
                listener.HColorChanger(getHValue());
            }
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        // make life easier
        int minY = topSideImage.getHeight(this);
        int maxY = getHeight()-bottomSideImage.getHeight(this); 
        
        // clamping as not to draw on borders
        int x1 = leftSideImage.getWidth(this); 
        int x2 = getWidth()-leftSideImage.getWidth(this)-rightSideImage.getWidth(this);
    
        // draw axis
        drawAxis(true, yPos, x1, x2, minY, maxY, selectorLineScale, graphics);
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        updateAxis(event.getY(), true);
    }

    @Override
    public void mouseMoved(MouseEvent event) {}

    @Override
    public void mouseClicked(MouseEvent event) {
        updateAxis(event.getY(), true);
    }

    @Override
    public void mouseEntered(MouseEvent event) {}

    @Override
    public void mouseExited(MouseEvent event) {}

    @Override
    public void mousePressed(MouseEvent event) {}

    @Override
    public void mouseReleased(MouseEvent event) {}
}