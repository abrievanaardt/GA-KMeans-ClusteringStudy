package ac.up.cos781.clusteringstudy.data;

import java.util.Arrays;

/**
 * Class that represents a single line of the dataset (a data pattern). All
 * methods of this class use deep copying for proper composition.
 *
 * @author Abrie van Aardt
 */
public class Pattern {

    public double[] getInputs() {
        return Arrays.copyOf(inputs, inputs.length);
    }

    public int[] getTargets() {
        return Arrays.copyOf(targets, targets.length);
    }

    public void setInputs(double[] _inputs) {
        inputs = Arrays.copyOf(_inputs, _inputs.length);
    }

    public void setTargets(int[] _targets) {
        targets = Arrays.copyOf(_targets, _targets.length);
    }

    private double[] inputs;
    private int[] targets;
}
