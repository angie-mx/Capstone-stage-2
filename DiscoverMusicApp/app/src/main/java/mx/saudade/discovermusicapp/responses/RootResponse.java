package mx.saudade.discovermusicapp.responses;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by angie on 6/19/16.
 */

public class RootResponse {

    private Root root;

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("root", root)
                .toString();
    }
}
