import java.awt.*;

import javax.swing.*;
import javax.swing.text.Document;

public class PlaceholderTextField extends JTextField {
    private String placeholder;

    /**
     * this constructor does not accept any things
     */
    public PlaceholderTextField() {
    }

    /**
     * this constructor initializes fields
     *
     * @param pDoc     is an instance of Document
     * @param pText    is string
     * @param pColumns is integer
     */
    public PlaceholderTextField(
            final Document pDoc,
            final String pText,
            final int pColumns) {
        super(pDoc, pText, pColumns);
    }

    /**
     * this constructor initializes fields
     *
     * @param pColumns is integer
     */
    public PlaceholderTextField(final int pColumns) {
        super(pColumns);
    }

    /**
     * this constructor initializes fields
     *
     * @param pText is string
     */
    public PlaceholderTextField(final String pText) {
        super(pText);
    }

    /**
     * this constructor initializes fields
     *
     * @param pText    is string
     * @param pColumns is integer
     */
    public PlaceholderTextField(final String pText, final int pColumns) {
        super(pText, pColumns);
    }

    /**
     * this method is for getting placeholder
     *
     * @return the return type of this method is string
     */
    public String getPlaceholder() {
        return placeholder;
    }

    /**
     * this method is override of paintComponent
     *
     * @param pG is an instance of Graphics
     */
    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
            return;
        }

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(getDisabledTextColor());
        g.drawString(placeholder, getInsets().left, pG.getFontMetrics()
                .getMaxAscent() + getInsets().top);
    }

    /**
     * this method is for setting placeholder!
     *
     * @param s is string
     */
    public void setPlaceholder(final String s) {
        placeholder = s;
    }

}